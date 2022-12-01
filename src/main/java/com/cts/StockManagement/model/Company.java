package com.cts.StockManagement.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private int companyCode;
    private String companyName;
    private String companyCEO;
    private int companyTurnover;
    private String companyWebsite;
    private int stockExchange;

    @OneToMany(targetEntity = Stock.class)
    private Set<Stock> stockList;

    public Company() {
    }

    public Company(int companyCode, String companyName, String companyCEO, int companyTurnover, String companyWebsite, int stockExchange, Set<Stock> stockList) {

        this.companyCode = companyCode;
        this.companyName = companyName;
        this.companyCEO = companyCEO;
        this.companyTurnover = companyTurnover;
        this.companyWebsite = companyWebsite;
        this.stockExchange = stockExchange;
        this.stockList = stockList;
    }

    public int getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(int companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyCEO() {
        return companyCEO;
    }

    public void setCompanyCEO(String companyCEO) {
        this.companyCEO = companyCEO;
    }

    public int getCompanyTurnover() {
        return companyTurnover;
    }

    public void setCompanyTurnover(int companyTurnover) {
        this.companyTurnover = companyTurnover;
    }

    public String getCompanyWebsite() {
        return companyWebsite;
    }

    public void setCompanyWebsite(String companyWebsite) {
        this.companyWebsite = companyWebsite;
    }

    public int getStockExchange() {
        return stockExchange;
    }

    public void setStockExchange(int stockExchange) {
        this.stockExchange = stockExchange;
    }

    public Set<Stock> getStockList() {
        return stockList;
    }

    public void setStockList(Set<Stock> stockList) {
        this.stockList = stockList;
    }

    @Override
    public String toString() {
        return "Company{" +
                "companyCode=" + companyCode +
                ", companyName='" + companyName + '\'' +
                ", companyCEO='" + companyCEO + '\'' +
                ", companyTurnover=" + companyTurnover +
                ", companyWebsite='" + companyWebsite + '\'' +
                ", stockExchange=" + stockExchange +
                ", stockList=" + stockList +
                '}';
    }
}
