package com.example.demogateway.service;

import com.example.demogateway.repository.ServerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Service
public class HealthService {

    private static final Instant START = Instant.now();
    private static final String VERSION = "1.0.0-gateway";

    @Autowired
    private ServerClient serverClient;

    public Map<String, Object> getHealth() {
        Map<String, Object> deps = checkServer();
        Map<String, Object> out = new HashMap<>();
        out.put("service", "gateway");
        String serverStatus = (String) deps.getOrDefault("server", "unreachable");
        out.put("status", "ok".equals(serverStatus) ? "ok" : "degraded");
        out.put("version", VERSION);
        double uptime = (double) (System.currentTimeMillis() - START.toEpochMilli()) / 1000.0;
        out.put("uptime_seconds", Math.round(uptime * 100.0) / 100.0);
        out.put("dependencies", deps);
        return out;
    }

    private Map<String, Object> checkServer() {
        Map<String, Object> result = new HashMap<>();
        try {
            Object resp = serverClient.serverGet("/health");
            if (resp != null) {
                result.put("server", "ok");
            } else {
                result.put("server", "error:empty");
            }
        } catch (Exception e) {
            result.put("server", "unreachable:" + e.getMessage());
        }
        return result;
    }
}
