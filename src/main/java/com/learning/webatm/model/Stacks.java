package com.learning.webatm.model;

import com.learning.webatm.enums.Currency;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Objects;

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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stacks stacks = (Stacks) o;
        return Objects.equals(notes, stacks.notes) && Objects.equals(count, stacks.count);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, notes, count);
    }

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
        return s.notes.getValue()-this.notes.getValue();
    }

    public int totalAmount(){
        return this.count * this.getNotes().getValue();
    }

    @Override
    public String toString() {
        return "Stacks{" +
                "id=" + id +
                ", notes=" + notes +
                ", count=" + count +
                '}';
    }
}
