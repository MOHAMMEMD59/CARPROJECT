package com.mezzi.carsnav.Controller;

import com.mezzi.carsnav.Entity.City;
import com.mezzi.carsnav.Entity.Company;
import com.mezzi.carsnav.Service.CityService;
import com.mezzi.carsnav.Service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class CompanyDashboardController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private CityService cityService;


    @GetMapping("/company-add-offer")
    public String showAddOfferPage(Model model) {
        List< Company> company = companyService.getAll();
        List<City> cities = cityService.getAllCities();
        model.addAttribute("cities", cities);
        model.addAttribute("company", company);

        return "company_add_offer";
    }

    @GetMapping("/company-dashboard")
    public String showCompanyDashboard(Model model) {
        // Assuming you have the company available for the current user (e.g., by username or through authentication)
        Company company = companyService.getCompanyForCurrentUser();

        model.addAttribute("company", company);
        return "company-dashboard"; // The HTML page you want to show for the company dashboard
    }



    @GetMapping("/company/{companyId}")
    public String showCompanyDetails(@PathVariable Long companyId, Model model) {
        Company company = companyService.getCompanyById(companyId);

        model.addAttribute("company", company);
        return "company-details"; // A page showing detailed information about the company
    }



}
