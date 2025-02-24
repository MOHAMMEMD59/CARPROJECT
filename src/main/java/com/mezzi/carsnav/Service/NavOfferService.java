package com.mezzi.carsnav.Service;

import com.mezzi.carsnav.Entity.NavOffer;
import com.mezzi.carsnav.Repository.NavOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NavOfferService {

    @Autowired
    private NavOfferRepository navOfferRepository;

    public List<NavOffer> getAllAvailableOffers() {
        return navOfferRepository.findAllAvailableOffers();

    }

    public Page<NavOffer> getAll(int page, int size){
        Pageable pageable = PageRequest.of(page-1, size);
        return navOfferRepository.findAll(pageable);

    }

    public List<NavOffer> findNavOffers(String departureCity, String arrivalCity) {
        // Use the repository to find offers based on the cities
        return navOfferRepository.findByDepartureCityAndArrivalCity(departureCity, arrivalCity);
    }

    public NavOffer addNavOffer(NavOffer navOffer) {
        navOffer.setAvailableSeats(navOffer.getMaxSubscribers());
        return navOfferRepository.save(navOffer);


    }

    public boolean isOfferExists(NavOffer navOffer) {
        List<NavOffer> availableOffers = getAllAvailableOffers();


        for (NavOffer existingOffer : availableOffers) {
            if (existingOffer.getDepartureCity().equals(navOffer.getDepartureCity()) &&
                    existingOffer.getArrivalCity().equals(navOffer.getArrivalCity()) &&
                    existingOffer.getCompany().equals(navOffer.getCompany())&&
                    existingOffer.hasAvailableSeats()     ) {
                return true;
            }
        }
        return false;
    }

    public  Map<String, Long> getMostPopularOffers() {
        List<Object[]> resultoff =navOfferRepository.findMostPopularOffers();

        Map<String, Long> subscriptionbytraject = new HashMap<>();
        for (Object[] row : resultoff) {
            String traject = (String) row[0];
            Long count = (Long) row[1];
            subscriptionbytraject.put(traject, count);
        }
        return subscriptionbytraject;
    }


    public Map<String, Long> getOffersCountByCompany() {
        // Assuming you have a repository for NavOffer
        List<Object[]> result = navOfferRepository.countOffersByCompany();

        // Process the results into a Map (Company name -> Number of offers)
        Map<String, Long> offersByCompany = new HashMap<>();
        for (Object[] row : result) {
            String companyName = (String) row[0];
            Long count = (Long) row[1];
            offersByCompany.put(companyName, count);
        }

        return offersByCompany;
    }

}