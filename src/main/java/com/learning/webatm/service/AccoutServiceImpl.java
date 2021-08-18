package com.learning.webatm.service;

import com.learning.webatm.model.Account;
import com.learning.webatm.model.User;
import com.learning.webatm.repository.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccoutServiceImpl implements AccountService {

    @Autowired
    AccountRepo accountRepo;

    @Override
    public Account findAccountById(Long id) {
        return accountRepo.findAccountById(id);
    }

    @Override
    public List<Account> findAccountsByOwner(User user) {
        return accountRepo.findAccountsByOwner(user);
    }

    @Override
    public Account save(Account account){
        return accountRepo.save(account);
    }

    @Override
    public void deleteAccount(Account account) { accountRepo.delete(account);}

}
