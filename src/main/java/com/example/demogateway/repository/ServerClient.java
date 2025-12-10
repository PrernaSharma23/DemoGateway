package com.example.demogateway.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@Service
public class ServerClient {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${upstream.server.url:http://localhost:8002}")
    private String serverUrl;

    public Object serverGet(String path) {
        String url = serverUrl + path;
        try {
            ResponseEntity<Object> resp = restTemplate.getForEntity(url, Object.class);
            if (resp.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
            }
            if (resp.getStatusCode() != HttpStatus.OK) {
                throw new ResponseStatusException(resp.getStatusCode(), "Upstream error");
            }
            return resp.getBody();
        } catch (ResourceAccessException e) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Server unreachable: " + e.getMessage());
        } catch (RestClientResponseException e) {
            throw new ResponseStatusException(HttpStatus.valueOf(e.getRawStatusCode()), "Upstream error: " + e.getResponseBodyAsString());
        }
    }
}
