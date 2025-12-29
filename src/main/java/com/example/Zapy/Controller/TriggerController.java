package com.example.Zapy.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Zapy.service.TriggerService;

@RestController
@RequestMapping("Trigger/{projectId}")
public class TriggerController {
     @Autowired
    private TriggerService triggerService;
    @PostMapping
    public ResponseEntity<String> addTrigger(
            @PathVariable String projectId,
            @RequestBody String AvailId,
            @RequestBody double x_coordinate,
            @RequestBody double y_coordinate
        ) {



        triggerService.createTrigger(projectId, AvailId,x_coordinate,y_coordinate);

        return ResponseEntity.ok("Webhook received");
    }
}
