package com.learning.webatm.model;

import com.learning.webatm.enums.Currency;

import java.util.ArrayList;
import java.util.List;

public class Money {

   private Long id;
   private Currency type;
   private List<Stacks> stacks;


    public Money() {

    }
    public Money(Currency type, List<Stacks> stacks) {
        this.type = type;
        this.stacks = stacks;
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

    public Currency getType() {
        return type;
    }

    public void setType(Currency type) {
        this.type = type;
    }

    public Stacks getStackByValue(Stacks s){
        return this.getStacks().stream()
                        .filter(stack->stack.getNotes().getValue()==s.getNotes().getValue())
                        .findFirst()
                        .orElse(null);
    }

    public void fill(Stacks s){
        Stacks existingStacks = this.getStackByValue(s);
        System.out.println(existingStacks);
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
}
