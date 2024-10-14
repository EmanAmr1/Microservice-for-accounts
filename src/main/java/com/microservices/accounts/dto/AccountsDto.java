package com.microservices.accounts.dto;

import lombok.Data;

@Data
public class AccountsDto {

    private String email;

    private String mobileNumber;

    private String accountType;

    private String branchAddress;
}
