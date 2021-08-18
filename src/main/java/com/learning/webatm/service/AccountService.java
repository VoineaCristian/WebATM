package com.learning.webatm.service;

import com.learning.webatm.model.Account;
import com.learning.webatm.model.User;

import java.util.List;

public interface AccountService {

    Account findAccountById(Long id);
    List<Account> findAccountsByOwner(User user);
    Account save(Account account);
    void deleteAccount(Account account);
}
