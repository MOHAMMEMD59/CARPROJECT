package com.mezzi.carsnav.Repository;


import com.mezzi.carsnav.Entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    // Custom query methods (if needed) can be added here
}


