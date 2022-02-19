package com.liyuehong.weeklyreport.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
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
//         ParserConfig.getGlobalInstance().addAccept("me.zhengjie.domain");
        // key的序列化采用StringRedisSerializer
    }

    public FastJsonRedisSerializer(Class<T> clazz) {
        super();
        this.clazz = clazz;
    }

    //序列化 我们存储时，存储的是json对象，而默认存储的是byte类型的，所以在可视化窗口上显示时，看到的是乱码
    @Override
    public byte[] serialize(T t) throws SerializationException {
//        logger.debug("序列化");
        if (t == null) {
            return new byte[0];
        }
        return JSON.toJSONString(t, SerializerFeature.WriteClassName).getBytes(DEFAULT_CHARSET);
    }

    //反序列化
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
