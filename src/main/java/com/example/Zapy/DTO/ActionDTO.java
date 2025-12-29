package com.example.Zapy.DTO;


public record ActionDTO(
        String id,
        String actionId,
        double x_coordinate,
        double y_coordinate,
        AvailableActionDTO type
) {}
