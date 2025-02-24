package com.mezzi.carsnav.Entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    private boolean seen = false; // Default is false (unread)

    private LocalDateTime createdAt = LocalDateTime.now();

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public boolean isSeen() {
        return seen;
    }

    public String getMessage() {
        return message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Notification(Long id, boolean seen, String message, LocalDateTime createdAt) {
        this.id = id;
        this.seen = seen;
        this.message = message;
        this.createdAt = createdAt;
    }

    public Notification() {
    }
}

