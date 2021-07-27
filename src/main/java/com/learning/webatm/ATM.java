package com.learning.webatm;

import com.learning.webatm.enums.Currency;
import com.learning.webatm.enums.MoneyType;
import com.learning.webatm.exception.NotEnoughMoney;
import com.learning.webatm.exception.NotEnoughPennies;
import com.learning.webatm.exception.RunOutOfMoney;
import com.learning.webatm.model.Drawer;
import com.learning.webatm.model.Message;
import com.learning.webatm.model.Money;
import com.learning.webatm.model.Receipt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.TreeMap;
import java.util.logging.Logger;

@Component
public class ATM {

    Drawer drawer;

    public Money refill(Money money){

        this.drawer.addMoney(money.getCurrency(), money.getStacks());

        return drawer.getMoneyListByType(money.getCurrency());

    }

    public void sendMessages(){
//
//        List<Message> messages = this.bank.getMailbox();
//
//        if(!messages.isEmpty()) {
//            messages.forEach(message -> System.out.println(message));
//            this.bank.cleanMailbox();
//        }
    }

//    public Receipt getBankBalance(){
//        return this.bank.getBalance();
//    }




}
