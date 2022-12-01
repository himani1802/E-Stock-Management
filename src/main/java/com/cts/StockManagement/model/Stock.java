package com.cts.StockManagement.model;

import javax.persistence.*;

@Entity
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int transactionId;
    private int companyCode_fk;
    private String date;
    private int stockPrice;


    public Stock() {
    }

    public Stock(int transactionId, int companyCode_fk, String date, int stockPrice) {
        this.transactionId = transactionId;
        this.companyCode_fk = companyCode_fk;
        this.date = date;
        this.stockPrice = stockPrice;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getCompanyCode_fk() {
        return companyCode_fk;
    }

    public void setCompanyCode_fk(int companyCode_fk) {
        this.companyCode_fk = companyCode_fk;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getStockPrice() {
        return stockPrice;
    }

    public void setStockPrice(int stockPrice) {
        this.stockPrice = stockPrice;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "transactionId=" + transactionId +
                ", companyCode_fk=" + companyCode_fk +
                ", date='" + date + '\'' +
                ", stockPrice=" + stockPrice +
                '}';
    }
}
