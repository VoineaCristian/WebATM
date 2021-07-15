package com.learning.webatm.controller;


import com.learning.webatm.ATM;
import com.learning.webatm.enums.MoneyType;
import com.learning.webatm.enums.TransactionType;
import com.learning.webatm.enums.UserRole;
import com.learning.webatm.model.Account;
import com.learning.webatm.model.Receipt;
import com.learning.webatm.model.User;

import com.learning.webatm.service.AccountService;
import com.learning.webatm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
public class UserATMController {

    @Autowired
    UserService userService;

    @Autowired
    AccountService accountService;

    @Autowired
    ATM atm;

    @GetMapping("/balance")
    public ResponseEntity<Integer> findAccountBalance(@RequestParam String username,
                                                  @RequestParam Long accID) {

        User user = userService.findUserByUsername(username);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (user.isAdmin()) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        Account account = accountService.findAccountById(accID);

        if (account == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(account.getSold(), HttpStatus.OK);
    }

    @GetMapping("/accounts")
    public ResponseEntity<List<Account>> getAccountBalance(@RequestParam String username) {

        User user = userService.findUserByUsername(username);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(user.getAccounts(), HttpStatus.OK);

    }



    @PostMapping("/withdraw")
    public ResponseEntity withdraw(@RequestParam String username,
                                            @RequestParam Long accountId,
                                            @RequestParam Integer value) {

        User user = userService.findUserByUsername(username);
        Account acc = accountService.findAccountById(accountId);

        if(user.isAdmin()){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        if(!acc.isMyOwner(user)){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        if(!acc.enoughSold(value)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficent funds");
        }

        Receipt rcp = atm.withDraw(value);

        if(rcp == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Technical Difficulties");
        }

        acc.updateSold(value, TransactionType.TRANSACTION_WITHDRAW);
        acc.addNewTransaction(rcp);
        if(value >= 200){

        }

        return new ResponseEntity<>(rcp, HttpStatus.OK);

    }

    @PostMapping("/deposit")
    public ResponseEntity deposit(@RequestParam String username,
                                     @RequestParam Long accountId,
                                     @RequestBody TreeMap<MoneyType, Integer> map){

        User user = userService.findUserByUsername(username);
        Account acc = accountService.findAccountById(accountId);

        if(user.isAdmin()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You have no permission");
        }

        if(!acc.isMyOwner(user)){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        Receipt receipt = new Receipt(map, TransactionType.TRANSACTION_DEPOSIT);
        acc.addNewTransaction(receipt);
        acc.deposit(receipt);
        atm.refill(map);

        return ResponseEntity.status(HttpStatus.OK).body(receipt);
    }

}
