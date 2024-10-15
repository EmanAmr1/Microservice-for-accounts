package com.microservices.accounts.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AccountsDto {

    private String email;

    private String mobileNumber;

    @NotEmpty(message = "Account type can't be null or empty")
    private String accountType;

    @NotEmpty(message = "Branch Address type can't be null or empty")
    private String branchAddress;

    @NotEmpty(message = "AccountNumber can't be empty or null")
    @Pattern(regexp = "^$|[0-9]{10}",message = "Account Number must be 10 digits")
    private Long accountNumber;
}
