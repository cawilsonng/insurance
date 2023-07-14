package com.wilson.insurance.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.wilson.insurance.configuration.CachingConfig;
import lombok.extern.java.Log;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Log
public class ApiServiceImpl implements ApiService {

    private final RestTemplate restTemplate;

    public ApiServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    @Cacheable(CachingConfig.JSON_RESOURCE)
    public JsonNode getJsonResource(String url) {
        log.info("retrieve url : " + url);
        JsonNode rootNode = restTemplate.getForObject(url, JsonNode.class);
        return rootNode;
    }
}
