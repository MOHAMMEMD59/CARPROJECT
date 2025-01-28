package com.mezzi.carsnav.Controller;

import com.mezzi.carsnav.Entity.City;
import com.mezzi.carsnav.Entity.NavOffer;
import com.mezzi.carsnav.Entity.Request;
import com.mezzi.carsnav.Entity.User;
import com.mezzi.carsnav.Service.CityService;
import com.mezzi.carsnav.Service.RequestService;
import com.mezzi.carsnav.Service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class RequestController {

    @Autowired
    private CityService cityService;

    @Autowired
    private RequestService requestService;

    @Autowired
    private UserService userService; // To fetch user details by ID

    // Display the add-request form
    @GetMapping("/add-request")
    public String showAddRequestForm(Model model) {
        List<City> cities = cityService.getAllCities();
        model.addAttribute("cities", cities);
        model.addAttribute("request", new Request()); // Bind a new Request object for the form


        return "add-request"; // HTML template name
    }

    // Handle form submission
    @Autowired
    private HttpSession session;

    @PostMapping("/add-request")
    public String submitAddRequestForm(@ModelAttribute Request request) {
        // Get the authenticated user from the session
        User authenticatedUser = getAuthenticatedUser(session);

        // Associate the user with the request
        request.setUser(authenticatedUser);

        request.setUser(userService.findById(authenticatedUser.getId()));

        // Save the request
        requestService.saveRequest(request);

        // Redirect to the dashboard or success page

        return "redirect:/request_consultation";
    }

    private User getAuthenticatedUser(HttpSession session) {
        User authenticatedUser = (User) session.getAttribute("loggedInUser");

        if (authenticatedUser == null) {
            throw new IllegalStateException("No authenticated user in session");
        }

        return authenticatedUser;
    }


    @GetMapping("/request_consultation")
    public String showAllRequests(Model model) {
        List<Request> requests = requestService.getAllRequests();
        model.addAttribute("requests", requests);
        return "request_consultation";
    }





}
