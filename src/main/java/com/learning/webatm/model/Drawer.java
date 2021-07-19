package com.learning.webatm.model;

import com.learning.webatm.enums.Currency;

import java.util.ArrayList;
import java.util.List;

public class Drawer {

    private List<Money> moneys;

    public Drawer() {
        this.moneys = new ArrayList<>();
    }

    public Money getMoneyListByType(Currency type){

        return moneys.stream()
                .filter(money->money.getType().equals(type))
                .findFirst()
                .orElse(null);

    }

    public Money addMoney(Currency currency, List<Stacks> stacks) {

        Money getStacks = getMoneyListByType(currency);

        if (getStacks == null) {
            this.moneys.add(new Money(currency, stacks));
        } else getStacks.addStacks(stacks);

        return getStacks;
    }
}
