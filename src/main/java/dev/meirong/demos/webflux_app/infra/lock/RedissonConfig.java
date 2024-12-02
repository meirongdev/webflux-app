package dev.meirong.demos.webflux_app.infra.lock;

import org.redisson.Redisson;
import org.redisson.api.RedissonReactiveClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfig {

    @Bean
    public RedissonReactiveClient redissonClient() {
        return Redisson.create().reactive();
    }
}
