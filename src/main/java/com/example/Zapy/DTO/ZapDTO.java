package com.example.Zapy.DTO;

import java.util.List;

public record ZapDTO(
        String id,
        String projectId,
        TriggerDTO trigger,
        List<ActionDTO> actions,
        List<EdgesDTO> Edges
) {}
