package com.cts.StockManagement.company;

import com.cts.StockManagement.model.Company;
import com.cts.StockManagement.repository.CompanyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
@AutoConfigureMockMvc
public class CompanyRepositoryTest {

    @Autowired
    private CompanyRepository companyRepository;
    Company company = new Company();

    @BeforeEach
    public void init() {
       company.setCompanyCode(1);
       company.setCompanyCEO("abc");
       company.setCompanyName("Cisco");
       company.setCompanyTurnover(1200000);
       company.setCompanyWebsite("www.cisco.com");
       company.setStockExchange(200000);
    }

    @Test
    public void saveCompanySuccess() throws Exception {
        Company c1 = null;
        companyRepository.save(company);
        c1= companyRepository.getCompanyByCode(company.getCompanyCode());
        assertEquals(company.getCompanyCode(), c1.getCompanyCode());
    }

    @Test
    public void saveCompanyFailure() throws Exception {
        Company u1 = null;
        if(companyRepository.findAll().toString().isEmpty()) {
            companyRepository.save(company);
            u1 = companyRepository.findById(company.getCompanyCode()).get();
        }
        assertNull(u1);
    }
}
