package com.learning.webatm.dto;

import com.learning.webatm.enums.Currency;
import com.learning.webatm.model.Transaction;
import org.modelmapper.TypeToken;

import javax.persistence.Column;
import java.lang.reflect.Type;
import java.util.List;

public class AccountDTO {

    Long id;
    int sold;
    Currency currency;
    List<Transaction> transactions;
    String cvv;
    String cardNumber;
    String expirationDate;

    public AccountDTO() {
    }

    public AccountDTO(Long id, int sold, Currency currency, List<Transaction> transactions, String cvv, String cardNumber, String expirationDate) {
        this.id = id;
        this.sold = sold;
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

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
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

    public static Type getListTypeToken(){
        TypeToken<List<AccountDTO>> typeToken;
        typeToken = new TypeToken<>(){};
        return typeToken.getType();
    }
}
