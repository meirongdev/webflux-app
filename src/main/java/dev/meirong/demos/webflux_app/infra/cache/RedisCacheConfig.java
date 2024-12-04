package dev.meirong.demos.webflux_app.infra.cache;

import java.time.Duration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;

@Configuration
public class RedisCacheConfig {
    private static final String SEPARATOR = ":";

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
                .build();
    }
    // @Bean
    // public RedisCacheManagerBuilderCustomizer
    // redisCacheManagerBuilderCustomizer() {
    // return (builder) -> builder
    // .withCacheConfiguration(CacheNames.CUSTOMER_CACHE,
    // cacheConfiguration());
    // }
}
