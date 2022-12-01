package com.cts.StockManagement.company;

import com.cts.StockManagement.model.Company;
import com.cts.StockManagement.repository.CompanyRepository;
import com.cts.StockManagement.service.CompanyServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@AutoConfigureMockMvc
@SpringBootTest
public class CompanyServiceTest {


    @Mock
    private CompanyRepository companyRepository;
    @InjectMocks
    private CompanyServiceImpl companyService;
    @Autowired
    private MockMvc mockmvc;

    private List<Company> companyList = new ArrayList();

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        mockmvc = MockMvcBuilders.standaloneSetup(companyService).build();
    }
    @Test
    public void getAllCompanySuccess() throws Exception {
        Company company = new Company();
        company.setCompanyCode(101);
        company.setCompanyCEO("abc");
        company.setCompanyWebsite("www.cisco.com");
        company.setCompanyName("Cisco");
        company.setCompanyTurnover(250000000);
        company.setStockExchange(200000);
        companyList.add(company);
        when(companyRepository.findAll()).thenReturn(companyList);
        List<Company> companyList = companyService.getAllCompany();
        assertEquals(companyList, companyList);
    }

    @Test
    public void getAllCompanyFailure() throws Exception {
        when(companyRepository.findAll()).thenReturn(null);
        List<Company> uList = companyService.getAllCompany();
        assertNull(uList);
    }


    @Test
    public void addCompanySuccess() throws Exception {
        Company company  = new Company();
        company.setCompanyCode(101);
        company.setCompanyCEO("abc");
        company.setCompanyWebsite("www.cisco.com");
        company.setCompanyName("Cisco");
        company.setCompanyTurnover(250000000);
        company.setStockExchange(200000);

        companyList.add(company);
        when(companyRepository.save(any())).thenReturn(company);
        Company comp  = companyService.addCompany(company);
        assertEquals(company,comp);
    }

    @Test
    public void addCompanyFailure() throws Exception {
        Company company  = new Company();
        company.setCompanyCode(101);
        company.setCompanyCEO("abc");
        company.setCompanyWebsite("www.cisco.com");
        company.setCompanyName("Cisco");
        company.setCompanyTurnover(250000000);
        company.setStockExchange(200000);
        companyList.add(company);
        when(companyRepository.save(any())).thenReturn(null);
        Company comp  = companyService.addCompany(company);
        assertNull(comp);
    }
    @Test
    public void getCompanyByCompanyCodeSuccess() throws Exception{

        Company company = new Company();
        company.setCompanyCode(101);
        company.setCompanyCEO("abc");
        company.setCompanyWebsite("www.cisco.com");
        company.setCompanyName("Cisco");
        company.setCompanyTurnover(250000000);
        company.setStockExchange(200000);
        companyList.add(company);
        when(companyRepository.getCompanyByCode(company.getCompanyCode())).thenReturn(company);
        List<Company> comp = List.of(companyService.getCompanyByCompanyCode(company.getCompanyCode()));
        assertEquals(companyList, comp);
    }

    @Test
    public void getCompanyByCompanyCodeFailure() throws Exception {
        Company company= new Company();
        when(companyRepository.getCompanyByCode(company.getCompanyCode())).thenReturn(null);
        Company uList = companyService.getCompanyByCompanyCode(company.getCompanyCode());
        assertNull(uList);
    }

//    @Test
//    public void deleteCompanyByCompanyCodeSuccess()throws Exception{
//        Company company = new Company();
//        company.setCompanyCode(101);
//        company.setCompanyCEO("abc");
//        company.setCompanyWebsite("www.cisco.com");
//        company.setCompanyName("Cisco");
//        company.setCompanyTurnover(250000000);
//        company.setStockExchange(200000);
//        companyList.add(company);
//        when(companyRepository.deleteCompanyByCode(company.getCompanyCode())).thenReturn(true);
//        boolean s1= companyService.deleteCompanyByCompanyCode(company.getCompanyCode());
//        assertEquals(true, s1);
//    }
}
