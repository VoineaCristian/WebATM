package com.learning.webatm;



import com.learning.webatm.enums.MoneyType;
import com.learning.webatm.enums.TransactionType;
import com.learning.webatm.exception.NotEnoughMoney;
import com.learning.webatm.exception.NotEnoughPennies;
import com.learning.webatm.exception.RunOutOfMoney;
import com.learning.webatm.factory.MessageFactory;
import com.learning.webatm.model.Message;
import com.learning.webatm.model.Receipt;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;


@Component
public class Bank {

    private List<MoneyType> money;
    private TreeMap<MoneyType, Integer> map;
    private List<Integer> moneyStatus;
    private static Bank instance;
    private List<Message> mailbox;
    private int availableAmount;

    public Bank(){

        this.moneyStatus = new ArrayList<>();
        this.money = new ArrayList();
        this.mailbox = new ArrayList<>();
        this.money = Arrays.stream(MoneyType.values()).map(s->(MoneyType)s).collect(Collectors.toList());
        this.money.forEach(moneyType->{
            this.moneyStatus.add(moneyType.getInitialCount());
            availableAmount += moneyType.getValue()*moneyType.getInitialCount();
        });
        map = new TreeMap<MoneyType, Integer>();
        Arrays.stream(MoneyType.values()).forEach(moneyType -> map.put(moneyType, moneyType.getInitialCount()));
    }

    public Receipt getAmountOf(Integer amount) throws NotEnoughPennies, NotEnoughMoney, RunOutOfMoney {

        TreeMap<MoneyType, Integer> dueMoney = new TreeMap<>();
        TreeMap<MoneyType, Integer> mapBackup = new TreeMap<MoneyType, Integer>(this.map);
        int availableAmountBackup = availableAmount;

        if(!this.validAmount(amount)){
            if(availableAmount == 0){
                throw new RunOutOfMoney();
            } else  throw new NotEnoughMoney();
        }

        for(MoneyType moneyType : map.keySet())
        {
            int moneyIndex = moneyType.ordinal();
            int billValue = moneyType.getValue();
            int availableBills = map.get(moneyType);
            int nrOfUsedBills = Math.min(amount / billValue, availableBills);
            float percOfUsedMoney = 0;

            amount -= nrOfUsedBills*moneyType.getValue();
            availableBills -= nrOfUsedBills;
            map.put(moneyType, availableBills);

            dueMoney.put(moneyType, nrOfUsedBills);
            percOfUsedMoney = (float)availableBills/moneyType.getInitialCount();
            this.availableAmount -= nrOfUsedBills*moneyType.getValue();

            if(moneyType == MoneyType.LEU_100 && percOfUsedMoney < 0.2){
                addNewMail(billValue, percOfUsedMoney);
            } else if(moneyType == MoneyType.LEU_50 && percOfUsedMoney < 0.15){
                addNewMail(billValue, percOfUsedMoney);
            }
        }

        if(amount != 0){
            System.out.println(map);
            this.map = mapBackup;
            this.availableAmount = availableAmountBackup;

            if(this.availableAmount >= amount){
                throw new NotEnoughPennies();
            }
            throw new NotEnoughMoney();
        }
        System.out.println(this);
        System.out.println(this.map);
        return new Receipt(dueMoney, TransactionType.TRANSACTION_WITHDRAW);
    }

    public Receipt refill(TreeMap<MoneyType, Integer> refillMap){

        Receipt rcp = new Receipt(map, TransactionType.TRANSACTION_REFILL);

        refillMap.forEach((moneyType, count) -> this.map.merge(moneyType, count, Integer::sum));
        this.availableAmount += rcp.getReceiptTotalAmount();

        return rcp;
    }


    public boolean validAmount(int amount){
        return amount <= this.availableAmount;
    }

    public void addNewMail(int billValue, float percOfUsedMoney){

        MessageFactory messageFactory = new MessageFactory();
        Message msg = messageFactory.create(billValue, percOfUsedMoney, "fillMeUpPlease@superbancomat.com");
        this.mailbox.add(msg);

    }
    public List<Message> getMailbox() {
        return mailbox;
    }

    public void setMailbox(List<Message> mailbox) {
        this.mailbox = mailbox;
    }

    public void cleanMailbox() {
        this.mailbox.clear();
    }

    public static Integer getMoneyEntryValue(Map.Entry<MoneyType, Integer> entry){
        return entry.getKey().getValue() * entry.getValue();
    }

    public Receipt getBalance(){
        return new Receipt(this.map, TransactionType.TRANSACTION_CHECK_ATM_BALANCE);
    }




}
