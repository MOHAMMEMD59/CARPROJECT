package com.mezzi.carsnav.Service;



import com.mezzi.carsnav.Entity.NavOffer;

import com.mezzi.carsnav.Repository.NavOfferRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class NavOfferService {

    @Autowired
    private NavOfferRepository navOfferRepository;

    public List<NavOffer> getAllAvailableOffers() {
        return navOfferRepository.findAll();
    }
}