package com.learning.webatm.controller;


 import com.learning.webatm.container.AccountContainer;
 import com.learning.webatm.dto.AccountDTO;
 import com.learning.webatm.dto.MoneyDTO;
 import com.learning.webatm.enums.*;
 import com.learning.webatm.exception.NotEnoughMoney;
 import com.learning.webatm.exception.NotEnoughPennies;
 import com.learning.webatm.exception.RunOutOfMoney;
 import com.learning.webatm.model.*;

 import com.learning.webatm.service.AccountService;
 import com.learning.webatm.service.DrawerService;
 import com.learning.webatm.service.NotesService;
 import com.learning.webatm.service.UserService;
 import org.modelmapper.ModelMapper;
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
    DrawerService drawerService;

    @Autowired
    NotesService notesService;

    ModelMapper modelMapper = new ModelMapper();


    @GetMapping("/balance")
    public ResponseEntity findAccountBalance(@RequestParam String username,
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

        AccountDTO accountDTO = modelMapper.map(account, AccountDTO.class);

        return ResponseEntity.status(HttpStatus.OK).body(accountDTO);
    }

    @GetMapping("/accounts")
    public ResponseEntity getAccountBalance(@RequestParam String username) {

        User user = userService.findUserByUsername(username);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        List<Account> accounts = accountService.findAccountsByOwner(user);
        List<AccountDTO> accountsDTO = modelMapper.map(accounts, AccountDTO.getListTypeToken());

        return ResponseEntity.status(HttpStatus.OK).body(new AccountContainer(accountsDTO));

    }

    @PostMapping("/withdraw")
    public ResponseEntity withdraw(@RequestParam String username,
                                   @RequestParam Long accountId,
                                   @RequestParam Integer value){

        User user = userService.findUserByUsername(username);
        Account acc = accountService.findAccountById(accountId);
        Money money;

        if(user.isAdmin()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        if(acc.getSold() < value){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient funds, Check Acc Balance");
        }

        Drawer drawer = drawerService.getDrawer(1L);

        try{
            money = drawer.withdraw(acc.getCurrency(), value);
        } catch (NotEnoughMoney notEnoughMoney) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not Enough Money in ATM");
        } catch (NotEnoughPennies notEnoughPennies) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not Enough Pennies, enter a rounded sum");
        } catch (RunOutOfMoney runOutOfMoney) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Run Out Of Money");
        }

        drawerService.save(drawer);
        MoneyDTO moneyDTO = modelMapper.map(money, MoneyDTO.class);
        return ResponseEntity.status(HttpStatus.OK).body(moneyDTO);

    }

    @PostMapping("/deposit")
    public ResponseEntity deposit(@RequestParam String username,
                                     @RequestParam Long accountId,
                                     @RequestBody MoneyDTO moneyDTO){


        User user = userService.findUserByUsername(username);
        Account acc = accountService.findAccountById(accountId);
        Money money = modelMapper.map(moneyDTO, Money.class);
        Drawer drawer = drawerService.getDrawer(1L);
        String pattern = Arrays.stream(BnkNamePattern.values())
                               .filter(n->n.getCurrency() == acc.getCurrency())
                               .findFirst().get().getPattern();
        List<Banknotes> atmNotes = notesService.findNotesByTypeLike(pattern).stream().map(n->n.getType()).collect(Collectors.toList());
        System.out.println(atmNotes);

        if(user.isAdmin()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You have no permission");
        }

        if(money.getCurrency() != acc.getCurrency()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Coaie, nu amesteci euro cu lei");
        }

        if(!drawer.checkMoney(atmNotes, money)){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You had introduced wrong note/s");
        }

        acc.setSold(acc.getSold() + money.getTotalAmount());
        accountService.save(acc);
        AccountDTO accountDTO = modelMapper.map(acc, AccountDTO.class);
        return ResponseEntity.status(HttpStatus.OK).body(accountDTO);
    }

}
