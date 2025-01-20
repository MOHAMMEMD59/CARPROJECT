package com.mezzi.carsnav.Repository;



import com.mezzi.carsnav.Entity.NavOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface NavOfferRepository extends JpaRepository<NavOffer, Long> {
    // Define any custom query methods here, if needed
}
