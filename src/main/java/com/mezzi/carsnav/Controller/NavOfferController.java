package com.mezzi.carsnav.Controller;
import com.mezzi.carsnav.Entity.NavOffer;
import com.mezzi.carsnav.Entity.SearchCriteria;
import com.mezzi.carsnav.Entity.User;
import com.mezzi.carsnav.Service.NavOfferService;
import com.mezzi.carsnav.Service.SubscriptionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;


@Controller
public class NavOfferController {
    @Autowired
    private NavOfferService navOfferService;

    @Autowired
    private SubscriptionService subscriptionService;

    @GetMapping("/offer-consultation")
    public String getAvailableOffers(Model model) {
        model.addAttribute("allAvailableOffers", navOfferService.getAllAvailableOffers());
        return "offer-consultation";
    }

    @GetMapping("/offer_consultation_admin")
    public String showAllRequests(@RequestParam(defaultValue = "1") int page,
                                  @RequestParam(defaultValue = "5") int size,
                                  Model model) {
        Page<NavOffer> requestPage = navOfferService.getAll(page, size);

        model.addAttribute("nav_offer", requestPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", requestPage.getTotalPages());

        return "offer_consultation_admin";
    }
    @PostMapping("/add-offer")

    public String addOffer(@ModelAttribute NavOffer navOffer, RedirectAttributes redirectAttributes) {

        if (navOfferService.isOfferExists(navOffer)) {

            redirectAttributes.addFlashAttribute("result", "Cette offer navette deja existe , verifier votre liste Navette créer.");
            return "redirect:/company-add-offer";
        }


        navOfferService.addNavOffer(navOffer);
        redirectAttributes.addFlashAttribute("result", "Offre Navette ajouter avec succes!");
        return "redirect:/company-add-offer";
    }


//    public String addOffer(@ModelAttribute NavOffer navOffer,RedirectAttributes redirectAttributes) {
//        NavOffer savedOffer = navOfferService.addNavOffer(navOffer);
//              return "admin_company_dashboard";
//    }

    @GetMapping("/subscribe")
    public String subscribe(@RequestParam("offerId") Long offerId, HttpSession session, RedirectAttributes redirectAttributes) {
        User authenticatedUser = (User) session.getAttribute("loggedInUser");
        String result = subscriptionService.subscribeToOffer(offerId,authenticatedUser.getUsername(),authenticatedUser.getEmail());
        redirectAttributes.addFlashAttribute("result", result);
        return "redirect:/offer-consultation";
    }

    @GetMapping("/")
    public String searchOffers(@ModelAttribute("searchCriteria") SearchCriteria searchCriteria, Model model) {
        String departureCity = searchCriteria.getDepartureCity();
        String arrivalCity = searchCriteria.getArrivalCity();

        List<NavOffer> navOffers = navOfferService.findNavOffers(departureCity, arrivalCity);
    if(navOffers.toArray().length==0){
        navOffers=null;

    }

        model.addAttribute("navOffers", navOffers);

        return "index1";
    }


}