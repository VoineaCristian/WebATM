package com.learning.webatm.repository;

import com.learning.webatm.enums.Currency;
import com.learning.webatm.model.Money;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoneyRepo extends CrudRepository<Money, Long> {

    Money findMoneyByCurrency(Currency currency);

}
