package com.learning.webatm.service;

import com.learning.webatm.dto.MoneyDTO;
import com.learning.webatm.model.Money;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class MoneyServiceTest {




    public List<Money> fromDTOtoMoneyList(ModelMapper modelMapper, List<MoneyDTO> moneyDTOS){
        return moneyDTOS.stream()
                        .map(n->modelMapper.map(n, Money.class))
                        .collect(Collectors.toList());
    }
}
