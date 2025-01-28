package com.mezzi.carsnav.Controller;

import com.mezzi.carsnav.Entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class DashboardController {



    @GetMapping("/dashboard")
    public String showDashboard(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            return "redirect:/login";
        }
        model.addAttribute("username", user.getUsername());

        switch (user.getRole()) {
            case "admin":
                return "admin_dashboard";
            case "admin_company":
                return "admin_company_dashboard";
            default:
                return "user_dashboard";
        }
    }
}