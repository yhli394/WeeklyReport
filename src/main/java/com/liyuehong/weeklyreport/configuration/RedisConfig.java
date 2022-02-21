package com.liyuehong.weeklyreport.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.lang.reflect.Method;
import java.time.Duration;

@Configuration
//开启基于注解的缓存
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

    final static Logger logger = LoggerFactory.getLogger(RedisConfig.class);

    FastJsonRedisSerializer<Object> fastJsonRedisSerializer = new FastJsonRedisSerializer<>(Object.class);
    /**
     * 更换SpringSession中默认的序列化器
     * @return
     */
//    @Bean("springSessionDefaultRedisSerializer")
//    public RedisSerializer setSerializer(){
//        return fastJsonRedisSerializer;
//    }

    /**
     * 自定义缓存管理器
     * @param connectionFactory
     * @return
     */
    @Bean
    public CacheManager CustomCacheManager(RedisConnectionFactory connectionFactory) {
        RedisCacheManager customCacheManager = RedisCacheManager.RedisCacheManagerBuilder
                //Redis链接工厂
                .fromConnectionFactory(connectionFactory)
                //缓存默认存储一小时
                .cacheDefaults(getCacheConfigurationWithTtl(Duration.ofHours(1)))
                //配置同步修改或删除  put/evict
                .transactionAware()
                //对于不同的cacheName设置不同的过期时间
//                .withCacheConfiguration("week",getCacheConfigurationWithTtl(Duration.ofHours(2)))
//                .withCacheConfiguration("user",getCacheConfigurationWithTtl(Duration.ofHours(1)))
                .build();
        return customCacheManager;
    }


    /**
     * 自定义缓存信息配置
     * @param duration
     * @return
     */
    private RedisCacheConfiguration getCacheConfigurationWithTtl(Duration duration) {
        return RedisCacheConfiguration
                .defaultCacheConfig()
                //设置key和value的序列化方式
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(fastJsonRedisSerializer))
                // 不缓存null
                .disableCachingNullValues()
                // 设置缓存的过期时间
                .entryTtl(duration)
                //修改redis中key的前缀
                .computePrefixWith(cacheName -> cacheName+":");
    }


    /**
     * 不设置主键时的生成策略：类名+方法名+参数
     * @return
     */
    @Override
    @Bean
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuffer sb = new StringBuffer();
                sb.append(target.getClass().getName());
                sb.append(method.getName());
                for (Object obj : params
                ) {
                    sb.append(obj.toString());
                }
                return sb.toString();
            }
        };
    }


    /**
     * 配置缓存异常处理
     * @return
     */
    @Bean
    @Override
    public CacheErrorHandler errorHandler() {
        logger.info("初始化 -> [{}]", "Redis CacheErrorHandler");
        return new CacheErrorHandler() {
            @Override
            public void handleCacheGetError(RuntimeException e, Cache cache, Object key) {
                logger.error("Redis occur handleCacheGetError：key -> [{}]", key, e);
            }

            @Override
            public void handleCachePutError(RuntimeException e, Cache cache, Object key, Object value) {
                logger.error("Redis occur handleCachePutError：key -> [{}]；value -> [{}]", key, value, e);
            }

            @Override
            public void handleCacheEvictError(RuntimeException e, Cache cache, Object key) {
                logger.error("Redis occur handleCacheEvictError：key -> [{}]", key, e);
            }

            @Override
            public void handleCacheClearError(RuntimeException e, Cache cache) {
                logger.error("Redis occur handleCacheClearError：", e);
            }
        };
    }


    /**
     * 自定义redisTemplate
     * @param factory
     * @return
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(fastJsonRedisSerializer);
        redisTemplate.setHashValueSerializer(fastJsonRedisSerializer);
        redisTemplate.setHashKeySerializer(fastJsonRedisSerializer);
        redisTemplate.setConnectionFactory(factory);
        return redisTemplate;
    }

    /**
     * 自定义stringRedisTemplate
     * @param factory
     * @return
     */
    @Bean
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory factory) {
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setKeySerializer(new StringRedisSerializer());
        stringRedisTemplate.setValueSerializer(fastJsonRedisSerializer);
        stringRedisTemplate.setConnectionFactory(factory);
        stringRedisTemplate.setHashValueSerializer(fastJsonRedisSerializer);
        return stringRedisTemplate;
    }



}
