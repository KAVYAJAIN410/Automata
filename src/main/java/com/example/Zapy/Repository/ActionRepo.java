package com.example.Zapy.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.Zapy.model.Action;




public interface ActionRepo extends JpaRepository<Action, String>   {
    @Modifying
    @Query("DELETE FROM Action a WHERE a.zap.id = :zapId")
    void deleteByZapId(String zapId);
}
