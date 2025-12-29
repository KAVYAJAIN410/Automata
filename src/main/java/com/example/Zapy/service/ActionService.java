package com.example.Zapy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Zapy.Repository.ActionRepo;
import com.example.Zapy.Repository.ZapRepo;
import com.example.Zapy.model.Action;
import com.example.Zapy.model.Zap;

@Service
public class ActionService {

    @Autowired
    private ActionRepo actionRepo;

    @Autowired
    private ZapRepo zapRepo;

    public String createAction(
            String projectId,
            String availActionId,
            double xCoordinate,
            double yCoordinate
    ) {
        Zap zap = zapRepo.findByProjectId(projectId)
                .orElseThrow(() -> new RuntimeException("Zap not found"));

        Action action = new Action(
                zap.getId(),
                availActionId,
                xCoordinate,
                yCoordinate
        );

        actionRepo.save(action);

        return action.getId(); // 🔑 THIS IS THE KEY
    }
}
