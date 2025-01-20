package com.mezzi.carsnav.Service;



import com.mezzi.carsnav.Entity.NavOffer;
import com.mezzi.carsnav.Entity.Subscription;
import com.mezzi.carsnav.Repository.NavOfferRepository;
import com.mezzi.carsnav.Repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private NavOfferRepository navOfferRepository;

    // Method to subscribe to an offer
    public boolean subscribeToOffer(Long offerId, String subscriberName, String subscriberEmail) {
        Optional<NavOffer> navOfferOpt = navOfferRepository.findById(offerId);

        if (navOfferOpt.isPresent()) {
            NavOffer navOffer = navOfferOpt.get();

            // Check if the offer has available seats
            if (navOffer.hasAvailableSeats()) {
                Subscription subscription = new Subscription(navOffer, subscriberName, subscriberEmail);

                // Save the subscription
                subscriptionRepository.save(subscription);

                // Increment the current subscribers count in the NavOffer
                navOffer.incrementSubscribers();
                navOfferRepository.save(navOffer);

                return true; // Subscription successful
            }
        }

        return false; // No available seats or invalid offer
    }

    // Optionally: Add more methods for deactivating, canceling, or querying subscriptions
}
