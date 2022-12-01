package com.cts.StockManagement.repository;

import com.cts.StockManagement.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface CompanyRepository extends JpaRepository<Company,Integer> {

    @Query(value = "select * from company where company.company_Code = :companyCode", nativeQuery = true)
    public Company getCompanyByCode(int companyCode);

}
