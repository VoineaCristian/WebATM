package com.learning.webatm.container;

import com.learning.webatm.dto.MoneyDTO;

import java.util.List;

public class MoneyContainer {

    private List<MoneyDTO> moneys;
    private Integer numberOfElements;

    public MoneyContainer() {
    }

    public MoneyContainer(List<MoneyDTO> moneys) {
        this.moneys = moneys;
        this.numberOfElements = moneys.size();
    }

    public List<MoneyDTO> getMoneys() {
        return moneys;
    }

    public void setMoneys(List<MoneyDTO> moneys) {
        this.moneys = moneys;
    }

    public Integer getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(Integer numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

}
