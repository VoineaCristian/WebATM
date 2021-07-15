package com.learning.webatm.repository;

import com.learning.webatm.model.Account;
import com.learning.webatm.model.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;


public class AccountRepo {

    private List<Account> accounts;

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public Account findAccountById(Long id){
        List<Account> accountsQuery = accounts.stream()
                .filter(account -> account.getId() == id)
                .collect(Collectors.toList());
        if(!accountsQuery.isEmpty()){
            return accountsQuery.get(0);
        } else return null;
    }

    public List<Account> findAccountsByOwner(User user){
        List<Account> accountsQuery = accounts.stream()
                .filter(account -> account.getOwner() == user)
                .collect(Collectors.toList());
        if(!accountsQuery.isEmpty()){
            return accountsQuery;
        } else return null;
    }
}
