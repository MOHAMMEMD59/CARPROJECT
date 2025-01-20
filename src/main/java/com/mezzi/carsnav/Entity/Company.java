package com.mezzi.carsnav.Entity;



import jakarta.persistence.Entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "companies") // Specify the table name
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment primary key
    private Long id; // Unique identifier for the transport company

    @Column(nullable = false, length = 255)
    private String name; // Name of the transport company

    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt; // Timestamp of company creation

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt; // Timestamp of last update

    // Default constructor
    public Company() {
    }

    // Constructor with parameters
    public Company(String name) {
        this.name = name;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // Getter and Setter methods
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    // Automatically update 'updatedAt' field before updating the entity
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
