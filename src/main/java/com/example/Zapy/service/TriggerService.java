package com.example.Zapy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Zapy.Repository.TriggerRepo;
import com.example.Zapy.Repository.ZapRepo;
import com.example.Zapy.model.Trigger;
import com.example.Zapy.model.Zap;

@Service
public class TriggerService {

    @Autowired
    private TriggerRepo triggerRepo;

    @Autowired
    private ZapRepo zapRepo;

    public String createTrigger(
        String projectId,
        String availTriggerId,
        double xCoordinate,
        double yCoordinate
) {
    Zap zap = zapRepo.findByProjectId(projectId)
            .orElseThrow(() -> new RuntimeException("Zap not found"));

    Trigger trigger = new Trigger(
            zap.getId(),
            availTriggerId,
            xCoordinate,
            yCoordinate
    );

    triggerRepo.save(trigger);
    return trigger.getId(); // 👈 IMPORTANT
}
}

