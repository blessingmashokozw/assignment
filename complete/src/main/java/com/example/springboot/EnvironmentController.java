package com.example.springboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import java.util.Map;
import java.util.HashMap;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class EnvironmentController {

    @Value("${app.environment:unknown}")
    private String environment;

    @Value("${app.version:1.0.0}")
    private String version;

    @GetMapping("/environment")
    public ResponseEntity<Map<String, Object>> getEnvironmentInfo() {
        Map<String, Object> envInfo = new HashMap<>();
        
        // Basic environment info
        envInfo.put("environment", environment);
        envInfo.put("version", version);
        envInfo.put("application", "Spring Boot Demo App");
        
        // Timestamps
        envInfo.put("currentTime", LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        envInfo.put("serverTimezone", java.time.ZoneId.systemDefault().toString());
        
        // System info
        envInfo.put("javaVersion", System.getProperty("java.version"));
        envInfo.put("osName", System.getProperty("os.name"));
        envInfo.put("osVersion", System.getProperty("os.version"));
        envInfo.put("availableProcessors", Runtime.getRuntime().availableProcessors());
        
        // Memory info
        Runtime runtime = Runtime.getRuntime();
        long maxMemory = runtime.maxMemory();
        long totalMemory = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();
        long usedMemory = totalMemory - freeMemory;
        
        Map<String, Long> memory = new HashMap<>();
        memory.put("max", maxMemory / 1024 / 1024); // MB
        memory.put("total", totalMemory / 1024 / 1024); // MB
        memory.put("used", usedMemory / 1024 / 1024); // MB
        memory.put("free", freeMemory / 1024 / 1024); // MB
        envInfo.put("memoryMB", memory);
        
        // Environment specific details
        Map<String, String> envDetails = new HashMap<>();
        if ("production".equals(environment)) {
            envDetails.put("type", "Production Environment");
            envDetails.put("description", "Live production server with real data");
            envDetails.put("monitoring", "Enabled");
            envDetails.put("debug", "Disabled");
        } else if ("staging".equals(environment)) {
            envDetails.put("type", "Staging Environment");
            envDetails.put("description", "Pre-production testing environment");
            envDetails.put("monitoring", "Enabled");
            envDetails.put("debug", "Enabled");
        } else {
            envDetails.put("type", "Unknown Environment");
            envDetails.put("description", "Environment not properly configured");
            envDetails.put("monitoring", "Unknown");
            envDetails.put("debug", "Unknown");
        }
        envInfo.put("environmentDetails", envDetails);
        
        return ResponseEntity.ok(envInfo);
    }

    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> healthCheck() {
        Map<String, String> health = new HashMap<>();
        health.put("status", "UP");
        health.put("environment", environment);
        health.put("timestamp", LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        health.put("application", "Spring Boot Demo App");
        return ResponseEntity.ok(health);
    }

    @GetMapping("/info")
    public ResponseEntity<Map<String, String>> applicationInfo() {
        Map<String, String> info = new HashMap<>();
        info.put("name", "Spring Boot Demo Application");
        info.put("version", version);
        info.put("environment", environment);
        info.put("description", "Demo application showcasing Spring Boot with Docker deployment");
        info.put("endpoints", new String[]{"/", "/status", "/version", "/users", "/environment", "/health", "/info"}.toString());
        return ResponseEntity.ok(info);
    }
}
