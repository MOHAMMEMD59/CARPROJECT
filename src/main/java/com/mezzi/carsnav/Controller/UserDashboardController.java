package com.mezzi.carsnav.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserDashboardController {

    @GetMapping("/offer-consultation")
    public String showOfferConsultationPage() {
        // This will serve the 'offer-consultation.html' file
        return "offer-consultation";
    }
}
