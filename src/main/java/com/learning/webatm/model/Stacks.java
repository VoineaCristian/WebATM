package com.learning.webatm.model;

import com.learning.webatm.enums.Currency;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
public class Stacks implements Comparable<Stacks> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "notes_id")
    private Notes notes;
    @Column(name = "count")
    private Integer count;


    public Stacks() {
    }

    public Stacks(Notes notes, Integer count) {
        this.notes = notes;
        this.count = count;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Notes getNotes() {
        return notes;
    }

    public void setNotes(Notes notes) {
        this.notes = notes;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public int compareTo(Stacks s) {
        return this.notes.getValue()-s.notes.getValue();
    }

    public int totalAmount(){
        return this.count * this.getNotes().getValue();
    }




}
