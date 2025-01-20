package com.mezzi.carsnav.Controller;

import com.mezzi.carsnav.Entity.NavOffer;

import com.mezzi.carsnav.Service.NavOfferService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/navOffers")
public class NavOfferController {

    @Autowired
    private NavOfferService navOfferService;

    @GetMapping("/available")
    public List<NavOffer> getAvailableOffers() {
        return navOfferService.getAllAvailableOffers();
    }
}
