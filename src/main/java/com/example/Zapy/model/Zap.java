package com.example.Zapy.model;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "zaps")
public class Zap {

    @Id
    @Column(nullable = false, updatable = false)
    private String id = UUID.randomUUID().toString();

    @Column(name = "project_id")
    private String projectId;

    // @Column(name = "Name")
    // private String name;
   

    @Column(name = "user_id", nullable = false)   // 👈 FK to users.id
    private String userId;

    
    @OneToOne(mappedBy = "zap")
    private Trigger trigger;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    private User user;  // Optional reference for direct access

    @OneToMany(mappedBy = "zap")
    @JsonManagedReference("zap-actions")
    private List<Action> actions;


    @OneToMany(mappedBy = "zap")
    private List<Edges> edges;

    public Zap() {}

    // ------------- GETTERS & SETTERS -------------
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getProjectId() { return projectId; }
    public void setProjectId(String projectId) { this.projectId = projectId; }

    

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

        public Trigger getTrigger() { return trigger; }
    public void setTrigger(Trigger trigger) { this.trigger = trigger; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public List<Action> getActions() { return actions; }
    public void setActions(List<Action> actions) { this.actions = actions; }


    public List<Edges> getEdgeses() { return edges; }
    public void setEdges(List<Edges> edges) { this.edges = edges; }
}
