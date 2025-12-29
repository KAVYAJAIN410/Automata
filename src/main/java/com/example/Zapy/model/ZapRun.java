package com.example.Zapy.model;

import com.example.Zapy.enums.ZapRunStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "zap_runs")
public class ZapRun {

   @Id
@Column(nullable = false, updatable = false, length = 36)
private String id = java.util.UUID.randomUUID().toString();

    @Column(name = "zap_id")
    private String zapId;   // FK column

     @Column(columnDefinition = "TEXT")
    private String metadata;

    // Many ZapRuns belong to one Zap
    @ManyToOne
    @JoinColumn(name = "zap_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Zap zap;

    @Column
    private ZapRunStatus status;

    @OneToOne(mappedBy = "zapRun", fetch = FetchType.LAZY)
    private ZapRunOutbox zapRunOutbox;

    public ZapRun() {}

    public ZapRun(String zapId, String metaData) {
        this.metadata = metaData;
        this.zapId = zapId;
    }

    // -------- GETTERS & SETTERS ----------
    public String getId() {
        return id;
    }
    public ZapRunStatus getStatus() {
        return this.status;
    }
    public void setStatus(ZapRunStatus status) {
        this.status=status ;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getZapId() {
        return zapId;
    }

    public void setZapId(String zapId) {
        this.zapId = zapId;
    }
    public String getMetadata() { return metadata; }
    public void setMetadata(String metadata) { this.metadata = metadata; }

    public Zap getZap() {
        return zap;
    }

    public void setZap(Zap zap) {
        this.zap = zap;
    }
     public ZapRunOutbox getZapRunOutbox() { return zapRunOutbox; }
    public void setZapRunOutbox(ZapRunOutbox zapRunOutbox) { this.zapRunOutbox = zapRunOutbox; }
}
