package com.learning.webatm.dto;

import com.learning.webatm.enums.Currency;
import org.modelmapper.TypeToken;
import java.lang.reflect.Type;
import java.util.List;

public class AccountDTO {

    int id;
    int sold;
    Currency currency;


    public AccountDTO() {
    }

    public AccountDTO(int id, int sold, Currency currency) {
        this.id = id;
        this.sold = sold;
        this.currency = currency;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public static Type getListTypeToken(){
        TypeToken<List<AccountDTO>> typeToken;
        typeToken = new TypeToken<>(){};
        return typeToken.getType();
    }
}
