package com.learning.webatm.model;

import com.learning.webatm.enums.Banknotes;
import com.learning.webatm.enums.Currency;
import com.learning.webatm.exception.NotEnoughMoney;
import com.learning.webatm.exception.NotEnoughPennies;
import com.learning.webatm.exception.RunOutOfMoney;
import com.learning.webatm.service.MoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Entity
public class Drawer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @OneToMany(cascade = {CascadeType.ALL})
    private List<Money> moneys;

    public Drawer() {
        this.moneys = new ArrayList<>();
        Arrays.stream(Currency.values()).forEach(curr -> this.moneys.add(new Money(curr)));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Money> getMoneys() {
        return moneys;
    }

    public void setMoneys(List<Money> moneys) {
        this.moneys = moneys;
    }

    public Money getMoneyListByType(Currency type) {

        return moneys.stream()
                .filter(money -> money.getCurrency().equals(type))
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

    public Money withdraw(Currency currency, int amount) throws NotEnoughMoney, NotEnoughPennies, RunOutOfMoney {
        Money money = getMoneyListByType(currency);
        List<Stacks> stacks = money.getStacks();
        List<Stacks> dueStacks = new ArrayList<>();
        int drawerAmount = this.getMoneyListByType(currency).getTotalAmount();
        if (drawerAmount == 0) {
            throw new RunOutOfMoney();
        }
        Collections.sort(stacks);
        for (Stacks stack : stacks) {
            int nrOfBills = amount / stack.getNotes().getValue();
            nrOfBills = Math.min(nrOfBills, stack.getCount());
            if(nrOfBills != 0)
                dueStacks.add(new Stacks(stack.getNotes(), nrOfBills));
            System.out.println(amount + " ---" + stack.getNotes() + " ===" + nrOfBills);
            amount -= nrOfBills * stack.getNotes().getValue();
            stack.setCount(stack.getCount() - nrOfBills);
        }

        if (amount != 0 && this.getMoneyListByType(currency).getTotalAmount() > amount) {
            throw new NotEnoughPennies();
        }
        if (amount != 0) {
            throw new NotEnoughMoney();
        }

        Money returnedMoney = new Money(currency, dueStacks);
        return returnedMoney;
    }

    public boolean checkMoney(List<Banknotes> notes, Money userMoney) {

        List<Banknotes> userNotes = userMoney.getStacks()
                                       .stream()
                                       .map(stack->stack.getNotes().getType())
                                       .collect(Collectors.toList())
                                       ;
        return notes.containsAll(userNotes);

    }
}
