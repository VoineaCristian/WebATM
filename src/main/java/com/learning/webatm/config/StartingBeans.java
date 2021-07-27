package com.learning.webatm.config;

import com.learning.webatm.ATM;
import com.learning.webatm.service.MoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StartingBeans {

    @Autowired
    MoneyService moneyService;

//    @Bean
//    public ATM initATM(){
//
//    }



}
