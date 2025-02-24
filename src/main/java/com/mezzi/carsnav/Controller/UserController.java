package com.mezzi.carsnav.Controller;


import com.mezzi.carsnav.Entity.User;
import com.mezzi.carsnav.Service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/login";
    }


    @PostMapping("/edit/{id}")
    public String editUser(@PathVariable("id") Long id, @ModelAttribute("user") User user) {
        if (!id.equals(user.getId())) {
            throw new IllegalArgumentException("User ID mismatch!");
        }
        userService.update(user);
        return "redirect:/list_user";
    }

    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/list_user";
    }



    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        User user = userService.findById(id);
        if (user == null) {
            return "redirect:/list_user";
        }
        model.addAttribute("user", user);
        return "edit_user";
    }

    @GetMapping("/admin_statistique")
    public String adminStatistique() {
        return "admin_statistique"; // This corresponds to admin_statistique.html
    }

    @GetMapping("/home")
    public String home(HttpSession session, Model model) {
        User authenticatedUser = (User) session.getAttribute("authenticatedUser");
        if (authenticatedUser == null) {
            return "redirect:/login"; // Redirect to login if not authenticated
        }

        model.addAttribute("user", authenticatedUser);
        return "home"; // View: home.html
    }


    @GetMapping("/list_user")


    public String listUsers(@RequestParam(defaultValue = "1") int page,
                                  @RequestParam(defaultValue = "5") int size,
                                  Model model) {

        Page<User> requestPage = userService.getRequests(page, size);


        model.addAttribute("users", requestPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", requestPage.getTotalPages());

        return "list_user";
    }


}