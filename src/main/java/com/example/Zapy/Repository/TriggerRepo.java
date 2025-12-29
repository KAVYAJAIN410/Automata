package com.example.Zapy.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.Zapy.model.Trigger;


public interface TriggerRepo extends JpaRepository<Trigger, String>   {
    @Modifying
    @Query("DELETE FROM Trigger t WHERE t.zap.id = :zapId")
    void deleteByZapId(String zapId);
}
