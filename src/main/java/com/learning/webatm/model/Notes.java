package com.learning.webatm.model;

import com.learning.webatm.enums.Banknotes;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    private Banknotes type;
    @Column(name = "value")
    private Integer value;

    public Notes() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notes notes = (Notes) o;
        return type == notes.type && Objects.equals(value, notes.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, value);
    }

    public Notes(Banknotes bnk) {
        this.type = bnk;
    }



    public Banknotes getType() {
        return type;

    }

    public void setType(Banknotes type) {
        this.type = type;
    }

    public Notes(Banknotes type, Integer value) {
        this.type = type;
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Notes{" +
                "id=" + id +
                ", type=" + type +
                ", value=" + value +
                '}';
    }
}
