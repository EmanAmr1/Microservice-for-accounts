package com.microservices.accounts.mapper;

import com.microservices.accounts.dto.AccountsDto;
import com.microservices.accounts.entity.Accounts;

public class AccountsMapper {

    public static AccountsDto mapTOAccountDto(AccountsDto accountsDto, Accounts accounts){

        accountsDto.setAccountType(accounts.getAccountType());
        accountsDto.setBranchAddress(accounts.getBranchAddress());
        accountsDto.setEmail(accounts.getEmail());
        accountsDto.setMobileNumber(accounts.getMobileNumber());
        return accountsDto;
    }

    public static Accounts mapTOAccounts(Accounts accounts ,AccountsDto accountsDto){
        accounts.setAccountType(accountsDto.getAccountType());
        accounts.setBranchAddress(accountsDto.getBranchAddress());
        accounts.setEmail(accountsDto.getEmail());
        accounts.setMobileNumber(accountsDto.getMobileNumber());
        return accounts;
    }
}
