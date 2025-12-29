package com.example.Zapy.model;

import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

   @Id
    @Column(nullable = false, updatable = false)
    private String id = UUID.randomUUID().toString();

    @Column(nullable = false, unique = true)
    private String googleId;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(length = 100)
    private String name;

    private String pictureUrl;
    private Instant createdAt;
    private Instant lastLoginAt;

    public User() {}

    public User(String email, String name) {
        this.email = email;
        this.name = name;
    }

    // ---------- GETTERS ----------
    public String getId() {
        return id;
    }

    public String getGoogleId() {
        return googleId;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getLastLoginAt() {
        return lastLoginAt;
    }

    // ---------- SETTERS ----------
    public void setId(String id) {
        this.id = id;
    }

    public void setGoogleId(String googleId) {
        this.googleId = googleId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public void setLastLoginAt(Instant lastLoginAt) {
        this.lastLoginAt = lastLoginAt;
    }
}
