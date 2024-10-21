package com.microservices.accounts.service.impl;

import com.microservices.accounts.constants.AccountsConstants;
import com.microservices.accounts.dto.AccountsDto;
import com.microservices.accounts.dto.CustomerDto;
import com.microservices.accounts.entity.Accounts;
import com.microservices.accounts.entity.Customer;
import com.microservices.accounts.exception.CustomerAleardyExistException;
import com.microservices.accounts.exception.ResourceNotFoundException;
import com.microservices.accounts.mapper.AccountsMapper;
import com.microservices.accounts.mapper.CustomerMapper;
import com.microservices.accounts.repository.accountRepo;
import com.microservices.accounts.repository.customerRepo;
import com.microservices.accounts.service.AccountsService;
import org.springframework.data.crossstore.ChangeSetPersister;
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
         //customer.setCreatedAt(LocalDateTime.now());
        // customer.setCreatedBy("eman");
        Customer savedCustomer= customerRepo.save(customer);
        accountRepo.save(createNewAccount(savedCustomer));

    }



    private Accounts createNewAccount(Customer customer){
        Accounts accounts = new Accounts();

        accounts.setCustomerId(customer.getCustomerId());
        Long randomAccNumber =100000l +new Random().nextInt(90000000);
        accounts.setEmail(customer.getEmail());
        accounts.setMobileNumber(customer.getMobileNumber());
        accounts.setAccountNumber(randomAccNumber);
        accounts.setAccountType(AccountsConstants.SAVINGS);
        accounts.setBranchAddress(AccountsConstants.ADDRESS);
        //accounts.setCreatedAt(LocalDateTime.now());
        //accounts.setCreatedBy("eman");
        return  accounts;
    }

    @Override
    public CustomerDto fetchAccount(String mobileNumber) {

      Customer customer=  customerRepo.findByMobileNumber(mobileNumber).orElseThrow(
                ()->new ResourceNotFoundException("Customer","Mobile Number",mobileNumber)
        );

      Accounts accounts=  accountRepo.findByCustomerId(customer.getCustomerId()).orElseThrow(
                ()->new ResourceNotFoundException("Account","Customer id",customer.getCustomerId().toString())
        );

      CustomerDto customerDto= CustomerMapper.mapToCustomerDto(new CustomerDto(),customer);
      customerDto.setAccountsDto(AccountsMapper.mapTOAccountDto(new AccountsDto(),accounts));

        return customerDto;
    }



    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdated = false;

       AccountsDto accountsDto=customerDto.getAccountsDto(); //FIRST GET ACCOUNT

       if(accountsDto!=null) { //IF ACCOUNT EXIST GET ITS ACCOUNT NUMBER AND ITS CUSTOMER

           Accounts accounts = accountRepo.findById(accountsDto.getAccountNumber()).orElseThrow(
                   () -> new ResourceNotFoundException("Account", "Account number", accountsDto.getAccountNumber().toString())
           );

           Long customerId = accounts.getCustomerId(); //GET CUSTOMER OF THIS ACCOUNT
           Customer customer = customerRepo.findById(customerId).orElseThrow( //IF EXIST SAVE IT IN CUSTOMER OBJ
                   () -> new ResourceNotFoundException("Customer", "Customer id", customerId.toString())
           );

           CustomerMapper.maptoCustomer(customerDto,customer);

           customerRepo.save(customer);
           isUpdated = true;

       }

        return isUpdated;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer =customerRepo.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "Mobile Number", mobileNumber)
        );

        accountRepo.deleteByCustomerId(customer.getCustomerId());
        customerRepo.deleteById(customer.getCustomerId());

        return true;
    }

}
