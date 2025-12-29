package com.example.Zapy.model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "triggers")
public class Trigger {

    @Id
     @Column(nullable = false, updatable = false)
    private String id=UUID.randomUUID().toString();

    @Column(name = "zap_id", unique = true)
    private String zapId;   // 1–1 with Zap

    @Column(name = "trigger_id")
    private String triggerId; // FK to AvailableTrigger.id

    @Column(name = "webhook_id", nullable = false, unique = true, updatable = false)
private String webhookId;

@PrePersist
private void generateWebhookId() {
    if (this.webhookId == null) {
        this.webhookId = "wh_" + UUID.randomUUID().toString().replace("-", "");
    }
}


    // Many triggers can be of the same available trigger type
    @ManyToOne
    @JoinColumn(name = "trigger_id", referencedColumnName = "id", insertable = false, updatable = false)
    private AvailableTrigger type;

    // Each zap has at most one trigger
    @OneToOne
    @JoinColumn(name = "zap_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Zap zap;

     @Column(name="x_coordinate")
    private double x_coordinate;

     @Column(name="y_coordinate")
    private double y_coordinate;

    public Trigger(String zapId,String triggerId,double x_coordinate,double y_coordinate) {
        this.zapId=zapId;
        this.triggerId=triggerId;
        this.x_coordinate=x_coordinate;
        this.y_coordinate=y_coordinate;
    }

    public Trigger(){};
    public String getWebhookId() {
    return webhookId;
}
    // getters & setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getZapId() { return zapId; }
    public void setZapId(String zapId) { this.zapId = zapId; }

    public double getX() { return x_coordinate; }
    public void setX(double x_coordinate) { this.x_coordinate = x_coordinate; }

    public double getY() { return y_coordinate; }
    public void setY(double y_coordinate) { this.y_coordinate = y_coordinate; }

    public String getTriggerId() { return triggerId; }
    public void setTriggerId(String triggerId) { this.triggerId = triggerId; }

    public AvailableTrigger getType() { return type; }
    public void setType(AvailableTrigger type) { this.type = type; }

    public Zap getZap() { return zap; }
    public void setZap(Zap zap) { this.zap = zap; }
}
