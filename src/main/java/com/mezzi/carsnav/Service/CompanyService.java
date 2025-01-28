package com.mezzi.carsnav.Service;

import com.mezzi.carsnav.Entity.Company;
import com.mezzi.carsnav.Repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public Company getCompanyById(Long companyId) {
        return companyRepository.findById(companyId)
                .orElseThrow(() -> new RuntimeException("Company not found"));
    }

    // Assuming you are using Spring Security or some authentication method to get the current logged-in user
    public Company getCompanyForCurrentUser() {
        // For example, fetching the company for the logged-in user by their username
        String username = "currentLoggedInUsername"; // Replace with actual user retrieval logic
        Optional<Company> company = companyRepository.findByName(username);

        return company.orElseThrow(() -> new RuntimeException("Company not found for the user"));
    }

    public  List<Company> getAll() {
        List<Company> companies = companyRepository.findAll();
        return companies;
    }

}
