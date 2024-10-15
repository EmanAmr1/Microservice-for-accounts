package com.microservices.accounts.service;

import com.microservices.accounts.dto.CustomerDto;
import com.microservices.accounts.entity.Customer;

public interface AccountsService {

    void createAccount(CustomerDto customerDto);

    CustomerDto fetchAccount(String mobileNumber );

    boolean updateAccount(CustomerDto customerDto);
}
