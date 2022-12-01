package com.cts.StockManagement.exception;

public class CompanyFieldMustNotBeEmptyException extends RuntimeException{
    public CompanyFieldMustNotBeEmptyException(String message) {
        super("The field " + message + " must not be blank or empty.");
    }
}
