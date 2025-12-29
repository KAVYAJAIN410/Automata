package com.example.Zapy.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Zapy.model.Zap;

public interface ZapRepository extends JpaRepository<Zap, String> {

    @EntityGraph(attributePaths = {
            "trigger",
            "trigger.type",
            "actions",
            "actions.type"
    })
    Optional<Zap> findById(String id);
}
