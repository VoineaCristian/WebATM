package com.learning.webatm.dto;

import com.learning.webatm.enums.Currency;
import com.learning.webatm.model.Stacks;
import org.modelmapper.TypeToken;

import javax.persistence.CascadeType;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import java.lang.reflect.Type;
import java.util.List;

public class MoneyDTO {

    private Currency currency;
    private List<StacksDTO> stacks;
    TypeToken<List<MoneyDTO>> typeToken = new TypeToken<>() {
    };
    public MoneyDTO() {
    }

    public MoneyDTO(Currency type, List<StacksDTO> stacks) {
        this.currency = type;
        this.stacks = stacks;
    }



    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency type) {
        this.currency = type;
    }

    public List<StacksDTO> getStacks() {
        return stacks;
    }

    public void setStacks(List<StacksDTO> stacks) {
        this.stacks = stacks;
    }

     public static Type getListTypeToken(){
         TypeToken<List<MoneyDTO>> typeToken;
         typeToken = new TypeToken<>(){};
        return typeToken.getType();
     }
}
