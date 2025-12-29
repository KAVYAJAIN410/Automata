package com.example.Zapy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Zapy.Repository.AvailableActionRepo;
import com.example.Zapy.model.AvailableAction;


@Service
public class AvailableActionService {

    @Autowired
    private AvailableActionRepo availableActionRepo;

    public List<AvailableAction> getAll() {
        return availableActionRepo.findAll();
    }
}
