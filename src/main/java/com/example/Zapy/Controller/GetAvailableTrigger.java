package com.example.Zapy.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Zapy.model.AvailableTrigger;
import com.example.Zapy.service.AvailableTriggerService;

@RestController
@RequestMapping("/AvailableTriggers")
@CrossOrigin(origins = "*")
public class GetAvailableTrigger {

    @Autowired
    private AvailableTriggerService availableTriggerService;

    @GetMapping
    public List<AvailableTrigger> getAllAvailableTriggers() {
        return availableTriggerService.getAll();
    }
}
