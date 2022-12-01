package com.cts.StockManagement.service;

import com.cts.StockManagement.model.Company;
import com.cts.StockManagement.model.Stock;

import java.util.List;
import java.util.Set;

public interface StockService {

    public Set<Stock> getAllStockPriceInfo(int companyCode);

    public Stock getStockPriceInfoByCompanyCode(int companyCode);


    public boolean addStock(Stock stock);

    public boolean deleteStock(int companyCode);


}
