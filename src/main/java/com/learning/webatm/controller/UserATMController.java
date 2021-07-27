package com.learning.webatm.controller;


 import com.learning.webatm.container.AccountContainer;
 import com.learning.webatm.dto.AccountDTO;
 import com.learning.webatm.dto.MoneyDTO;
 import com.learning.webatm.enums.MoneyType;
import com.learning.webatm.enums.TransactionType;
import com.learning.webatm.enums.UserRole;
 import com.learning.webatm.model.*;

 import com.learning.webatm.service.AccountService;
 import com.learning.webatm.service.DrawerService;
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

        return ResponseEntity.status(HttpStatus.OK).body(account);
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



//    @PostMapping("/withdraw")
//    public ResponseEntity withdraw(@RequestParam String username,
//                                            @RequestParam Long accountId,
//                                            @RequestParam Integer value) {
//
//        User user = userService.findUserByUsername(username);
//        Account acc = accountService.findAccountById(accountId);
//
//        if(user.isAdmin()){
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//        }
//
//        if(!acc.isMyOwner(user)){
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//        }
//
//        if(!acc.enoughSold(value)){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insufficent funds");
//        }
//
//        Receipt rcp = atm.withDraw(value);
//
//        if(rcp == null){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Technical Difficulties");
//        }
//
//        acc.updateSold(value, TransactionType.TRANSACTION_WITHDRAW);
//        acc.addNewTransaction(rcp);
//        if(value >= 200){
//
//        }
//
//        return new ResponseEntity<>(rcp, HttpStatus.OK);
//
////    }

    @PostMapping("/withdraw")
    public ResponseEntity withdraw(@RequestParam String username,
                                   @RequestParam Long accountId,
                                   @RequestParam Integer value) {

        User user = userService.findUserByUsername(username);
        Account acc = accountService.findAccountById(accountId);

        if(user.isAdmin()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Drawer drawer = drawerService.getDrawer(1L);

        Money money = drawer.withdraw(acc.getCurrency(), value);

        drawerService.save(drawer);
        return ResponseEntity.status(HttpStatus.OK).body(money);

    }

    @PostMapping("/deposit")
    public ResponseEntity deposit(@RequestParam String username,
                                     @RequestParam Long accountId,
                                     @RequestBody MoneyDTO moneyDTO){

        User user = userService.findUserByUsername(username);
        Account acc = accountService.findAccountById(accountId);
        Money money = modelMapper.map(moneyDTO, Money.class);

        if(user.isAdmin()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You have no permission");
        }
        System.out.println(money.getCurrency());
        if(money.getCurrency() != acc.getCurrency()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Coaie, nu amesteci euro cu lei");
        }

        acc.setSold(acc.getSold() + money.getTotalAmount());
        accountService.save(acc);
        return ResponseEntity.status(HttpStatus.OK).body(acc);
    }

}
