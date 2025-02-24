package com.mezzi.carsnav.Repository;


import com.mezzi.carsnav.Entity.Request;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    Page<Request> findAll(Pageable pageable);

    Page<Request> findByuserId(long userId, Pageable pageable);

    Page<Request> findBytraiter(int traiter, Pageable pageable);



}


