package com.example.Zapy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Zapy.Repository.AvailableTriggerRepo;
import com.example.Zapy.model.AvailableTrigger;

@Service
public class AvailableTriggerService {

    @Autowired
    private AvailableTriggerRepo availableTriggerRepo;

    public List<AvailableTrigger> getAll() {
        return availableTriggerRepo.findAll();
    }
}
