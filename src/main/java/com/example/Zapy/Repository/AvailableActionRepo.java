package com.example.Zapy.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Zapy.model.AvailableAction;


public interface AvailableActionRepo extends JpaRepository<AvailableAction, String> {
}
