package com.wangguolong.concurrency.example.cache;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;

/**
 * @author Wang Guolong
 * @version 1.0
 * @date 2020/1/14 2:34 下午
 */
@Configuration
public class RedisConfig {

    @Bean(name = "redisPool")
    public JedisPool jedisPool(@Value("${jedis.host}") String host,
                               @Value("${jedis.port}") int port,
                               @Value("${jedis.password}") String password) {
        // public JedisPool(final GenericObjectPoolConfig poolConfig, final String host, int port,
        //      int timeout, final String password)
        return new JedisPool(new GenericObjectPoolConfig(), host, port, 10000, password);
    }
}
