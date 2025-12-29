package com.example.Zapy.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "available_actions")
public class AvailableAction {

    @Id
    private String id;   // UUID

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "type")
@JsonManagedReference("availableAction-actions")
    private List<Action> actions;

    public AvailableAction() {}

    // getters & setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<Action> getActions() { return actions; }
    public void setActions(List<Action> actions) { this.actions = actions; }
}
