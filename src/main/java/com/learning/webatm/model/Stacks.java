package com.learning.webatm.model;

import com.learning.webatm.enums.Currency;
import org.springframework.util.comparator.ComparableComparator;

public class Stacks implements Comparable<Stacks> {


    private Long id;
    private Notes notes;
    private Integer count;


    public Stacks(Notes note, Integer count) {
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
        return this.notes.value-s.notes.value;
    }


    @Override
    public String toString() {
        return "Bill{" +
                ", value=" +
                ", count=" + count +
                '}';
    }


}
