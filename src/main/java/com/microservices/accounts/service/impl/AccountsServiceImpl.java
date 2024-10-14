package com.microservices.accounts.service.impl;

import com.microservices.accounts.constants.AccountsConstants;
import com.microservices.accounts.dto.CustomerDto;
import com.microservices.accounts.entity.Accounts;
import com.microservices.accounts.entity.Customer;
import com.microservices.accounts.exception.CustomerAleardyExistException;
import com.microservices.accounts.mapper.CustomerMapper;
import com.microservices.accounts.repository.accountRepo;
import com.microservices.accounts.repository.customerRepo;
import com.microservices.accounts.service.AccountsService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class AccountsServiceImpl implements AccountsService {

    private final customerRepo customerRepo;
    private final accountRepo accountRepo;
    public AccountsServiceImpl(customerRepo customerRepo, com.microservices.accounts.repository.accountRepo accountRepo) {
        this.customerRepo = customerRepo;
        this.accountRepo = accountRepo;
    }

    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.maptoCustomer(customerDto,new Customer());
        Optional<Customer> existingCustomer = customerRepo.findByMobileNumber(customerDto.getMobileNumber());
         if (existingCustomer.isPresent()) {
             throw new CustomerAleardyExistException("THIS CUSTOMER ALREADY EXISTS with mobile number : "+customerDto.getMobileNumber());
         }
         customer.setCreatedAt(LocalDateTime.now());
         customer.setCreatedBy("eman");
        Customer savedCustomer= customerRepo.save(customer);
        accountRepo.save(createNewAccount(savedCustomer));

    }

    private Accounts createNewAccount(Customer customer){
        Accounts accounts = new Accounts();

        accounts.setCustomerId(customer.getCustomerId());
        Long randomAccNumber =100000l +new Random().nextInt(90000000);
        accounts.setAccountNumber(randomAccNumber);
        accounts.setAccountType(AccountsConstants.SAVINGS);
        accounts.setBranchAddress(AccountsConstants.ADDRESS);
        accounts.setCreatedAt(LocalDateTime.now());
        accounts.setCreatedBy("eman");
        return  accounts;
    }
}
