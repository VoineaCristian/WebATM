package com.learning.webatm;

import com.learning.webatm.enums.MoneyType;
import com.learning.webatm.enums.TransactionType;
import com.learning.webatm.enums.UserRole;
import com.learning.webatm.model.Account;
import com.learning.webatm.model.Receipt;
import com.learning.webatm.model.SMS;
import com.learning.webatm.model.User;
import com.learning.webatm.repository.AccountRepo;
import com.learning.webatm.repository.UserRepo;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import sms.SMSSender;

import java.util.*;

@Configuration
public class StartingBeans {

    List<Account> accounts = new ArrayList<>();

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    public UserRepo userRepo(){
        UserRepo userRepo =  new UserRepo();

        TreeMap<MoneyType, Integer> money1 = new TreeMap<>();
        money1.put(MoneyType.LEU_100, 5);
        money1.put(MoneyType.LEU_50, 3);
        money1.put(MoneyType.LEU_10, 1);
        money1.put(MoneyType.LEU_5, 7);
        money1.put(MoneyType.LEU_1, 2);

        TreeMap<MoneyType, Integer> money2 = new TreeMap<>();
        money2.put(MoneyType.LEU_100, 8);
        money2.put(MoneyType.LEU_50, 1);
        money2.put(MoneyType.LEU_10, 9);
        money2.put(MoneyType.LEU_5, 4);
        money2.put(MoneyType.LEU_1, 1);

        TreeMap<MoneyType, Integer> money3 = new TreeMap<>();
        money3.put(MoneyType.LEU_100, 8);
        money3.put(MoneyType.LEU_50, 9);
        money3.put(MoneyType.LEU_10, 2);
        money3.put(MoneyType.LEU_5, 7);
        money3.put(MoneyType.LEU_1, 3);



        Receipt rec1 = new Receipt(1L, money1, TransactionType.TRANSACTION_WITHDRAW);
        Receipt rec2 = new Receipt(2L, money2, TransactionType.TRANSACTION_DEPOSIT);
        Receipt rec3 = new Receipt(3L ,money3, TransactionType.TRANSACTION_WITHDRAW);


        User user1 = new User(1L, "vasile",null, UserRole.ROLE_ADMIN, "0741111111");
        User user2 = new User(2L, "cornel",new ArrayList<>(), UserRole.ROLE_USER, "0742222222");
        User user4 = new User(3L, "mirel",null, UserRole.ROLE_ADMIN, "0744444444");
        User user3 = new User(4L, "dorel", new ArrayList<>(), UserRole.ROLE_USER, "0743333333");

        Account acc1 = new Account(1L, 100,new ArrayList<>(Arrays.asList(rec1, rec2)), user2);
        Account acc2 = new Account(2L, 99999, new ArrayList<>(Arrays.asList(rec2, rec3)), user2 );
        Account acc3 = new Account(3L, 200, new ArrayList<>(Arrays.asList(rec1, rec3)), user3);
        Account acc4 = new Account(4L, 300, null, user3);
        Account acc5 = new Account(5L, 400, new ArrayList<>(Arrays.asList(rec1, rec2)), user3);

        user2.setAccounts(new ArrayList<>(Arrays.asList(acc1, acc2)));
        user3.setAccounts(new ArrayList<>(Arrays.asList(acc3, acc4, acc5)));


        Collections.addAll(accounts, acc1, acc2, acc3, acc4, acc5);
        userRepo.setUsers(new ArrayList<>(Arrays.asList(user1, user2, user3, user4)));

        return userRepo;
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    public AccountRepo accountRepo(){

        AccountRepo accRepo = new AccountRepo();
        accRepo.setAccounts(accounts);
        return accRepo;
    }

//    @Bean
//    public SMSSender smsSender(){
//        SMSSender smsSender = new SMSSender(new SMS());
//        return smsSender;
 //   }

}