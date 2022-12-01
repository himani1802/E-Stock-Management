package com.cts.StockManagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.CONFLICT, reason="CompanyId already present in Database")
public class ExistedCompanyException extends RuntimeException{
}
