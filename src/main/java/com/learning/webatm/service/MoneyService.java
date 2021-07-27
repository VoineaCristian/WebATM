package com.learning.webatm.service;

import com.learning.webatm.enums.Currency;
import com.learning.webatm.model.Money;

public interface MoneyService {

    Money findMoneyByCurrency(Currency currency);
    Money save(Money money);
}
