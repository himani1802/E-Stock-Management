package com.cts.StockManagement.stock;

import com.cts.StockManagement.model.Stock;
import com.cts.StockManagement.repository.StockRepository;
import com.cts.StockManagement.service.StockServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@AutoConfigureMockMvc
@SpringBootTest
public class StockServiceTest {

    @Mock
    private StockRepository stockRepository;
    @InjectMocks
    private StockServiceImpl stockService;
    @Autowired
    private MockMvc mockmvc;

    private List<Stock> stockList = new ArrayList();

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        mockmvc = MockMvcBuilders.standaloneSetup(stockService).build();
    }

    @Test
    public void getStockPriceInfoByCodeSuccess()throws Exception{
        Stock stock  = new Stock();
        stock.setCompanyCode_fk(1);
        stock.setStockPrice(40000000);
        stock.setDate("05/03/22");
        stockList.add(stock);
        when(stockRepository.getStockByCompanyCode(stock.getCompanyCode_fk())).thenReturn(stock);
        List<Stock> s1= List.of(stockService.getStockPriceInfoByCompanyCode(stock.getCompanyCode_fk()));
        assertEquals(stockList,s1);
    }

    @Test
    public void getStockPriceInfoByCodeFailure()throws Exception{
        Stock stock  = new Stock();
        when(stockRepository.getStockByCompanyCode(stock.getCompanyCode_fk())).thenReturn(null);
        Stock s1= stockService.getStockPriceInfoByCompanyCode(stock.getCompanyCode_fk());
        assertNull(s1);
    }

//    @Test
//    public void deleteStockSuccess()throws Exception{
//        Stock stock  = new Stock();
//        stock.setCompanyCode_fk(1);
//        stock.setStockPrice(40000000);
//        stock.setDate("05/03/22");
//        stockList.add(stock);
//        when(stockRepository.deleteStockData(stock.getCompanyCode_fk())).thenReturn(true);
//        boolean s1= stockService.deleteStock(stock.getCompanyCode_fk());
//        assertEquals(true, s1);
//    }

    @Test
    public void addStockSuccess() throws Exception {
        Stock stock  = new Stock();
        stock.setCompanyCode_fk(1);
        stock.setStockPrice(40000000);
        stock.setDate("05/03/22");
        stockList.add(stock);
        when(stockRepository.save(any())).thenReturn(true);
        boolean st  = stockService.addStock(stock);
        assertEquals(true,st);
    }

//    @Test
//    public void addStockFailure() throws Exception {
//        Stock stock  = new Stock();
//        stock.setCompanyCode_fk(1);
//        stock.setStockPrice(40000000);
//        stock.setDate("05/03/22");
//        stockList.add(stock);
//        when(stockRepository.save(any())).thenReturn(false);
//        Stock st  = stockService.addStock(stock);
//        assertNull(st);
//    }
}
