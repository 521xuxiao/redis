package com.example.one.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration
@PropertySource("classpath:/propertis/redis.properties")
public class ResisConfig {
    /**
     * 配置单台redis
     */
//    @Value("${redis.host}")
//    private String host;
//    @Value("${redis.port}")
//    private Integer port;
//    @Bean
//    @Scope("prototype")
//    public Jedis jedis() {
//        return new Jedis(host, port);
//    }
    //////////////////////////////////////////////////////////////////////
    /**
     * 配置分片（实现扩容）
     */
//    @Value("${redis.node}")
//    private String nodes;
//    @Bean
//    @Scope("prototype")
//    public ShardedJedis jedis() {
//        List<JedisShardInfo> shared = new ArrayList<JedisShardInfo>();
//        String[] nodeArr = nodes.split(",");
//        for (String item: nodeArr) {
//            String host = item.split(":")[0];
//            String port = item.split(":")[1];
//            shared.add(new JedisShardInfo(host, port));
//        }
//        return new ShardedJedis(shared);
//    }


    /////////////////////////////////////////////////////////////////////////////
    /**
     * 配置redis哨兵
     */
    @Value("${redis.sentinel}")
    private String sentinel;
    @Bean
    public JedisSentinelPool sentinelPool() {
        Set<String> sentinels = new HashSet<>();
        sentinels.add(sentinel);
        JedisSentinelPool jedisSentinelPool = new JedisSentinelPool("mymaster", sentinels);
        return jedisSentinelPool;
    }
}
