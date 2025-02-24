package com.mezzi.carsnav.Entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "nav_offer")
public class NavOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @Column(nullable = false , name="departure_city")
    private String departureCity;

    @Column(nullable = false , name ="arrival_city")
    private String arrivalCity;

    @Column(nullable = false , name ="start_date")
    private LocalDate startDate;

    @Column(nullable = false , name ="end_date")
    private LocalDate endDate;

    @Column(nullable = false , name ="departure_time")
    private LocalTime departureTime;

    @Column(nullable = false , name ="arrival_time")
    private LocalTime arrivalTime;

    @Column(nullable = false , name ="max_subscribers")
    private int maxSubscribers;



    @Column(nullable = false , name ="price_offer")
    private double priceOffer = 0.0;



    @Column(nullable = false , name ="available_seats")
    private int availableSeats;

    @Column(length = 1000 , name ="vehicle_description")
    private String vehicleDescription;

    @Column(nullable = false , name ="current_subscribers")
    private int currentSubscribers = 0;

    @Column(nullable = false, updatable = false , name ="created_at")
    private LocalDate createdAt;

    @Column(nullable = false , name ="updated_at")
    private LocalDate updatedAt;

    @OneToMany(mappedBy = "navOffer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Subscription> subscriptions;


    public NavOffer() {}


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
        this.currentSubscribers = 0; // Initialize to 0
    }


    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
    }


    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDate.now();
    }


    public void reduceAvailableSeats() {
        if (availableSeats > 0) {
            availableSeats--;
        }
    }


    public boolean hasAvailableSeats() {
        return availableSeats > 0;
    }

    // Getters and setters

    public double getPriceOffer() {
        return priceOffer;
    }

    public void setPriceOffer(double priceOffer) {
        this.priceOffer = priceOffer;
    }

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

    public int getCurrentSubscribers() {
        return currentSubscribers;
    }

    public void setCurrentSubscribers(int currentSubscribers) {
        this.currentSubscribers = currentSubscribers;
    }



    // Method to check if the offer is still valid (not expired)
    public boolean isOfferValid() {
        return LocalDate.now().isBefore(endDate);
    }

    // Method to update the offer details
    public void updateOffer(String departureCity, String arrivalCity, LocalDate startDate, LocalDate endDate,
                            LocalTime departureTime, LocalTime arrivalTime, int maxSubscribers, int availableSeats,double priceOffer,
                            String vehicleDescription) {
        this.departureCity = departureCity;
        this.arrivalCity = arrivalCity;
        this.startDate = startDate;
        this.endDate = endDate;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.maxSubscribers = maxSubscribers;
        this.availableSeats = availableSeats;
        this.priceOffer=priceOffer;
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