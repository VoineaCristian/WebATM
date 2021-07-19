package com.learning.webatm;

import com.learning.webatm.enums.Currency;
import com.learning.webatm.enums.MoneyType;
import com.learning.webatm.exception.NotEnoughMoney;
import com.learning.webatm.exception.NotEnoughPennies;
import com.learning.webatm.exception.RunOutOfMoney;
import com.learning.webatm.model.Bank;
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

    @Autowired
    private Bank bank;


//    public  withDraw(int amount, Currency currency)  {
//
//
//    }

    public Money refill(Money money) {

        return this.bank.refill(money);

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

    @Override
    public String toString() {
        return "ATM{" +
                "bank=" + bank +
                '}';
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }


}
