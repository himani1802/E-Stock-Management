package com.cts.StockManagement.repository;

import com.cts.StockManagement.model.Company;
import com.cts.StockManagement.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Set;

@Repository
@Transactional
public interface StockRepository extends JpaRepository<Stock,Integer> {

    @Query(value = "select * from stock where stock.company_Code_fk = :companyCode", nativeQuery = true)
    public Stock getStockByCompanyCode(int companyCode);

    @Query(value="select * from stock  where stock.company_Code_fk= :companyCode", nativeQuery = true) // DQL
    public Set<Stock> getStockPriceInfoList(int companyCode);
    @Modifying
    @Query(value="delete from stock where stock.company_Code_fk= :companyCode", nativeQuery = true)
    public void deleteStockData(int companyCode);

}
