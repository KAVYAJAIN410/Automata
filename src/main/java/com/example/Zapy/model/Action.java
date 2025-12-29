package com.example.Zapy.model;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "actions")
public class Action {

    @Id
    @Column(nullable = false, updatable = false)
    private String id=UUID.randomUUID().toString(); // UUID

    @Column(name = "zap_id")
    private String zapId;

    @ManyToOne
    @JoinColumn(name = "zap_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JsonBackReference("zap-actions")
    private Zap zap;

    @Column(name = "action_id")
    private String actionId; // FK to AvailableAction.id

    @ManyToOne
    @JoinColumn(name = "action_id", referencedColumnName = "id", insertable = false, updatable = false)
@JsonBackReference("availableAction-actions")
    private AvailableAction type;

    @Column(name="x_coordinate")
    private double x_coordinate;

     @Column(name="y_coordinate")
    private double y_coordinate;

    public Action() {}
    public Action(String ZapId,String AvailId,double x_coordinate,double y_coordinate){
        this.zapId=ZapId;
        this.actionId=AvailId;
        this.x_coordinate=x_coordinate;
        this.y_coordinate=y_coordinate;
    }
    // getters & setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getZapId() { return zapId; }
    public void setZapId(String zapId) { this.zapId = zapId; }

    public Zap getZap() { return zap; }
    public void setZap(Zap zap) { this.zap = zap; }

    
 public double getX() { return x_coordinate; }
    public void setX(double x_coordinate) { this.x_coordinate = x_coordinate; }

    public double getY() { return y_coordinate; }
    public void setY(double y_coordinate) { this.y_coordinate = y_coordinate; }

    public String getActionId() { return actionId; }
    public void setActionId(String actionId) { this.actionId = actionId; }

    public AvailableAction getType() { return type; }
    public void setType(AvailableAction type) { this.type = type; }
}
