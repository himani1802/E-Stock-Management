package com.cts.StockManagement.service;

import com.cts.StockManagement.model.Company;

import java.util.List;
import java.util.Optional;

public interface CompanyService {

    List<Company> getAllCompany();
    Company getCompanyByCompanyCode(int companyCode);

    Company addCompany(Company company);

    boolean updateCompanyByCompanyCode(Company company);

    boolean deleteCompanyByCompanyCode(int companyCode);


}
