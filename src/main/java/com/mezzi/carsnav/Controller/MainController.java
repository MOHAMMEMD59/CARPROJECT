//package com.mezzi.carsnav.Controller;
//
//import com.mezzi.carsnav.Entity.NavOffer;
//import com.mezzi.carsnav.Service.NavOfferService;
//import jakarta.servlet.http.HttpSession;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//@Controller
//public class MainController {
//    @Autowired
//    private NavOfferService navOfferService;
//    // Example of a protected page, "somePage"
//    @GetMapping("/offer-consultation")
//    public String somePage(HttpSession session) {
//                if (session.getAttribute("user") == null) {
//
//                    return "redirect:/login";
//        }
//
//        return "offer-consultation";
//    }
//
//    @GetMapping("/offer_consultation_admin")
//    public String showAllRequests(@RequestParam(defaultValue = "1") int page,
//                                  @RequestParam(defaultValue = "5") int size,
//                                  Model model,HttpSession session) {
//        if (session.getAttribute("user") == null) {
//
//            return "redirect:/login";
//        }
//        Page<NavOffer> requestPage = navOfferService.getAll(page, size);
//
//        model.addAttribute("nav_offer", requestPage.getContent());
//        model.addAttribute("currentPage", page);
//        model.addAttribute("totalPages", requestPage.getTotalPages());
//
//        return "offer_consultation_admin";
//    }
//    // Other protected pages can be added similarly
//}