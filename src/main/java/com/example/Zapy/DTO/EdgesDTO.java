package com.example.Zapy.DTO;

public record EdgesDTO(
        String id,
        String fromTriggerId,
        String fromActionId,
        String toActionId
) {}