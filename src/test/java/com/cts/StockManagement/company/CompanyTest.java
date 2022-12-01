package com.cts.StockManagement.company;

import com.cts.StockManagement.model.Company;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CompanyTest {

    @Test
    public void testCompany() {
        Company companyObj = Mockito.mock(Company.class);

        companyObj.setCompanyCode(101);
        companyObj.setCompanyName("Cisco");
        companyObj.setCompanyCEO("abc");
        companyObj.setCompanyWebsite("www.cisco.com");
        companyObj.setCompanyTurnover(2500000);
        companyObj.setStockExchange(4000000);

        Integer mockCompanyCode = companyObj.getCompanyCode();
        String mockCompanyCEO= companyObj.getCompanyCEO();
        String mockCompanyWebsite= companyObj.getCompanyWebsite();
        String mockCompanyName= companyObj.getCompanyName();
        Integer mockStockExchange = companyObj.getStockExchange();
        Integer mockCompanyTurnover = companyObj.getCompanyTurnover();

        when(companyObj.getCompanyCode()).thenReturn(mockCompanyCode);
        when(companyObj.getCompanyCEO()).thenReturn(mockCompanyCEO);
        when(companyObj.getCompanyWebsite()).thenReturn(mockCompanyWebsite);
        when(companyObj.getCompanyName()).thenReturn(mockCompanyName);
        when(companyObj.getStockExchange()).thenReturn(mockStockExchange);
        when(companyObj.getCompanyTurnover()).thenReturn(mockCompanyTurnover);

        assertEquals(companyObj.getCompanyCode(), mockCompanyCode);
        assertEquals(companyObj.getCompanyCEO(),mockCompanyCEO);
        assertEquals(companyObj.getCompanyWebsite(),mockCompanyWebsite);
        assertEquals(companyObj.getCompanyName(),mockCompanyName);
        assertEquals(companyObj.getStockExchange(),mockStockExchange);
        assertEquals(companyObj.getCompanyTurnover(),mockCompanyTurnover);
    }
}
