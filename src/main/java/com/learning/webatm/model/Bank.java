package com.learning.webatm.model;



import com.learning.webatm.enums.MoneyType;
import com.learning.webatm.enums.TransactionType;
import com.learning.webatm.exception.NotEnoughMoney;
import com.learning.webatm.exception.NotEnoughPennies;
import com.learning.webatm.exception.RunOutOfMoney;
import com.learning.webatm.factory.MessageFactory;
import com.learning.webatm.model.Message;
import com.learning.webatm.model.Money;
import com.learning.webatm.model.Receipt;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;


@Component
public class Bank {


    private Integer availableAmount;
    private Drawer drawer;


    public Money getAmountOf(Integer amount, Currency type){
        return new Money();
    }

    public Bank(){

        this.drawer = new Drawer();
        this.availableAmount = 0;
    }

    public static int getMoneyEntryValue(Map.Entry<MoneyType, Integer> moneyTypeIntegerEntry) {
        return 0;
    }
//
//    public Receipt getAmountOf(Integer amount, Currency type) throws NotEnoughPennies, NotEnoughMoney, RunOutOfMoney {
//
//
//        int availableAmountBackup = availableAmount;
//
//
//        if(!this.validAmount(amount)){
//            if(availableAmount == 0){
//                throw new RunOutOfMoney();
//            } else  throw new NotEnoughMoney();
//        }
//
//        for(MoneyType moneyType : map.keySet())
//        {
//            int moneyIndex = moneyType.ordinal();
//            int billValue = moneyType.getValue();
//            int availableBills = map.get(moneyType);
//            int nrOfUsedBills = Math.min(amount / billValue, availableBills);
//            float percOfUsedMoney = 0;
//
//            amount -= nrOfUsedBills*moneyType.getValue();
//            availableBills -= nrOfUsedBills;
//            map.put(moneyType, availableBills);
//
//            dueMoney.put(moneyType, nrOfUsedBills);
//            percOfUsedMoney = (float)availableBills/moneyType.getInitialCount();
//            this.availableAmount -= nrOfUsedBills*moneyType.getValue();
//
//            if(moneyType == MoneyType.LEU_100 && percOfUsedMoney < 0.2){
//                addNewMail(billValue, percOfUsedMoney);
//            } else if(moneyType == MoneyType.LEU_50 && percOfUsedMoney < 0.15){
//                addNewMail(billValue, percOfUsedMoney);
//            }
//        }
//
//        if(amount != 0){
//            System.out.println(map);
//            this.map = mapBackup;
//            this.availableAmount = availableAmountBackup;
//
//            if(this.availableAmount >= amount){
//                throw new NotEnoughPennies();
//            }
//            throw new NotEnoughMoney();
//        }
//        System.out.println(this);
//        System.out.println(this.map);
//        return new Receipt(dueMoney, TransactionType.TRANSACTION_WITHDRAW);
//    }
//
    public Money refill(Money money){

        this.drawer.addMoney(money.getType(), money.getStacks());

        return drawer.getMoneyListByType(money.getType());

    }
//
//
//    public boolean validAmount(int amount){
//        return amount <= this.availableAmount;
//    }
//
//    public void addNewMail(int billValue, float percOfUsedMoney){
//
//        MessageFactory messageFactory = new MessageFactory();
//        Message msg = messageFactory.create(billValue, percOfUsedMoney, "fillMeUpPlease@superbancomat.com");
//        this.mailbox.add(msg);
//
//    }
//    public List<Message> getMailbox() {
//        return mailbox;
//    }
//
//    public void setMailbox(List<Message> mailbox) {
//        this.mailbox = mailbox;
//    }
//
//    public void cleanMailbox() {
//        this.mailbox.clear();
//    }
//
//    public static Integer getMoneyEntryValue(Map.Entry<MoneyType, Integer> entry){
//        return entry.getKey().getValue() * entry.getValue();
//    }
//
//    public Receipt getBalance(){
//        return new Receipt(this.map, TransactionType.TRANSACTION_CHECK_ATM_BALANCE);
//    }
//
//


}
