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
import org.springframework.data.domain.Page;
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


    @GetMapping("/add-request")
    public String showAddRequestForm(Model model) {
        List<City> cities = cityService.getAllCities();
        model.addAttribute("cities", cities);
        model.addAttribute("request", new Request()); // Bind a new Request object for the form


        return "add-request";
    }


    @Autowired
    private HttpSession session;

    @PostMapping("/add-request")
    public String submitAddRequestForm(@ModelAttribute Request request,Model model) {

        User authenticatedUser = getAuthenticatedUser(session);

        request.setUser(authenticatedUser);

        request.setUser(userService.findById(authenticatedUser.getId()));

        requestService.saveRequest(request);

        model.addAttribute("successMessage", "Votre demander ajouter avec sucees !!");

        return "redirect:/user_request_consultation";
    }

    @GetMapping("/traiter-request")
    public String traiterrequest(@RequestParam("requestId") long requestId,Model model) {

        requestService.updaterequeststatus(requestId,1);
        return "redirect:/request_consultation";
    }


    @GetMapping("/cancel-request")
    public String cancelrrequest(@RequestParam("requestId") long requestId,Model model) {

        requestService.updaterequeststatus(requestId,2);
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
    public String showAllRequests(@RequestParam(defaultValue = "1") int page,
                                  @RequestParam(defaultValue = "10") int size,
                                  Model model,HttpSession session) {

        Page<Request> requestPage ;
        User user =(User)session.getAttribute("loggedInUser");
        if(user.getRole().equals("admin"))
            requestPage=requestService.getRequests(page, size);
        else
         requestPage = requestService.getRequestsByStatus(page, size,0);


        model.addAttribute("requests", requestPage.getContent()); // List of requests
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", requestPage.getTotalPages());

        return "request_consultation"; // Name of your JSP/Thymeleaf page
    }

    @GetMapping("/user_request_consultation")
    public String showAllRequestsuser(@RequestParam(defaultValue = "1") int page,
                                      @RequestParam(defaultValue = "10") int size,
                                      Model model, HttpSession session) {
        User authenticatedUser = (User) session.getAttribute("loggedInUser");
        if (authenticatedUser == null) {

            return "redirect:/login";
        }
        long userId = authenticatedUser.getId();
        System.out.println("Authenticated User ID: " + userId);
        Page<Request> requestPage = requestService.getRequestsuser(userId, page, size);
        model.addAttribute("requests", requestPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", requestPage.getTotalPages());
        return "user_request_consultation";
    }

    @GetMapping("/admin_list_request")
    public String showAllRequestsadmin(@RequestParam(defaultValue = "1") int page,
                                  @RequestParam(defaultValue = "10") int size,
                                  Model model) {
        Page<Request> requestPage = requestService.getRequests(page, size);


        model.addAttribute("requests", requestPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", requestPage.getTotalPages());

        return "admin_list_request";
    }





}
