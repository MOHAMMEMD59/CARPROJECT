package com.mezzi.carsnav.Controller;


import com.mezzi.carsnav.Entity.Subscription;
import com.mezzi.carsnav.Entity.User;
import com.mezzi.carsnav.Service.SubscriptionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
    @RequestMapping("/subscriptions")
    public class SubscriptionController {

        @Autowired
        private SubscriptionService subscriptionService;

        @GetMapping("/my-subscriptions")
        public String mySubscriptions(Model model, HttpSession session) {
            User loggedInUser = (User) session.getAttribute("loggedInUser");
            if (loggedInUser == null) {
                return "redirect:/login"; // Redirect if not logged in
            }
            String username = loggedInUser.getUsername();
            List<Subscription> userSubscriptions = subscriptionService.getSubscriptionsByUser(username);
            model.addAttribute("subscriptions", userSubscriptions);
            return "user_subscription";
        }

        @GetMapping("/cancel")
        public String cancelSubscription(@RequestParam("id") Long subscriptionId, HttpSession session) {
            User loggedInUser = (User) session.getAttribute("loggedInUser");
            if (loggedInUser == null) {
                return "redirect:/login"; // Redirect if not logged in
            }

            subscriptionService.cancelSubscription(subscriptionId, loggedInUser);
            return "redirect:/subscriptions/my-subscriptions";
        }

    }


