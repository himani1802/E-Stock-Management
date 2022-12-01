package com.cts.StockManagement.company;

import com.cts.StockManagement.controller.CompanyController;
import com.cts.StockManagement.controller.StockController;
import com.cts.StockManagement.model.Company;
import com.cts.StockManagement.service.CompanyServiceImpl;
import com.cts.StockManagement.service.StockServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@AutoConfigureMockMvc
@SpringBootTest
public class CompanyControllerTest {

    @Mock
    private CompanyServiceImpl companyService;

    @Mock
    private StockServiceImpl stockService;

    @InjectMocks
    private CompanyController companyController;

    @InjectMocks
    private StockController stockController;
    @Autowired
    private MockMvc mockmvc;

    private List<Company> companyList = new ArrayList();

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        mockmvc = MockMvcBuilders.standaloneSetup(companyController).build();
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
        when(companyService.addCompany(any())).thenReturn(company);
        Company comp  = companyService.addCompany(company);
        assertEquals(company,comp);
        mockmvc.perform(MockMvcRequestBuilders.post("/api/v1.0/market/company/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(company)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
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
        when(companyService.addCompany(any())).thenReturn(null);
        Company comp  = companyService.addCompany(company);
        assertNull(comp);
        mockmvc.perform(MockMvcRequestBuilders.post("/api/v1.0/market/company/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(company)))
                .andExpect(MockMvcResultMatchers.status().isConflict());
    }

    @Test
    public void getCompanyByCompanyCodeSuccess()throws Exception{
        Company company = new Company();
        company.setCompanyCode(101);
        company.setCompanyCEO("abc");
        company.setCompanyWebsite("www.cisco.com");
        company.setCompanyName("Cisco");
        company.setCompanyTurnover(250000000);
        company.setStockExchange(200000);
        companyList.add(company);
        when(companyService.getCompanyByCompanyCode(company.getCompanyCode())).thenReturn(company);
        List<Company> c1 = List.of(companyService.getCompanyByCompanyCode(company.getCompanyCode()));
        assertEquals(companyList,c1);
    }


    @Test
    public void getCompanyByCompanyCodeFailure()throws Exception{
        Company company= new Company();
                companyList.clear();
        when(companyService.getCompanyByCompanyCode(company.getCompanyCode())).thenReturn(company);

        assertEquals(0,companyList.size());

        mockmvc.perform(MockMvcRequestBuilders.get("/api/v1.0/market/company/info/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
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
        when(companyService.getAllCompany()).thenReturn(companyList);
        List<Company> companyList1 = companyService.getAllCompany();
        assertEquals(companyList, companyList1);
        mockmvc.perform(MockMvcRequestBuilders.get("/api/v1.0/market/company/getAll")
                .contentType(MediaType.APPLICATION_JSON));
    }

//    @Test
//    public void getAllCompanyFailure() throws Exception {
//        companyList.clear();
//        when(companyService.getAllCompany()).thenReturn(companyList);
//
//        assertEquals(0,companyList.size());
//
//        mockmvc.perform(MockMvcRequestBuilders.get("/api/v1.0/market/company/getAll")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isNoContent());
//
//    }


}
