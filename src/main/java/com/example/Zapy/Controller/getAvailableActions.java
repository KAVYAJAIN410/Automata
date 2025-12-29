package com.example.Zapy.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Zapy.model.AvailableAction;
import com.example.Zapy.service.AvailableActionService;


@RestController
@RequestMapping("/AvailableActions")
@CrossOrigin(origins = "*")
public class getAvailableActions {

    @Autowired
    private AvailableActionService availableActionService;

    @GetMapping
    public List<AvailableAction> getAllAvailableTriggers() {
        return availableActionService.getAll();
    }
}
