package com.mezzi.carsnav.Repository;

import com.mezzi.carsnav.Entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    // Custom query methods can be added here if needed
}
