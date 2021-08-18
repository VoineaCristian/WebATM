package com.learning.webatm.controller;


import com.learning.webatm.container.AccountContainer;
import com.learning.webatm.dto.AccountDTO;
import com.learning.webatm.dto.MoneyDTO;
import com.learning.webatm.enums.*;
import com.learning.webatm.enums.Currency;
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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

//        return userer.getuser()
//                .filter()
//                .map()
//                .orElse()

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
    public ResponseEntity getAccountBalance() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findUserByUsername(username);
        System.out.println(username);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        List<Account> accounts = accountService.findAccountsByOwner(user);
        List<AccountDTO> accountsDTO = modelMapper.map(accounts, AccountDTO.getListTypeToken());

        return ResponseEntity.status(HttpStatus.OK).body(new AccountContainer(accountsDTO));

    }
    @GetMapping("/restricted")
    public ResponseEntity restricted() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        return ResponseEntity.status(HttpStatus.OK).body(new Notes());
    }

    @GetMapping("/getNotes")
    public ResponseEntity getAccountBalance(@RequestParam Currency currency) {
        String pattern = Arrays.stream(BnkNamePattern.values())
                .filter(n -> n.getCurrency() == currency)
                .findFirst().get().getPattern();

        List<Banknotes> atmNotes = notesService.findNotesByTypeLike(pattern).stream().map(n -> n.getType()).collect(Collectors.toList());
        List<Notes> notes = atmNotes.stream().map(n->notesService.findNoteByType(n)).collect(Collectors.toList());
        System.out.println(atmNotes);
        return ResponseEntity.status(HttpStatus.OK).body(
                notes);

    }

    @PostMapping("/accounts/{username}")
    public ResponseEntity addAccount(@PathVariable(name = "username") String username, @RequestBody AccountDTO account) {

        User user = userService.findUserByUsername(username);
        System.out.println(username);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Account acc = modelMapper.map(account, Account.class);
        acc.setOwner(user);
        accountService.save(acc);
        return ResponseEntity.status(HttpStatus.OK).body(account);

    }
    @RequestMapping(value="/accounts/{username}", method = RequestMethod.DELETE)
    public ResponseEntity deleteAccount(@PathVariable String username, @RequestParam(name = "accountId") Long accountId) {

        User user = userService.findUserByUsername(username);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        Account acc = accountService.findAccountById(accountId);
        accountService.deleteAccount(acc);
        return ResponseEntity.status(HttpStatus.OK).body("Success");

    }


    @PostMapping("/withdraw/{username}/{accountId}")
    public ResponseEntity withdraw(@PathVariable(name = "username") String username,
                                   @PathVariable(name = "accountId") Long accountId,
                                   @RequestParam Integer value) {

        User user = userService.findUserByUsername(username);
        Account acc = accountService.findAccountById(accountId);
        Money money;

        if (user.isAdmin()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        if (acc.getSold() < value) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficient funds, Check Acc Balance");
        }

        Drawer drawer = drawerService.getDrawer(1L);
        try {
            money = drawer.withdraw(acc.getCurrency(), value);
        } catch (NotEnoughMoney notEnoughMoney) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not Enough Money in ATM");
        } catch (NotEnoughPennies notEnoughPennies) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not Enough Pennies, enter a rounded sum");
        } catch (RunOutOfMoney runOutOfMoney) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Run Out Of Money");
        }

        //@ControllerAdvice
        Date date = new Date();
        acc.setSold(acc.getSold() - money.getTotalAmount());
        acc.getTransactions().add(new Transaction(null, date, value, TransactionType.TRANSACTION_WITHDRAW));

        accountService.save(acc);
        drawerService.save(drawer);
        MoneyDTO moneyDTO = modelMapper.map(money, MoneyDTO.class);
        return ResponseEntity.status(HttpStatus.OK).body(moneyDTO);

    }

    @PostMapping("/deposit/{username}/{accountId}")
    public ResponseEntity deposit(@PathVariable(name = "username") String username,
                                  @PathVariable(name = "accountId") Long accountId,
                                  @RequestBody MoneyDTO moneyDTO) {


        User user = userService.findUserByUsername(username);
        Account acc = accountService.findAccountById(accountId);
        Money money = modelMapper.map(moneyDTO, Money.class);
        Drawer drawer = drawerService.getDrawer(1L);

        String pattern = Arrays.stream(BnkNamePattern.values())
                .filter(n -> n.getCurrency() == acc.getCurrency())
                .findFirst().get().getPattern();
        List<Banknotes> atmNotes = notesService.findNotesByTypeLike(pattern).stream().map(n -> n.getType()).collect(Collectors.toList());

        System.out.println(moneyDTO);
        if (user.isAdmin()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You have no permission");
        }

        if (money.getCurrency() != acc.getCurrency()) {
            System.out.println("bad currency");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }

        if (!drawer.checkMoney(atmNotes, money)) {
            System.out.println("check");

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You had introduced wrong note/s");
        }

        acc.setSold(acc.getSold() + money.getTotalAmount());
        Date date = new Date();
        acc.getTransactions().add(new Transaction(null, date, money.getTotalAmount(), TransactionType.TRANSACTION_DEPOSIT));
        System.out.println(moneyDTO);
        accountService.save(acc);
        AccountDTO accountDTO = modelMapper.map(acc, AccountDTO.class);
        return ResponseEntity.status(HttpStatus.OK).body(accountDTO);
    }

}
