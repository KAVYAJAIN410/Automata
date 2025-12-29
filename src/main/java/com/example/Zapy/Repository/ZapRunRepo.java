package com.example.Zapy.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Zapy.model.ZapRun;

@Repository
public interface ZapRunRepo extends JpaRepository<ZapRun, String> {

}
