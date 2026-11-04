package com.springpractise.pharmapractise;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;

@Entity
@Table(name = "CRYPTO")
public class Crypto implements Serializable {

    @Id
    @Column(name = "ticker", length = 3)  // Column: ticker
    private String ticker;

    @Column(name = "name", length = 255)  // Column: name
    private String name;

    @Column(name = "amount")  // Column: amount (no underscore)
    private long amount;

    @Column(name = "marketCap")  // Column: marketCap (camelCase)
    private long marketCap;

    public Crypto() {}

    public Crypto(String ticker, String name, long amount, long marketCap) {
        this.ticker = ticker;
        this.name = name;
        this.amount = amount;
        this.marketCap = marketCap;
    }

    public String getTicker() {
        return this.ticker;
    }

    public String getName() {
        return this.name;
    }

    public long getAmount() {
        return this.amount;
    }

    public long getMarketCap() {
        return this.marketCap;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public void setMarketCap(long marketCap) {
        this.marketCap = marketCap;
    }
}
