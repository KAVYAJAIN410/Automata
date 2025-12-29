package com.example.Zapy.Controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Zapy.service.CreateZap;

@RestController
@RequestMapping("/zap")
public class ZapController {

    private final CreateZap zapService;

    public ZapController(CreateZap zapService) {
        this.zapService = zapService;
    }

    @PostMapping
    public ResponseEntity<String> addZap() {

        // Auto-generate unique projectId
        String projectId = UUID.randomUUID().toString();

        zapService.create(projectId);

        return ResponseEntity.ok(projectId); // return it to frontend
    }
}
