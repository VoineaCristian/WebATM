package com.learning.webatm.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.learning.webatm.enums.Currency;
import com.learning.webatm.enums.TransactionType;
import org.hibernate.annotations.CollectionId;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "account")
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
    @OneToMany(cascade = CascadeType.ALL)
    List<Transaction> transactions;
    @Column
    String cvv;
    @Column
    String cardNumber;
    @Column
    String expirationDate;
    

    public Account() {
    }

    public Account(Long id, int sold, User owner, Currency currency, List<Transaction> transactions, String cvv, String cardNumber, String expirationDate) {
        this.id = id;
        this.sold = sold;
        this.owner = owner;
        this.currency = currency;
        this.transactions = transactions;
        this.cvv = cvv;
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
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

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void updateSold(int value, TransactionType transactionType) {

        if (transactionType.equals(TransactionType.TRANSACTION_WITHDRAW)) {
            this.sold -= value;
        }
        if (transactionType.equals(TransactionType.TRANSACTION_DEPOSIT)) {
            this.sold += value;
        }
    }

}
