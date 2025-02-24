package com.ali.companyms.company.impl;

import com.ali.companyms.company.Company;
import com.ali.companyms.company.CompanyService;
import com.ali.companyms.company.clients.ReviewClient;
import com.ali.companyms.company.repository.CompanyRepository;
import com.ali.companyms.company.dto.ReviewMessage;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    ReviewClient reviewClient;


    public CompanyServiceImpl(
            CompanyRepository companyRepository,
            ReviewClient reviewClient
    ) {
        this.companyRepository = companyRepository;
        this.reviewClient = reviewClient;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public boolean updateCompany(Long id, Company companyUpdated) {
        Optional<Company> companyOptional = companyRepository.findById(id);

        if (companyOptional.isPresent()) {
            Company company = companyOptional.get();

            company.setName(companyUpdated.getName());
            company.setDescription(companyUpdated.getDescription());

//            company.setJobs(companyUpdated.getJobs());

            companyRepository.save(company);

            return true;
        }

        return false;
    }

    @Override
    public void createCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public boolean deleteCompany(Long id) {
        if (companyRepository.existsById(id)) {
            companyRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    @Override
    public void updateCompanyRating(ReviewMessage reviewMessage) {
        Company company =companyRepository.findById(reviewMessage.getCompanyId())
                .orElseThrow(() -> new NotFoundException("Company not found " + reviewMessage.getCompanyId()));

        Double averageRating = reviewClient.getAverageRatingForCompany(reviewMessage.getCompanyId());
        company.setRating(averageRating);

        companyRepository.save(company);
    }

}
