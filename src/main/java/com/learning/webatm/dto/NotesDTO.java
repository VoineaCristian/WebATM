package com.learning.webatm.dto;

import com.learning.webatm.enums.Banknotes;

import javax.persistence.Column;

public class NotesDTO {

    private Banknotes type;
    private Integer value;


    public NotesDTO(){

    }
    public NotesDTO(Banknotes type, Integer value) {
        this.type = type;
        this.value = value;
    }

    public Banknotes getType() {
        return type;
    }

    public void setType(Banknotes type) {
        this.type = type;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
