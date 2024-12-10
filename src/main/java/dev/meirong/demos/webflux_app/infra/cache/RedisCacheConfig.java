package dev.meirong.demos.webflux_app.infra.cache;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;

import io.lettuce.core.ClientOptions;

@Configuration
public class RedisCacheConfig {
    private static final String SEPARATOR = ":";

    @Value("${spring.data.redis.host}")
    private String redisHost;

    @Value("${spring.data.redis.port}")
    private int redisPort;

    @Bean
    public RedisCacheConfiguration cacheConfiguration() {
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(
                Object.class);
        // Use `Jackson2JsonRedisSerializer` to serialize values, will remove `@class`
        // field in redis
        return RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ZERO)
                .computePrefixWith(cacheName -> cacheName + SEPARATOR)
                // .disableCachingNullValues()
                .serializeValuesWith(SerializationPair.fromSerializer(jackson2JsonRedisSerializer));
    }

    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        RedisCacheConfiguration config = cacheConfiguration();
        return RedisCacheManager.builder(redisConnectionFactory)
                .cacheDefaults(config)
                .enableStatistics()
                .build();
    }

//     @Bean
//     public LettuceConnectionFactory lettuceConnectionFactory() {
//         RedisStandaloneConfiguration redisStandaloneConfiguration  = new RedisStandaloneConfiguration(redisHost, redisPort);
//         ClientOptions clientOptions = ClientOptions.builder()
//                 .publishOnScheduler(true)
//                 .build();
//         LettuceClientConfiguration clientConfiguration = LettuceClientConfiguration.builder()
//                 .clientOptions(clientOptions)
//                 .build();
//         return new LettuceConnectionFactory(redisStandaloneConfiguration, clientConfiguration);
//     }

    // @Bean
    // public RedisCacheManagerBuilderCustomizer
    // redisCacheManagerBuilderCustomizer() {
    // return (builder) -> builder
    // .withCacheConfiguration(CacheNames.CUSTOMER_CACHE,
    // cacheConfiguration());
    // }
}
