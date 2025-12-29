package com.example.Zapy.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Zapy.Repository.ZapRunOutboxRepo;
import com.example.Zapy.Repository.ZapRunRepo;
import com.example.Zapy.model.ZapRun;
import com.example.Zapy.model.ZapRunOutbox;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;

@Service
public class Hook {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ZapRunRepo zapRunRepo;

    @Autowired
    private ZapRunOutboxRepo zapRunOutboxRepo;

    @Transactional
    public void hook(String userId, String zapId, Map<String, Object> payload) {
        try {
            String metadataJson = objectMapper.writeValueAsString(payload);

            ZapRun zapRun = new ZapRun(zapId, metadataJson);
            zapRunRepo.save(zapRun);

            ZapRunOutbox zapOut = new ZapRunOutbox(zapRun);
            zapRunOutboxRepo.save(zapOut);

        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize webhook payload", e);
        }
    }
}


