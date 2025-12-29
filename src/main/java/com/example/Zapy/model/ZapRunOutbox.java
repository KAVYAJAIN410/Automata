package com.example.Zapy.model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "zap_run_outbox")
public class ZapRunOutbox {
    @Id
    @Column(name = "id", nullable = false, updatable = false, length = 36)
    private String id;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "zap_run_id", nullable = false, unique = true)
    private ZapRun zapRun;


    public ZapRunOutbox() {
        // generate UUID automatically if not set
        this.id = UUID.randomUUID().toString();
    }

    public ZapRunOutbox(ZapRun zapRun) {
        this();
        this.zapRun = zapRun;
    }

    // getters & setters

    public String getId() {
        return id;
    }

    public ZapRun getZapRun() {
        return zapRun;
    }

    public void setZapRun(ZapRun zapRun) {
        this.zapRun = zapRun;
    }
}
