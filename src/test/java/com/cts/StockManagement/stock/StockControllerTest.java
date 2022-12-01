package com.cts.StockManagement.stock;

import com.cts.StockManagement.controller.CompanyController;
import com.cts.StockManagement.controller.StockController;
import com.cts.StockManagement.model.Company;
import com.cts.StockManagement.model.Stock;
import com.cts.StockManagement.service.CompanyServiceImpl;
import com.cts.StockManagement.service.StockServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;



@AutoConfigureMockMvc
@SpringBootTest
public class StockControllerTest {

    @Mock
    private StockServiceImpl stockService;

    @InjectMocks
    private StockController stockController;

    @Autowired
    private MockMvc mockmvc;

    private List<Stock> stockList = new ArrayList();

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        mockmvc = MockMvcBuilders.standaloneSetup(stockController).build();
    }
}
