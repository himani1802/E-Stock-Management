package com.cts.StockManagement.service;

import com.cts.StockManagement.exception.CompanyFieldMustNotBeEmptyException;
import com.cts.StockManagement.exception.CompanyNotFoundException;
import com.cts.StockManagement.exception.CompanyTurnoverMinimumRequiredValidation;
import com.cts.StockManagement.model.Company;
import com.cts.StockManagement.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public List<Company> getAllCompany() {
        List<Company> companyList = companyRepository.findAll();
            return companyList;
    }

    @Override
    public Company getCompanyByCompanyCode(int companyCode) {
        return companyRepository.getCompanyByCode(companyCode);
    }

    @Override
    public Company addCompany(Company company) {
            validateCompanyBeforeSave(company);
            return companyRepository.save(company);
    }

    @Override
    public boolean updateCompanyByCompanyCode(Company company) {
        Company companyObj = companyRepository.getCompanyByCode(company.getCompanyCode());
        if (companyObj != null) {
            companyObj.setCompanyCode(company.getCompanyCode());
            companyObj.setCompanyCEO(company.getCompanyCEO());
            companyObj.setCompanyTurnover(company.getCompanyTurnover());
            companyObj.setCompanyName(company.getCompanyName());
            companyObj.setCompanyWebsite(company.getCompanyWebsite());
            companyObj.setStockExchange(company.getStockExchange());
//            companyObj.setStockList(company.getStockList());
            validateCompanyBeforeSave(company);
            Company comp = companyRepository.save(companyObj);
            return true;
        } else {
            throw new CompanyNotFoundException();
        }
    }
    @Override
    public boolean deleteCompanyByCompanyCode(int companyCode) {
        companyRepository.deleteById(companyCode);
        return true;
    }

    private void validateCompanyBeforeSave(Company company) {
        // company Turnover validation
        if (company.getCompanyTurnover() < 100000000) {
            throw new CompanyTurnoverMinimumRequiredValidation();
        }
        // following for field null check

        if (company.getCompanyCode()== 0) {
            throw new CompanyFieldMustNotBeEmptyException("Company Code");
        }

        if (company.getCompanyCEO().isBlank()) {
            throw new CompanyFieldMustNotBeEmptyException("Company CEO");
        }

        if (company.getCompanyName().isBlank()) {
            throw new CompanyFieldMustNotBeEmptyException("Company Name");
        }

        if (company.getCompanyWebsite().isBlank()) {
            throw new CompanyFieldMustNotBeEmptyException("Company website");
        }

        if (company.getStockExchange() == 0) {
            throw new CompanyFieldMustNotBeEmptyException("Stock Exchange");
        }
    }
}
