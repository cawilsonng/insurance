package com.wilson.insurance.services;

import com.fasterxml.jackson.databind.JsonNode;

public interface ApiService {
    /**
     * Retrieves a JSON resource from the specified URL.
     *
     * @param url The URL of the JSON resource.
     * @return The JSON resource as a JsonNode object.
     */
    JsonNode getJsonResource(String url);
}
