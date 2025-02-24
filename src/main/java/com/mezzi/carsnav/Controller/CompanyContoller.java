package com.mezzi.carsnav.Controller;


import com.mezzi.carsnav.Entity.Company;
import com.mezzi.carsnav.Entity.User;
import com.mezzi.carsnav.Service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CompanyContoller {

    @Autowired
    CompanyService companyService;


    @PostMapping("/editcompany/{id}")
    public String editUser(@PathVariable("id") Long id, @ModelAttribute("company") Company company) {
        if (!id.equals(company.getId())) {
            throw new IllegalArgumentException("User ID mismatch!");
        }
        companyService.updatecompany(company);
        return "redirect:/list_company";
    }

    @GetMapping("/editcompany/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Company company = companyService.findById(id);
        if (company == null) {
            return "redirect:/list_company";
        }
        model.addAttribute("company", company);
                return "edit_company";
    }



    @GetMapping("/list_company")

    public String listcompany(@RequestParam(defaultValue = "1") int page,
                            @RequestParam(defaultValue = "10") int size,
                            Model model) {

        Page<Company> requestPage = companyService.getAll(page, size);


        model.addAttribute("companies", requestPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", requestPage.getTotalPages());

        return "list_company";
    }

    @PostMapping("/deletecompany/{id}")
    public String deleteCompany(@PathVariable("id") Long id) {
        companyService.deleteCompany(id);
        return "redirect:/list_company";
    }


}
