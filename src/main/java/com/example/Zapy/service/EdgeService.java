package com.example.Zapy.service;



import org.springframework.stereotype.Service;

import com.example.Zapy.Repository.ActionRepo;
import com.example.Zapy.Repository.EdgeRepo;
import com.example.Zapy.Repository.TriggerRepo;
import com.example.Zapy.model.Action;
import com.example.Zapy.model.Edges;
import com.example.Zapy.model.Trigger;
import com.example.Zapy.model.Zap;

@Service
public class EdgeService {

    private final EdgeRepo edgeRepo;
    private final TriggerRepo triggerRepo;
    private final ActionRepo actionRepo;

    public EdgeService(
            EdgeRepo edgeRepo,
            TriggerRepo triggerRepo,
            ActionRepo actionRepo
    ) {
        this.edgeRepo = edgeRepo;
        this.triggerRepo = triggerRepo;
        this.actionRepo = actionRepo;
    }

    /* -------------------- Trigger → Action -------------------- */

    public void createFromTrigger(
            Zap zap,
            String fromTriggerId,
            String toActionId
    ) {
        Trigger trigger = triggerRepo.findById(fromTriggerId)
                .orElseThrow(() -> new RuntimeException("Trigger not found"));

        Action toAction = actionRepo.findById(toActionId)
                .orElseThrow(() -> new RuntimeException("Target action not found"));

        Edges edge = Edges.fromTrigger(zap, trigger, toAction);
        edgeRepo.save(edge);
    }

    /* -------------------- Action → Action -------------------- */

    public void createFromAction(
            Zap zap,
            String fromActionId,
            String toActionId
    ) {
        Action fromAction = actionRepo.findById(fromActionId)
                .orElseThrow(() -> new RuntimeException("Source action not found"));

        Action toAction = actionRepo.findById(toActionId)
                .orElseThrow(() -> new RuntimeException("Target action not found"));

        Edges edge = Edges.fromAction(zap, fromAction, toAction);
        edgeRepo.save(edge);
    }
}
