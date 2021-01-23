package com.example.one.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import redis.clients.jedis.Jedis;

@Configuration
@PropertySource("classpath:/propertis/redis.properties")
public class ResisConfig {
    @Value("${redis.host}")
    private String host;
    @Value("${redis.port}")
    private Integer port;

    @Bean
    @Scope("prototype")
    public Jedis jedis() {
        return new Jedis(host, port);
    }
}
