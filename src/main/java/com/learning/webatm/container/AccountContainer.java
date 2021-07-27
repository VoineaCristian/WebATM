package com.learning.webatm.container;

import com.learning.webatm.dto.AccountDTO;

import java.util.List;

public class AccountContainer {

    private List<AccountDTO> accounts;
    private Integer numberOfElements;

    public AccountContainer() {
    }

    public AccountContainer(List<AccountDTO> accounts) {
        this.accounts = accounts;
        this.numberOfElements = accounts.size();
    }

    public List<AccountDTO> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountDTO> accounts) {
        this.accounts = accounts;
    }

    public Integer getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(Integer numberOfElements) {
        this.numberOfElements = numberOfElements;
    }
}
