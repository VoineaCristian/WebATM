package com.learning.webatm.controller;

import com.learning.webatm.ATM;
import com.learning.webatm.enums.MoneyType;
import com.learning.webatm.enums.UserRole;
import com.learning.webatm.model.Money;
import com.learning.webatm.model.Receipt;
import com.learning.webatm.model.User;
import com.learning.webatm.service.AccountService;
import com.learning.webatm.service.SMSSenderService;
import com.learning.webatm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.TreeMap;

@RestController
@RequestMapping("/atm")
public class AdminATMController {

//    @Autowired
//    SMSSenderService senderService;

    @Autowired
    UserService userService;

    @Autowired
    AccountService accountService;

    @Autowired
    ATM atm;

    @PostMapping("/refill")
    public ResponseEntity refill(@RequestParam String username,
                                          @RequestBody Money money) {

        User user = userService.findUserByUsername(username);

        if(!user.isAdmin()){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        Money addedMoney = atm.refill(money);

        return ResponseEntity.status(HttpStatus.OK).body(addedMoney);

    }

//    @GetMapping("/atm-balance")
//    public ResponseEntity ATMBalance(@RequestParam String username){
//
//        User user = userService.findUserByUsername(username);
//        if(user.getRole() != UserRole.ROLE_ADMIN){
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You have no permission");
//        }
//
//        return ResponseEntity.status(HttpStatus.OK).body(atm.getBankBalance());
//    }

}
