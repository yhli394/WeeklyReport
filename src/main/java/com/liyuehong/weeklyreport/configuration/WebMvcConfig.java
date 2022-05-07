package com.liyuehong.weeklyreport.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author yhli3
 * @classname WebMvcConfig
 * @Date 2021/12/25 20:47
 */
@Configuration
@Component
@ConfigurationProperties(prefix = "cross")
public class WebMvcConfig implements WebMvcConfigurer {

    private String[] crossLists;

    public String[] getCrossLists() {
        return crossLists;
    }

    public void setCrossLists(String[] crossLists) {
        this.crossLists = crossLists;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(crossLists)
                .allowedMethods("*")
                .allowCredentials(true)
                .allowedHeaders("*");
    }
}