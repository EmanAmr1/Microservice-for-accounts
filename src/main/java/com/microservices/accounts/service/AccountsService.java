package com.microservices.accounts.service;

import com.microservices.accounts.dto.CustomerDto;

public interface AccountsService {

    void createAccount(CustomerDto customerDto);
}
