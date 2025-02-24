package com.mezzi.carsnav.Repository;

import com.mezzi.carsnav.Entity.NavOffer;
import com.mezzi.carsnav.Entity.Subscription;
import com.mezzi.carsnav.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    List<Subscription> findByNavOffer(NavOffer navOffer);
    List<Subscription> findByNavOfferAndSubscriberName(NavOffer navOffer, String username);
    List<Subscription> findBySubscriberName(String subscriberName);

    @Query("SELECT DATE(s.subscriptionDate), COUNT(s) FROM Subscription s GROUP BY DATE(s.subscriptionDate) ORDER BY DATE(s.subscriptionDate) ASC")
    List<Object[]> countSubscriptionsByDate();

    // Custom query to fetch subscriptions by companyId, which is related to NavOffer -> Company
    List<Subscription> findByNavOfferCompanyId(Long companyId);

}
