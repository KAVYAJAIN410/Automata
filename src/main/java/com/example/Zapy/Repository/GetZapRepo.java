package com.example.Zapy.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Zapy.model.Zap;

public interface GetZapRepo extends JpaRepository<Zap, String> {
    // find all zaps owned by a user
    List<Zap> findAllByUserId(String userId);

    // existing helper
    Optional<Zap> findByProjectId(String projectId);
}
