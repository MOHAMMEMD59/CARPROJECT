package com.mezzi.carsnav.Repository;



import com.mezzi.carsnav.Entity.NavOffer;
import com.mezzi.carsnav.Entity.Request;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface NavOfferRepository extends JpaRepository<NavOffer, Long> {
    @Query("SELECT n FROM NavOffer n WHERE n.availableSeats > 0 AND n.endDate >= CURRENT_DATE")
    List<NavOffer> findAllAvailableOffers();

    Page<NavOffer> findAll(Pageable pageable);

    List<NavOffer> findByDepartureCityAndArrivalCity(String departureCity, String arrivalCity);

//    // Fetch offers sorted by most subscribers
//    @Query("SELECT n FROM NavOffer n ORDER BY n.currentSubscribers DESC")
//    List<NavOffer> findMostPopularOffers();

    @Query("SELECT CONCAT(n.departureCity, ' → ', n.arrivalCity) AS trajectory, SUM(n.currentSubscribers) AS totalSubscribers " +
            "FROM NavOffer n " +
            "GROUP BY n.departureCity, n.arrivalCity " +
            "ORDER BY totalSubscribers Asc")
    List<Object[]> findMostPopularOffers();


    // Add this method to your NavOfferRepository class
    @Query("SELECT company.name, COUNT(o) FROM NavOffer o JOIN o.company company GROUP BY company.name")
    List<Object[]> countOffersByCompany();


}

