package com.example.springboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import java.util.Map;
import java.util.HashMap;

@RestController
public class HelloController {

  @Value("${app.environment:unknown}")
  private String environment;

  @GetMapping("/")
  public String index() {
    return "Greetings from Spring Boot with docker!";
  }

  @GetMapping("/status")
  public String status() {
    return "Application is running v1!";
  }

   @GetMapping("/version")
  public String version() {
    return "Application is running v1!";
  }

  @GetMapping("/users")
  public String users() {
    return "You will get a list of users here with rules !";
  }

  @GetMapping("/env")
  public ResponseEntity<Map<String, String>> environment() {
    Map<String, String> env = new HashMap<>();
    env.put("environment", environment);
    env.put("version", "v1");
    env.put("application", "Spring Boot Demo");
    env.put("timestamp", java.time.LocalDateTime.now().toString());
    return ResponseEntity.ok(env);
  }
}
