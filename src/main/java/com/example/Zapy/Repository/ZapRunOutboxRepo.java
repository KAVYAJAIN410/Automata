package com.example.Zapy.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Zapy.model.ZapRunOutbox;

@Repository
public interface ZapRunOutboxRepo extends JpaRepository<ZapRunOutbox, String> {

}


