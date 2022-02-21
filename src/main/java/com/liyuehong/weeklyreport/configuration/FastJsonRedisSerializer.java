package com.liyuehong.weeklyreport.configuration;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.nio.charset.Charset;

//重写FastJsonRedisSerialize的实现方式。
public class FastJsonRedisSerializer<T> implements RedisSerializer<T> {
    private ObjectMapper objectMapper = new ObjectMapper();
    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");
    private static Logger logger = LoggerFactory.getLogger(FastJsonRedisSerializer.class);

    private Class<T> clazz;

    static {
        // 全局开启AutoType，这里方便开发，使用全局的方式
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        // 建议使用这种方式，小范围指定白名单
//         ParserConfig.getGlobalInstance().addAccept("com.xxx.xxx");
        // key的序列化采用StringRedisSerializer
    }

    public FastJsonRedisSerializer(Class<T> clazz) {
        super();
        this.clazz = clazz;
    }

    /**
     * 将序列化时默认存储byte改为默认存储json
     * @param t
     * @return
     * @throws SerializationException
     */
    @Override
    public byte[] serialize(T t) throws SerializationException {
//        logger.debug("序列化");
        if (t == null) {
            return new byte[0];
        }
        return JSON.toJSONString(t, SerializerFeature.WriteClassName).getBytes(DEFAULT_CHARSET);
    }

    /**
     * 将反序列化时默认存储byte改为默认存储json
     * @param bytes
     * @return
     * @throws SerializationException
     */
    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
//        logger.debug("反序列化");
        if (bytes == null || bytes.length <= 0) {
            return null;
        }
        String str = new String(bytes, DEFAULT_CHARSET);
        return JSON.parseObject(str, clazz);
    }
}
