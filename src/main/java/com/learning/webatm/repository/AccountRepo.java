package com.learning.webatm.repository;

import com.learning.webatm.model.Account;
import com.learning.webatm.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public interface AccountRepo extends CrudRepository<Account, Long> {

    Account findAccountById(Long id);
    List<Account> findAccountsByOwner(User user);
}
