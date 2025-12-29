package com.example.Zapy.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.Zapy.model.Edges;




public interface EdgeRepo extends JpaRepository<Edges, String>   {
    @Modifying
    @Query("DELETE FROM Edges e WHERE e.zap.id = :zapId")
    void deleteByZapId(String zapId);
}
