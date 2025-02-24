package com.mezzi.carsnav.Repository;

import com.mezzi.carsnav.Entity.Company;
import com.mezzi.carsnav.Entity.Request;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {


    Optional<Company> findByName(String name);
    Page<Company> findAll(Pageable pageable);




}
