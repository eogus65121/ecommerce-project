package com.done.ecommerce.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Redis repository 생성 및 활성화
 * RedisConnectionFactory를 통해 내장 혹은 외부 Redis를 연결
 * redisTemplate : RedisTemplate를 통해 RedisConnection에서 넘겨준 byte 값을 객체 직렬화한다. Redis와 통신 시 사용
 */

@Configuration
@EnableRedisRepositories
public class RedisConfig {

    @Value("${spring.redis.port}")
    private int redisPort;

    @Value("${spring.redis.host}")
    private String redisHost;

    /**
     * RedisConnectionFactory 인터페이스를 통해 LettuceConnectionFactory를 생성하여 반환한다.
     * 새로운 Connection이나 이미 존재하는 Connection 리턴
     * >> Redis에 접근하기 위해 Redis 저장소와 연결 설정
     */
    @Bean
    public LettuceConnectionFactory redisConnectionFactory(){
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setDatabase(0);
        redisStandaloneConfiguration.setPort(redisPort);
        redisStandaloneConfiguration.setHostName(redisHost);
        return new LettuceConnectionFactory(redisStandaloneConfiguration);
    }

    /**
     * RedisTemplate, StringRedisTemplate bean 생성
     * RedisTemplate : java Object를 redis에 저장하는 경우 사용
     * StringRedisTemplate : 일반적인 String 값을 key,value로 사용하는 경우 사용
     *
     * Dto를 만들어 Redis에 입력하기 위해 GenericJackson2JsonRedisSerializer();를 사용
     */

    @Bean
    public RedisTemplate<String, Object> redisTemplate(){
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        return redisTemplate;
    }

    @Bean
    public StringRedisTemplate stringRedisTemplate(){
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setKeySerializer(new StringRedisSerializer());
        stringRedisTemplate.setValueSerializer(new StringRedisSerializer());
        stringRedisTemplate.setConnectionFactory(redisConnectionFactory());
        return stringRedisTemplate;
    }

}
