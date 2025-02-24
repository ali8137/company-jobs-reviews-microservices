package com.ali.companyms.company.repository;

import com.ali.companyms.company.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company,Long> {

}
