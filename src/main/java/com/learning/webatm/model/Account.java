package com.learning.webatm.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.learning.webatm.enums.Currency;
import com.learning.webatm.enums.TransactionType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "sold")
    int sold;
    @ManyToOne
    @JoinColumn(name = "user_id")
    User owner;
    @Column(name = "currency")
    @Enumerated(EnumType.STRING)
    Currency currency;

    public Account(){}

    public Account(Long id, int sold, User owner, Currency currency) {
        this.id = id;
        this.sold = sold;
        this.owner = owner;
        this.currency = currency;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", sold=" + sold +
                ", transactionHistory="  +
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
