package com.learning.webatm.model;

import com.learning.webatm.enums.Currency;

public class Bill {

    private Currency currency;
    private Integer value;
    private Integer count;


    public Bill(Currency currency, Integer value, Integer count) {
        this.currency = currency;
        this.value = value;
        this.count = count;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "currency=" + currency +
                ", value=" + value +
                ", count=" + count +
                '}';
    }
}
