package com.example.Zapy.model;

import java.util.UUID;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "edges")
public class Edges {

    @Id
    @Column(nullable = false, updatable = false)
    private final String id;

    /* -------------------- ZAP FK -------------------- */

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
        name = "zap_id",
        nullable = false
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Zap zap;

    /* -------------------- SOURCE -------------------- */
    // Exactly one of these must be non-null

    @ManyToOne
    @JoinColumn(name = "from_trigger_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Trigger fromTrigger;

    @ManyToOne
    @JoinColumn(name = "from_action_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Action fromAction;

    /* -------------------- TARGET -------------------- */

    @ManyToOne(optional = false)
    @JoinColumn(name = "to_action_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Action toAction;

    /* -------------------- CONSTRUCTORS -------------------- */

    public Edges() {
        this.id = UUID.randomUUID().toString();
    }

    /* -------------------- VALIDATION -------------------- */

    @PrePersist
    @PreUpdate
    private void validateEdge() {
        boolean hasTrigger = fromTrigger != null;
        boolean hasAction = fromAction != null;

        if (hasTrigger == hasAction) {
            throw new IllegalStateException(
                "Edge must have exactly ONE source: Trigger OR Action"
            );
        }

        if (zap == null) {
            throw new IllegalStateException("Edge must belong to a Zap");
        }
    }

    /* -------------------- FACTORY METHODS -------------------- */

    public static Edges fromTrigger(
            Zap zap,
            Trigger trigger,
            Action toAction
    ) {
        Edges edge = new Edges();
        edge.zap = zap;
        edge.fromTrigger = trigger;
        edge.toAction = toAction;
        return edge;
    }

    public static Edges fromAction(
            Zap zap,
            Action fromAction,
            Action toAction
    ) {
        Edges edge = new Edges();
        edge.zap = zap;
        edge.fromAction = fromAction;
        edge.toAction = toAction;
        return edge;
    }

    /* -------------------- GETTERS -------------------- */

    public String getId() {
        return id;
    }

    public Zap getZap() {
        return zap;
    }

    public Trigger getFromTrigger() {
        return fromTrigger;
    }

    public Action getFromAction() {
        return fromAction;
    }

    public Action getToAction() {
        return toAction;
    }

    /* -------------------- SETTERS -------------------- */

    public void setZap(Zap zap) {
        this.zap = zap;
    }

    public void setFromTrigger(Trigger fromTrigger) {
        this.fromTrigger = fromTrigger;
        this.fromAction = null;
    }

    public void setFromAction(Action fromAction) {
        this.fromAction = fromAction;
        this.fromTrigger = null;
    }

    public void setToAction(Action toAction) {
        this.toAction = toAction;
    }
}
