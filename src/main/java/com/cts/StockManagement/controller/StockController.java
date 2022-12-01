package com.cts.StockManagement.controller;

import com.cts.StockManagement.model.Company;
import com.cts.StockManagement.model.Stock;
import com.cts.StockManagement.service.CompanyService;
import com.cts.StockManagement.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1.0/market/stock/")
public class StockController {

    @Autowired
    private StockService stockService;

    @Autowired
    private CompanyService companyService;

    @PostMapping("/add/{companyCode}")
    public ResponseEntity<?> addStock(@PathVariable("companyCode") int companyCode,
                                      @RequestBody Stock stock) {
        Company existCompany = companyService.getCompanyByCompanyCode(companyCode);
        if(existCompany !=null) {
            existCompany.setCompanyCode(stock.getCompanyCode_fk());
            stock.setCompanyCode_fk(stock.getCompanyCode_fk());
            stock.setStockPrice(stock.getStockPrice());
            stock.setDate(stock.getDate());
            if(companyService.updateCompanyByCompanyCode(existCompany) && stockService.addStock(stock))
                return new ResponseEntity<String>("Stock data updated in Company table and added in Stock table", HttpStatus.CREATED);
        }
        return new ResponseEntity<String>("Stock could not be added or updated", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
