package com.learning.webatm.model;

import com.learning.webatm.Bank;
import com.learning.webatm.enums.MoneyType;
import com.learning.webatm.enums.TransactionType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Receipt {

    private Long id;
    private TreeMap<MoneyType, Integer> moneyMap;
    private TransactionType transactionType;

    public Receipt(Long id, TreeMap map, TransactionType type) {
        this.id = id;
        this.moneyMap = map;
        this.transactionType = type;
    }

    public Receipt(TreeMap map, TransactionType type) {
        moneyMap = map;
        this.transactionType = type;
    }

    public Integer getReceiptTotalAmount(){
        return this.getMoneyMap()
                    .entrySet()
                    .stream()
                    .mapToInt(Bank::getMoneyEntryValue)
                    .sum();
        }


    public TreeMap<MoneyType, Integer> getMoneyMap() {
        return moneyMap;
    }

    public void setMoneyMap(TreeMap<MoneyType, Integer> moneyMap) {
        this.moneyMap = moneyMap;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
