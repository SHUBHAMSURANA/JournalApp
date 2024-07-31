package net.engineeringdigest.journalApp.contoller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthChack {
    @GetMapping("/health-check")
    public String healthCheck() {
        return "OK shubham bhhaiia ";
    }
}
