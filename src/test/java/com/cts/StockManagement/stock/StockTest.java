package com.cts.StockManagement.stock;

import com.cts.StockManagement.model.Stock;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class StockTest {


    @Test
    public void testStock() {
        Stock stockObj = Mockito.mock(Stock.class);

        stockObj.setCompanyCode_fk(1);
        stockObj.setStockPrice(2500000);
        stockObj.setDate("05/03/22");

        Integer mockCompanyCode_fk = stockObj.getCompanyCode_fk();
        Integer mockStockPrice= stockObj.getStockPrice();
        String mockDate= stockObj.getDate();

        when(stockObj.getCompanyCode_fk()).thenReturn(mockCompanyCode_fk);
        when(stockObj.getStockPrice()).thenReturn(mockStockPrice);
        when(stockObj.getDate()).thenReturn(mockDate);

        assertEquals(stockObj.getCompanyCode_fk(), mockCompanyCode_fk);
        assertEquals(stockObj.getStockPrice(),mockStockPrice);
        assertEquals(stockObj.getDate(),mockDate);

    }
}
