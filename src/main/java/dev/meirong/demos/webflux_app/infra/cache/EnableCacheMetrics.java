package dev.meirong.demos.webflux_app.infra.cache;

import org.springframework.boot.actuate.metrics.cache.CacheMetricsRegistrar;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import dev.meirong.demos.webflux_app.constant.CacheNames;
import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class EnableCacheMetrics {
    private final CacheMetricsRegistrar cacheMetricsRegistrar;

    private final CacheManager cacheManager;
 
    @EventListener(ApplicationStartedEvent.class)
    void addCachesMetrics() {
        cacheMetricsRegistrar.bindCacheToRegistry(cacheManager.getCache(CacheNames.CUSTOMER_CACHE));
    }
}
