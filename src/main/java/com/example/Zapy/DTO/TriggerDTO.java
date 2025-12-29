package com.example.Zapy.DTO;

public record TriggerDTO(
        String id,
        String triggerId,
        String webhookId,
        double x_coordinate,
        double y_coordinate,
        AvailableTriggerDTO type
) {}
