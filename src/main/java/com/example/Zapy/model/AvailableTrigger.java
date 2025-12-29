package com.example.Zapy.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "available_triggers")
public class AvailableTrigger {

    @Id
    private String id;   // UUID

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "type")
    @JsonIgnore
    private List<Trigger> triggers;

    public AvailableTrigger() {}

    // getters & setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<Trigger> getTriggers() { return triggers; }
    public void setTriggers(List<Trigger> triggers) { this.triggers = triggers; }
}
