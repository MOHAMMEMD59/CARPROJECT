package com.mezzi.carsnav.Service;

import com.mezzi.carsnav.Entity.NavOffer;
import com.mezzi.carsnav.Entity.Subscription;
import com.mezzi.carsnav.Entity.User;
import com.mezzi.carsnav.Repository.NavOfferRepository;
import com.mezzi.carsnav.Repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private NavOfferRepository navOfferRepository;

    public String subscribeToOffer(Long offerId, String subscriberName, String subscriberEmail) {
        NavOffer navOffer = navOfferRepository.getById(offerId);
        if (navOffer!=null) {

            List<Subscription> res= subscriptionRepository.findByNavOfferAndSubscriberName(navOfferRepository.getById(offerId),subscriberName);
            if(!res.isEmpty())
                return  "you are already subscribed to this offer";

            if (navOffer.hasAvailableSeats()) {
                Subscription subscription = new Subscription(navOffer, subscriberName, subscriberEmail);
                subscriptionRepository.save(subscription);
                navOffer.incrementSubscribers();
                navOffer.setAvailableSeats(navOffer.getAvailableSeats() - 1);
                navOfferRepository.save(navOffer);
                return "offer added successfully";
            }
        }

        return "sets not available";
    }


    public List<Subscription> getSubscriptionsByUser(String subscriberName) {

        return subscriptionRepository.findBySubscriberName(subscriberName);
    }

    public SubscriptionService(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    public Map<LocalDate, Long> getSubscriptionsByDate() {

        List<Subscription> subscriptions = subscriptionRepository.findAll();


        return subscriptions.stream()
                .collect(Collectors.groupingBy(subscription -> subscription.getSubscriptionDate().toLocalDate(),
                        Collectors.counting()));
    }


    public List<Subscription> getSubscriptionsByCompanyId(Long companyId) {
        return subscriptionRepository.findByNavOfferCompanyId(companyId);
    }


    public void cancelSubscription(Long subscriptionId, User user) {
        Subscription subscription = subscriptionRepository.findById(subscriptionId).orElse(null);

        if (subscription != null && subscription.getSubscriberName().equals(user.getUsername())) {
            NavOffer navOffer = subscription.getNavOffer();

            if (navOffer != null) {
                navOffer.setAvailableSeats(navOffer.getAvailableSeats() + 1);
                navOffer.setCurrentSubscribers(navOffer.getCurrentSubscribers() - 1);
                navOfferRepository.save(navOffer);
            }

            subscriptionRepository.deleteById(subscriptionId);
        }
    }

}
