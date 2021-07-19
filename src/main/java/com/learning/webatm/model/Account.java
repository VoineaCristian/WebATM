package com.learning.webatm.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.learning.webatm.enums.TransactionType;

import java.util.ArrayList;
import java.util.List;

public class Account {

    Long id;
    int sold;
    List<Receipt> transactionHistory;
    @JsonIgnore
    User owner;

    public Account(Long id, Integer sold, List<Receipt> transations, User user) {
        this.id = id;
        this.sold = sold;
        if(transations == null)
            transactionHistory = new ArrayList<>();
        else this.transactionHistory = transations;
        this.owner = user;
    }


    public List<Receipt> getTransactionHistory() {
        return transactionHistory;
    }

    public void setTransactionHistory(List<Receipt> transactionHistory) {
        this.transactionHistory = transactionHistory;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSold() {
        return sold;
    }

    public void setSold(Integer sold) {
        this.sold = sold;
    }

    public boolean enoughSold(int value){
        return value <= this.sold;
    }

    public boolean isMyOwner(User user){
        return this.owner == user;
    }

    public Integer deposit(Receipt receipe){

        Integer amount  = receipe.getReceiptTotalAmount();
        this.sold += amount;
        return amount;
    }

    public void addNewTransaction(Receipt receipt){
        this.transactionHistory.add(receipt);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", sold=" + sold +
                ", transactionHistory=" + transactionHistory +
                '}';
    }

    public void updateSold(int value, TransactionType transactionType) {

        if(transactionType.equals(TransactionType.TRANSACTION_WITHDRAW)){
            this.sold -= value;
        }
        if(transactionType.equals(TransactionType.TRANSACTION_DEPOSIT)){
            this.sold += value;
        }
    }
}
