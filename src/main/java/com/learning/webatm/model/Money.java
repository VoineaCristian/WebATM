package com.learning.webatm.model;

import com.learning.webatm.enums.Currency;

import java.util.List;

public class Money {

    private Currency currency;
    private List<Bill> bills;

    public Money(Currency currency, List<Bill> bills) {
        this.currency = currency;
        this.bills = bills;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public List<Bill> getBills() {
        return bills;
    }

    public void setBills(List<Bill> bills) {
        this.bills = bills;
    }

    @Override
    public String toString() {
        return "Money{" +
                "currency=" + currency +
                ", bills=" + bills +
                '}';
    }
}
