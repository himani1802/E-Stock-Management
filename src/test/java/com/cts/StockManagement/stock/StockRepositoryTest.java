package com.cts.StockManagement.stock;

import com.cts.StockManagement.model.Stock;
import com.cts.StockManagement.repository.StockRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
@AutoConfigureMockMvc
public class StockRepositoryTest {

    @Autowired
    private StockRepository stockRepository;
    Stock stock = new Stock();

    @BeforeEach
    public void init() {
        stock.setCompanyCode_fk(1);
        stock.setStockPrice(25000);
        stock.setDate("05/03/22");
    }

    @Test
    public void saveStockSuccess() throws Exception {
        Stock s1 = null;
        stockRepository.save(stock);
        s1= stockRepository.getStockByCompanyCode(stock.getCompanyCode_fk());
        assertEquals(stock.getCompanyCode_fk(), s1.getCompanyCode_fk());
    }

    @Test
    public void saveStockFailure() throws Exception {
        Stock s1 = null;
        if(stockRepository.findAll().toString().isEmpty()) {
            stockRepository.save(stock);
            s1 = stockRepository.findById(stock.getCompanyCode_fk()).get();
        }
        assertNull(s1);
    }
}
