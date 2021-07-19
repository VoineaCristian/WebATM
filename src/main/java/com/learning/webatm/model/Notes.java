package com.learning.webatm.model;

import com.learning.webatm.enums.Currency;

public class Notes {

    Currency type;
    Integer value;

    public Notes(Currency type, Integer value) {
        this.type = type;
        this.value = value;
    }

    public Currency getType() {
        return type;
    }

    public void setType(Currency type) {
        this.type = type;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
