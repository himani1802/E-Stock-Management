package com.cts.StockManagement.exception;

public class CompanyTurnoverMinimumRequiredValidation extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public CompanyTurnoverMinimumRequiredValidation() {
        super("Company Turnover must be greater than 10cr.");
    }
}
