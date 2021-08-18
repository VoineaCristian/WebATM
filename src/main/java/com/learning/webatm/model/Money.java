package com.learning.webatm.model;

import com.learning.webatm.enums.Currency;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
public class Money {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @OneToMany(cascade = {CascadeType.ALL})
    private List<Stacks> stacks;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return currency == money.currency && Objects.equals(stacks, money.stacks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currency, stacks);
    }

    public Money() {

    }


    public Money(Currency currency, List<Stacks> stacks) {
        this.currency = currency;
        this.stacks = stacks;
    }

    public Money(Currency currency) {
        this.currency = currency;
    }

    public Money(Long id, Currency currency, List<Stacks> stacks) {
        this.id = id;
        this.currency = currency;
        this.stacks = stacks;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Stacks> getStacks() {
        return stacks;
    }

    public void setStacks(List<Stacks> stacks) {
        this.stacks = stacks;
    }



    public Stacks getStackByValue(Stacks s){
        return this.getStacks().stream()
                        .filter(stack->stack.getNotes().getType()==s.getNotes().getType())
                        .findFirst()
                        .orElse(null);
    }

    public void fill(Stacks s){
        Stacks existingStacks = this.getStackByValue(s);
        if(existingStacks == null){
            this.stacks.add(s);
        } else {
            int actualCount = existingStacks.getCount();
            existingStacks.setCount(actualCount+s.getCount());
        }
    }

    public void addStacks(List<Stacks> stacksList) {

        stacksList.forEach(this::fill);
    }

    public Integer getTotalAmount(){
        int sum = 0;
        sum = this.stacks.stream()
                         .mapToInt(Stacks::totalAmount)
                         .sum();
        return sum;
    }

    @Override
    public String toString() {
        return "Money{" +
                "id=" + id +
                ", currency=" + currency +
                ", stacks=" + stacks +
                '}';
    }
}
