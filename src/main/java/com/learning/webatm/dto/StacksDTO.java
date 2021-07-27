package com.learning.webatm.dto;

import com.learning.webatm.model.Notes;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class StacksDTO {

    private NotesDTO notes;
    private Integer count;

    public StacksDTO(){

    }

    public StacksDTO(NotesDTO notes, Integer count) {
        this.notes = notes;
        this.count = count;
    }


    public NotesDTO getNotes() {
        return notes;
    }

    public void setNotes(NotesDTO notes) {
        this.notes = notes;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
