package com.example.Zapy.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Zapy.service.ActionService;

@RestController
@RequestMapping("Action/{projectId}")
public class ActionController {
     @Autowired
    private ActionService actionService;
    @PostMapping
    public ResponseEntity<String> addTrigger(
            @PathVariable String projectId,
            @RequestBody String AvailId,
            @RequestBody double x_coordinate,
            @RequestBody double y_coordinate
        ) {



        actionService.createAction(projectId, AvailId,x_coordinate,y_coordinate);

        return ResponseEntity.ok("Action Created");
    }
}
