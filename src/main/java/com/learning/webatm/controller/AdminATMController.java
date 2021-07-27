package com.learning.webatm.controller;

import com.learning.webatm.container.MoneyContainer;
import com.learning.webatm.dto.MoneyDTO;
import com.learning.webatm.enums.UserRole;
import com.learning.webatm.model.*;
import com.learning.webatm.repository.DrawerRepo;
import com.learning.webatm.repository.NotesRepo;
import com.learning.webatm.service.AccountService;
import com.learning.webatm.service.DrawerService;
import com.learning.webatm.service.MoneyService;
import com.learning.webatm.service.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/atm")
public class AdminATMController {


    @Autowired
    MoneyService moneyService;

    @Autowired
    UserService userService;

    @Autowired
    AccountService accountService;

    @Autowired
    DrawerService drawerService;

    @Autowired
    NotesRepo notesRepo;

    ModelMapper modelMapper = new ModelMapper();

    @PostMapping("/refill")
    public ResponseEntity refill(@RequestParam String username,
                                 @RequestBody MoneyDTO moneyDTO) {

        User user = userService.findUserByUsername(username);
        Drawer drawer = drawerService.getDrawer(1L);
        Money money = modelMapper.map(moneyDTO, Money.class);

        money.getStacks().forEach(stack->stack.setNotes(notesRepo.findNoteByType(stack.getNotes().getType())));

        if(!user.isAdmin()){
            return ResponseEntity.status(HttpStatus.OK).build();
        }

        drawer.addMoney(money.getCurrency(), money.getStacks());
        drawerService.save(drawer);
        return ResponseEntity.status(HttpStatus.OK).body(moneyDTO);

    }

    @GetMapping("/balance")
    public ResponseEntity ATMBalance(@RequestParam String username){

        User user = userService.findUserByUsername(username);

        if(user.getRole() != UserRole.ROLE_ADMIN){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You have no permission");
        }

        Drawer drawer = drawerService.getDrawer(1L);
        List<Money> existingMoneys = drawer.getMoneys();

        TypeToken<List<MoneyDTO>> typeToken = new TypeToken<>() {
        };
        List<MoneyDTO> exxitingMoneysDTO = modelMapper.map(existingMoneys, MoneyDTO.getListTypeToken());


        return ResponseEntity.status(HttpStatus.OK).body(new MoneyContainer(exxitingMoneysDTO));
    }

}
