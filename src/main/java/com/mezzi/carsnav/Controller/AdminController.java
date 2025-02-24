package com.mezzi.carsnav.Controller;

import com.mezzi.carsnav.Entity.NavOffer;
import com.mezzi.carsnav.Entity.Subscription;
import com.mezzi.carsnav.Service.NavOfferService;
import com.mezzi.carsnav.Service.SubscriptionService;
import com.mezzi.carsnav.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")

public class AdminController {

    @Autowired
    private final UserService userService;
    private final NavOfferService navOfferService;
    private final SubscriptionService subscriptionService;

    @Autowired
    public AdminController(UserService userService, NavOfferService navOfferService, SubscriptionService subscriptionService) {
        this.userService = userService;
        this.navOfferService = navOfferService;
        this.subscriptionService = subscriptionService;
    }


    // Endpoint to fetch user count based on role
    @GetMapping("/role-distribution")
    public Map<String, Long> getUserRoleDistribution() {
        Map<String, Long> roleDistribution = userService.getRoleDistribution();
        return roleDistribution;
    }




    // Fetch most popular offers (sorted by subscribers)
    @GetMapping("/offer-popularity")
    public Map<String, Long> getPopularOffers() {
        return navOfferService.getMostPopularOffers();
    }


    // Fetch subscriptions count by date
    @GetMapping("/subscriptions-by-date")
    public Map<LocalDate, Long> getSubscriptionsByDate() {
        return subscriptionService.getSubscriptionsByDate();
    }



    @GetMapping("/subscriptions-by-company/{companyId}")
    public List<Subscription> getSubscriptionsByCompany(@PathVariable Long companyId) {
        return subscriptionService.getSubscriptionsByCompanyId(companyId);
    }

    // Add this method to your AdminController class
    @GetMapping("/offers-by-company")
    public Map<String, Long> getOffersByCompany() {
        return navOfferService.getOffersCountByCompany();  // Ensure you have this method in NavOfferService
    }



}

