package com.cts.StockManagement.exception;

public class CompanyNotFoundException extends RuntimeException {

    public  CompanyNotFoundException() {
        super("Company Code Not Found");
    }
}
