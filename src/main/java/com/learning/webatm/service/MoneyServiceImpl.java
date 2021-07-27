package com.learning.webatm.service;

import com.learning.webatm.enums.Currency;
import com.learning.webatm.model.Money;
import com.learning.webatm.repository.MoneyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MoneyServiceImpl implements MoneyService{

    @Autowired
    private MoneyRepo moneyRepo;

    @Override
    public Money findMoneyByCurrency(Currency currency) {
        return moneyRepo.findMoneyByCurrency(currency);
    }

    public Money save(Money money){
        return moneyRepo.save(money);
    }
}
