package com.example.Zapy.Mapper;

import java.util.stream.Collectors;

import com.example.Zapy.DTO.ActionDTO;
import com.example.Zapy.DTO.AvailableActionDTO;
import com.example.Zapy.DTO.AvailableTriggerDTO;
import com.example.Zapy.DTO.EdgesDTO;
import com.example.Zapy.DTO.TriggerDTO;
import com.example.Zapy.DTO.ZapDTO;
import com.example.Zapy.model.Action;
import com.example.Zapy.model.Edges;
import com.example.Zapy.model.Trigger;
import com.example.Zapy.model.Zap;

public class ZapMapper {

    public static ZapDTO toDTO(Zap zap) {
        return new ZapDTO(
                zap.getId(),
                zap.getProjectId(),
                toTriggerDTO(zap.getTrigger()),
                zap.getActions()
                        .stream()
                        .map(ZapMapper::toActionDTO)
                        .collect(Collectors.toList()),
                zap.getEdgeses().stream().map(ZapMapper::toEdgeDTO).collect(Collectors.toList())
        );
    }

    private static TriggerDTO toTriggerDTO(Trigger trigger) {
        if (trigger == null) return null;

        return new TriggerDTO(
                trigger.getId(),
                trigger.getTriggerId(),
                trigger.getWebhookId(),
                trigger.getX(),
                trigger.getY(),
                new AvailableTriggerDTO(
                        trigger.getType().getId(),
                        trigger.getType().getName()
                )
        );
    }

    private static ActionDTO toActionDTO(Action action) {
        return new ActionDTO(
                action.getId(),
                action.getActionId(),
                action.getX(),
                action.getY(),
                new AvailableActionDTO(
                        action.getType().getId(),
                        action.getType().getName()
                )
        );
    }
    public static EdgesDTO toEdgeDTO(Edges edge) {
    return new EdgesDTO(
        edge.getId(),
        edge.getFromTrigger() != null ? edge.getFromTrigger().getId() : null,
        edge.getFromAction() != null ? edge.getFromAction().getId() : null,
        edge.getToAction().getId()
    );
}
}
