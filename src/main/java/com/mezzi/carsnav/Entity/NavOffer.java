package com.mezzi.carsnav.Entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "nav_offer")
public class NavOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Unique identifier for the shuttle offer

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company; // Reference to the company creating the offer

    @Column(nullable = false)
    private String departureCity; // City of departure

    @Column(nullable = false)
    private String arrivalCity; // City of arrival

    @Column(nullable = false)
    private LocalDate startDate; // Start date of the subscription

    @Column(nullable = false)
    private LocalDate endDate; // End date of the subscription

    @Column(nullable = false)
    private LocalTime departureTime; // Departure time (time of day)

    @Column(nullable = false)
    private LocalTime arrivalTime; // Arrival time (time of day)

    @Column(nullable = false)
    private int maxSubscribers; // Maximum number of subscribers

    @Column(nullable = false)
    private int availableSeats; // Available seats for subscription

    @Column(length = 1000)
    private String vehicleDescription; // Description of the vehicle (e.g., air conditioning, number of seats, etc.)


    @Column(nullable = false)
    private int currentSubscribers;


    @Column(nullable = false, updatable = false)
    private LocalDate createdAt; // Timestamp of when the offer was created

    @Column(nullable = false)
    private LocalDate updatedAt; // Timestamp of when the offer was last updated

    // Constructor
    public NavOffer() {}

    // Constructor with essential fields
    public NavOffer(Company company, String departureCity, String arrivalCity, LocalDate startDate, LocalDate endDate,
                    LocalTime departureTime, LocalTime arrivalTime, int maxSubscribers, int availableSeats, String vehicleDescription) {
        this.company = company;
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.startDate = startDate;
        this.endDate = endDate;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.maxSubscribers = maxSubscribers;
        this.availableSeats = availableSeats;
        this.vehicleDescription = vehicleDescription;
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
    }

    // Pre-persist method to set createdAt and default values
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
    }

    // Method to update available seats (e.g., after a user subscribes)
    public void reduceAvailableSeats() {
        if (availableSeats > 0) {
            availableSeats--;
        }
    }

    // Method to check if there are available seats
    public boolean hasAvailableSeats() {
        return availableSeats > 0;
    }

    // Getters and setters for all fields
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    public void setArrivalCity(String arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getMaxSubscribers() {
        return maxSubscribers;
    }

    public void setMaxSubscribers(int maxSubscribers) {
        this.maxSubscribers = maxSubscribers;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public String getVehicleDescription() {
        return vehicleDescription;
    }

    public void setVehicleDescription(String vehicleDescription) {
        this.vehicleDescription = vehicleDescription;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    // Additional Methods:

    // Method to check if the offer is still valid (not expired)
    public boolean isOfferValid() {
        return LocalDate.now().isBefore(endDate);
    }

    // Method to update the offer details
    public void updateOffer(String departureCity, String arrivalCity, LocalDate startDate, String endDate,
                            String departureTime, LocalTime arrivalTime, int maxSubscribers, int availableSeats,
                            String vehicleDescription) {
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.startDate = startDate;
        this.endDate = LocalDate.parse(endDate);
        this.departureTime = LocalTime.parse(departureTime);
        this.arrivalTime = arrivalTime;
        this.maxSubscribers = maxSubscribers;
        this.availableSeats = availableSeats;
        this.vehicleDescription = vehicleDescription;
        this.updatedAt = LocalDate.now();
    }

    public void incrementSubscribers() {
        if (this.currentSubscribers < this.maxSubscribers) {
            this.currentSubscribers++;
        } else {
            throw new IllegalStateException("No available seats left for this offer.");
        }
    }
}
