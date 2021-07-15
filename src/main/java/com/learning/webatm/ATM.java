package com.learning.webatm;

import com.learning.webatm.enums.MoneyType;
import com.learning.webatm.exception.NotEnoughMoney;
import com.learning.webatm.exception.NotEnoughPennies;
import com.learning.webatm.exception.RunOutOfMoney;
import com.learning.webatm.model.Message;
import com.learning.webatm.model.Receipt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

@Component
public class ATM {

    @Autowired
    private Bank bank;


    public Receipt withDraw(int amount)  {

        Logger logger =  Logger.getLogger(ATM.class.getName());
        Receipt receipt = null;

        try {
            bank.getBalance();
            receipt = this.bank.getAmountOf(amount);
        } catch (NotEnoughMoney NEM){
            logger.warning(NEM.getMessage());
        }catch (NotEnoughPennies NEP){
            logger.warning(NEP.getMessage());
        }catch (RunOutOfMoney ROOF){
            logger.warning(ROOF.getMessage());
        }
        this.sendMessages();

        return receipt;
    }

    public Receipt refill(TreeMap<MoneyType, Integer> map) {

        return this.bank.refill(map);

    }

    public void sendMessages(){

        List<Message> messages = this.bank.getMailbox();

        if(!messages.isEmpty()) {
            messages.forEach(message -> System.out.println(message));
            this.bank.cleanMailbox();
        }
    }

    public Receipt getBankBalance(){
        return this.bank.getBalance();
    }

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
