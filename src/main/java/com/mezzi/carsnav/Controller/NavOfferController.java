package com.mezzi.carsnav.Controller;

import com.mezzi.carsnav.Entity.City;
import com.mezzi.carsnav.Entity.NavOffer;
import com.mezzi.carsnav.Entity.Request;
import com.mezzi.carsnav.Service.CityService;
import com.mezzi.carsnav.Service.NavOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class NavOfferController {




    @Autowired
    private NavOfferService navOfferService;


    @GetMapping("/offer-consultation")
    public String getAvailableOffers(Model model) {
        model.addAttribute("allAvailableOffers", navOfferService.getAllAvailableOffers());
        return "offer-consultation";
    }


    @PostMapping("/add-offer")
    public String addOffer(@ModelAttribute NavOffer navOffer) {

        NavOffer savedOffer = navOfferService.addNavOffer(navOffer);

        return "dashboard";


    }


}