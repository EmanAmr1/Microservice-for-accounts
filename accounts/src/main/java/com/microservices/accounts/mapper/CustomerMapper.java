package com.microservices.accounts.mapper;

import com.microservices.accounts.dto.CustomerDto;
import com.microservices.accounts.entity.Customer;

public class CustomerMapper {

    public static CustomerDto mapToCustomerDto(CustomerDto customerDto , Customer customer) {
        customerDto.setEmail(customer.getEmail());
        customerDto.setName(customer.getName());
        customerDto.setMobileNumber(customer.getMobileNumber());
        return customerDto;
    }

    public static Customer maptoCustomer(CustomerDto customerDto , Customer customer) {
        customer.setEmail(customerDto.getEmail());
        customer.setName(customerDto.getName());
        customer.setMobileNumber(customerDto.getMobileNumber());
        return customer;
    }
}
