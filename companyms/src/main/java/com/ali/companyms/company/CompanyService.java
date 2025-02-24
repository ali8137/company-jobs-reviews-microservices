package com.ali.companyms.company;

import com.ali.companyms.company.dto.ReviewMessage;

import java.util.List;

//@Service
////@Service annotation should not be added here but should instead be added above the implementation of this interface, so that the implementation is taken as a bean by spring boot
public interface CompanyService {

    List<Company> getAllCompanies();

    boolean updateCompany(Long id, Company companyUpdated);

    void createCompany(Company company);

    boolean deleteCompany(Long id);

    Company getCompanyById(Long id);

    void updateCompanyRating(ReviewMessage reviewMessage);
}
