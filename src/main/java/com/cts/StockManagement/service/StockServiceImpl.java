package com.cts.StockManagement.service;


import com.cts.StockManagement.model.Stock;
import com.cts.StockManagement.repository.CompanyRepository;
import com.cts.StockManagement.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class StockServiceImpl implements StockService{

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private CompanyRepository companyRepository;


    @Override
    public Set<Stock> getAllStockPriceInfo(int companyCode) {

        Set<Stock> stockPriceInfoList = stockRepository.getStockPriceInfoList(companyCode);
        return stockPriceInfoList;
    }

    @Override
    public Stock getStockPriceInfoByCompanyCode(int companyCode) {
       Stock st= stockRepository.getStockByCompanyCode(companyCode);
        return st;
    }

    @Override
    public boolean addStock(Stock stock) {
      stock.setStockPrice(stock.getStockPrice());
      stock.setCompanyCode_fk(stock.getCompanyCode_fk());
      stock.setDate(stock.getDate());
      stockRepository.saveAndFlush(stock);
      return true;
    }

    @Override
    public boolean deleteStock(int companyCode) {
        stockRepository.deleteStockData(companyCode);
        return true;
    }

//    @Override
//    public Stock updateStockByCompanyCode(String companyCode, Stock stock) {
//        Stock stockObj = stockRepository.getStockByCompanyCode(companyCode);
//        if (stockObj !=null){
//            stockObj.setStockPrice(stock.getStockPrice());
//            stockObj.setDate(stock.getDate());
//            stockObj.setCompanyCode_fk(stock.getCompanyCode_fk());
//            stockObj.setTransactionId(stock.getTransactionId());
//            Stock st= stockRepository.save(stockObj);
//            return st;
//        }else {
//            throw new CompanyNotFoundException();
//        }
//    }

//    @Override
//    public List<Stock> getAllStockPriceInfo() {
//        return stockRepository.findAll();
//    }

//    @Override
//    public Stock getStockPriceInfoByCompanyCode(String companyCode) {
//        return stockRepository.getStockByCompanyCode(companyCode);
//    }
}
