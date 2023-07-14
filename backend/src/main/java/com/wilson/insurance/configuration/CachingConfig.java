package com.wilson.insurance.configuration;

import lombok.extern.java.Log;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.DateFormat;
import java.util.Date;

@Configuration
@EnableCaching
@EnableScheduling
@Log
public class CachingConfig {
    public static final String JSON_RESOURCE = "JsonResource";

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager(JSON_RESOURCE);
    }

    @Caching(evict = {
            @CacheEvict(allEntries = true, value = JSON_RESOURCE)
    })
    @Scheduled(fixedDelay = 30 * 1000, initialDelay = 500)
    public void cacheEvict() {
        log.info("Flush Cache " + DateFormat.getInstance().format(new Date()));
    }

}