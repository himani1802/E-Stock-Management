package com.cts.StockManagement.controller;

import com.cts.StockManagement.exception.CompanyNotFoundException;
import com.cts.StockManagement.exception.ExistedCompanyException;
import com.cts.StockManagement.model.Company;
import com.cts.StockManagement.model.Stock;
import com.cts.StockManagement.response.ResponseHandler;
import com.cts.StockManagement.service.CompanyService;
import com.cts.StockManagement.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/v1.0/market/company/")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private StockService stockService;


    @GetMapping("getAll")
    public ResponseEntity<?> getAllCompanyDetails() {
        List<Company> companyList = companyService.getAllCompany();
        if (companyList != null) {
            for (Company comp : companyList) {
                Set<Stock> stockList = stockService.getAllStockPriceInfo(comp.getCompanyCode());
                comp.setStockList(stockList);
            }
//            return new ResponseEntity<List<Company>>(companyList, HttpStatus.OK);
//            Response Handler
//            return ResponseHandler.generateResponse("Successfully fetching all company details",
//                    HttpStatus.OK, companyList);
//          ResponseHandler in CacheControl
            CacheControl cacheControl = CacheControl.maxAge(30, TimeUnit.HOURS);
            return  ResponseEntity.ok().cacheControl(cacheControl)
                    .body(ResponseHandler.generateResponse("Successfully fetching all company details",
                            HttpStatus.OK, companyList));
        }
        return new ResponseEntity<String>("companyList is empty", HttpStatus.NO_CONTENT);
    }

    @PostMapping("register")
    public ResponseEntity<?> registerCompany(@RequestBody Company company) throws ExistedCompanyException {
        if (companyService.addCompany(company) != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(companyService.addCompany(company));
        }
        return new ResponseEntity<String>("Company could not be created", HttpStatus.CONFLICT);
    }

    @GetMapping("info/{companyCode}")
    public ResponseEntity<?> getCompanyByCode(@PathVariable("companyCode") int companyCode) {
        Company compa = companyService.getCompanyByCompanyCode(companyCode);
        if (compa != null) {
            Set<Stock> stockList = stockService.getAllStockPriceInfo(compa.getCompanyCode());
            compa.setStockList(stockList);
//            return new ResponseEntity<Company>(compa, HttpStatus.OK);
            CacheControl cacheControl = CacheControl.maxAge(30, TimeUnit.HOURS);
            return  ResponseEntity.ok().cacheControl(cacheControl)
                    .body(ResponseHandler.generateResponse("Successfully fetching company details along with stock details",
                            HttpStatus.OK, compa));
        } else {
            return new ResponseEntity<String>("companyList is empty", HttpStatus.NO_CONTENT);
        }
    }


    @DeleteMapping("/delete/{companyCode}")
    public ResponseEntity<?> deleteCompany(@PathVariable("companyCode") int companyCode)throws CompanyNotFoundException {
        if(stockService.deleteStock(companyCode) && companyService.deleteCompanyByCompanyCode(companyCode)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<String>("company could not deleted", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("/put")
    public ResponseEntity<?> updateCompany(@RequestBody Company company) {
        if (companyService.updateCompanyByCompanyCode(company)) {
            return new ResponseEntity<String>("company record updated", HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("company could not updated", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
