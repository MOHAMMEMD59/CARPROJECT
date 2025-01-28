package com.mezzi.carsnav.Repository;



import com.mezzi.carsnav.Entity.NavOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface NavOfferRepository extends JpaRepository<NavOffer, Long> {
    @Query("SELECT n FROM NavOffer n WHERE n.availableSeats > 0 AND n.endDate >= CURRENT_DATE")
    List<NavOffer> findAllAvailableOffers();
}
