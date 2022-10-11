package com.logging.cache.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@Configuration
@EnableRedisRepositories
public class RedisConfig {
    @Value("${spring.redis.host}")
    private String redisHostName;

    @Value("${spring.redis.port}")
    private Integer redisPort;
    

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        RedisStandaloneConfiguration redisConfig = new RedisStandaloneConfiguration();
        redisConfig.setHostName(redisHostName);
        redisConfig.setPort(redisPort);

        JedisConnectionFactory jedisConn=new JedisConnectionFactory(redisConfig);
        jedisConn.getPoolConfig().setMaxIdle(30);
        jedisConn.getPoolConfig().setMinIdle(10);
     //   return new JedisConnectionFactory(redisConfig);
        return jedisConn;
    }


    
    @Bean
    public RedisTemplate<String, String> redisTemplate(){
        RedisTemplate<String, String> empTemplate = new RedisTemplate<>();
        empTemplate.setConnectionFactory(jedisConnectionFactory());
        empTemplate.setEnableTransactionSupport(true);
        
        return empTemplate;
    }
}
