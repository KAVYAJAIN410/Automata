package com.example.Zapy.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Zapy.service.Hook;

@RestController
@RequestMapping("/hook/{userId}/{zapId}")
public class hookController {
    @Autowired
    private Hook hookService;
    @PostMapping
    public ResponseEntity<String> addZapRun(
            @PathVariable String userId,
            @PathVariable String zapId,
            @RequestBody Map<String, Object> payload) {



        hookService.hook(userId, zapId, payload);

        return ResponseEntity.ok("Webhook received");
    }
}
