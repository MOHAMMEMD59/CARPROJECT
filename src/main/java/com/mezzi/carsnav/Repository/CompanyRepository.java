package com.mezzi.carsnav.Repository;

import com.mezzi.carsnav.Entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    // Custom method to find a company by name (or any other attribute as needed)
    Optional<Company> findByName(String name);
    // Additional queries can be added as needed.
}
