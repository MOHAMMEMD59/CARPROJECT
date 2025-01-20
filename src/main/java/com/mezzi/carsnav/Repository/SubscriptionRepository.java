package com.mezzi.carsnav.Repository;

import com.mezzi.carsnav.Entity.NavOffer;
import com.mezzi.carsnav.Entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    List<Subscription> findByNavOffer(NavOffer navOffer);
}
