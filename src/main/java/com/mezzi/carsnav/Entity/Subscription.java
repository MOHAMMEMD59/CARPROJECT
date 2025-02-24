package com.mezzi.carsnav.Entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "subscription")
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "nav_offer_id", nullable = false)
    private NavOffer navOffer;

    @Column(nullable = false, length = 255)
    private String subscriberName;

    @Column(nullable = false, length = 255)
    private String subscriberEmail;

    @Column(nullable = false)
    private LocalDateTime subscriptionDate;

    @Column(nullable = false)
    private boolean isActive;

    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;


    public Subscription() {
    }


    public Subscription(NavOffer navOffer, String subscriberName, String subscriberEmail) {
        this.navOffer = navOffer;
        this.subscriberName = subscriberName;
        this.subscriberEmail = subscriberEmail;
        this.subscriptionDate = LocalDateTime.now();
        this.isActive = true;
    }


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


    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }


    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }


    public void deactivateSubscription() {
        this.isActive = false;
    }
}
