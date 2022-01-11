package com.liyuehong.weeklyreport.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @author yhli3
 * @classname SessionConfig
 * @Date 2022/1/11 14:35
 */
@Configuration
@EnableRedisHttpSession(redisNamespace = "week:session")
public class SessionConfig {

}
