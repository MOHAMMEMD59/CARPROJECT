package com.mezzi.carsnav.Service;

import com.mezzi.carsnav.Entity.Company;
import com.mezzi.carsnav.Entity.User;
import com.mezzi.carsnav.Repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;


    public  void deleteCompany(Long Id) {
        companyRepository.deleteById(Id);
    }


    public Company getCompanyById(Long companyId) {
        return companyRepository.findById(companyId)
                .orElseThrow(() -> new RuntimeException("Company not found"));
    }


    public Company findById(Long companyId) {
        return companyRepository.findById(companyId).orElse(null);
    }

    public Company getCompanyForCurrentUser() {

        String username = "currentLoggedInUsername";
        Optional<Company> company = companyRepository.findByName(username);

        return company.orElseThrow(() -> new RuntimeException("Company not found for the user"));
    }


    public List<Company> getAll() {
        return companyRepository.findAll();  // This will return a List<Company>

         }

         public Page<Company> getAll(int page, int size) {
            Pageable pageable = PageRequest.of(page-1, size);
            return companyRepository.findAll(pageable);
        }


    public void updatecompany(Company company) {
        // Check if the user exists
        if (company.getId() == null) {
            throw new IllegalArgumentException("User ID cannot be null for update");
        }

        Company existingCompany = companyRepository.findById(company.getId())
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + company.getId()));

        existingCompany.setName(company.getName());

        companyRepository.save(existingCompany);
    }

}
