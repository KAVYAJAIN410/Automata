package com.example.Zapy.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Zapy.model.Zap;

@Repository
public interface ZapRepo extends JpaRepository<Zap, String> {

Optional<Zap> findByProjectId(String projectId);


}