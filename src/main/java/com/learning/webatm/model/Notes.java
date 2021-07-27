package com.learning.webatm.model;

import com.learning.webatm.enums.Banknotes;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

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
        System.out.println(value);
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
