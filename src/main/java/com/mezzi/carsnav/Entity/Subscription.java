package com.mezzi.carsnav.Entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "subscription") // Specify the table name
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment primary key
    private Long id; // Unique identifier for the subscription

    @ManyToOne
    @JoinColumn(name = "nav_offer_id", nullable = false) // Foreign key to the NavOffer
    private NavOffer navOffer; // The shuttle offer that this subscription is related to

    @Column(nullable = false, length = 255)
    private String subscriberName; // Name of the subscriber

    @Column(nullable = false, length = 255)
    private String subscriberEmail; // Email of the subscriber

    @Column(nullable = false)
    private LocalDateTime subscriptionDate; // Date and time when the subscription was made

    @Column(nullable = false)
    private boolean isActive; // Indicates if the subscription is still active

    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt; // Timestamp when the subscription was created

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt; // Timestamp when the subscription was last updated

    // Default constructor
    public Subscription() {
    }

    // Constructor with all parameters (excluding ID, which is auto-generated)
    public Subscription(NavOffer navOffer, String subscriberName, String subscriberEmail) {
        this.navOffer = navOffer;
        this.subscriberName = subscriberName;
        this.subscriberEmail = subscriberEmail;
        this.subscriptionDate = LocalDateTime.now(); // Set the subscription date to the current time
        this.isActive = true; // By default, a new subscription is active
    }

    // Getters and Setters for all fields
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public NavOffer getNavOffer() {
        return navOffer;
    }

    public void setNavOffer(NavOffer navOffer) {
        this.navOffer = navOffer;
    }

    public String getSubscriberName() {
        return subscriberName;
    }

    public void setSubscriberName(String subscriberName) {
        this.subscriberName = subscriberName;
    }

    public String getSubscriberEmail() {
        return subscriberEmail;
    }

    public void setSubscriberEmail(String subscriberEmail) {
        this.subscriberEmail = subscriberEmail;
    }

    public LocalDateTime getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(LocalDateTime subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
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

    // Automatically set the timestamps before persisting
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // Automatically update the 'updatedAt' timestamp before updating the entity
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    // Method to deactivate the subscription (e.g., if the user cancels or unsubscribes)
    public void deactivateSubscription() {
        this.isActive = false;
    }
}
