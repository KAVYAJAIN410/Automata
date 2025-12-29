package com.example.Zapy.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Zapy.DTO.FlowSaveRequest;
import com.example.Zapy.service.FlowService;

@RestController
@RequestMapping("/api/projects")
public class FlowController {

    private final FlowService flowService;

    public FlowController(FlowService flowService) {
        this.flowService = flowService;
    }

    @PostMapping("/{projectId}/flow")
    public ResponseEntity<?> saveFlow(
            @PathVariable String projectId,
            @RequestBody FlowSaveRequest request
    ) {

        
        flowService.replaceFlowByProjectId(projectId,request);

       

        return ResponseEntity.ok().build();
    }
}
