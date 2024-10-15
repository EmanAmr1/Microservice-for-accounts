package com.microservices.accounts.controller;

import com.microservices.accounts.constants.AccountsConstants;
import com.microservices.accounts.dto.AccountsDto;
import com.microservices.accounts.dto.CustomerDto;
import com.microservices.accounts.dto.ResponseDto;
import com.microservices.accounts.service.AccountsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

@RestController
@RequestMapping(path = "/api",produces = {MediaType.APPLICATION_JSON_VALUE})
public class AccountsController {

    private final AccountsService accountsService;
    public AccountsController(AccountsService accountsService) {
        this.accountsService = accountsService;
    }

    @PostMapping("/create")
    public ResponseEntity <ResponseDto> createAccount(@RequestBody CustomerDto customerDto){

        accountsService.createAccount(customerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountsConstants.STATUS_201,AccountsConstants.MESSAGE_201));
    }

    @GetMapping("/fetch")
    public ResponseEntity <CustomerDto> fetchAccountDetails(@RequestParam String mobileNumber){
      CustomerDto customerDto = accountsService.fetchAccount(mobileNumber);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerDto);
    }


    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccount(@RequestBody CustomerDto customerDto){
     boolean isUpdated=  accountsService.updateAccount(customerDto);

     if(isUpdated){
       return ResponseEntity
               .status(HttpStatus.OK)
               .body(new ResponseDto(AccountsConstants.STATUS_200,AccountsConstants.MESSAGE_200));
     }

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseDto(AccountsConstants.STATUS_500,AccountsConstants.MESSAGE_500));

    }


    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccount(@RequestParam String mobileNumber){

      boolean isDeleted= accountsService.deleteAccount(mobileNumber);

      if(isDeleted){
      return ResponseEntity.status(HttpStatus.OK).body(
              new ResponseDto(AccountsConstants.STATUS_200,AccountsConstants.MESSAGE_200));
      }

      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
              .body(new ResponseDto(AccountsConstants.STATUS_500,AccountsConstants.MESSAGE_500));

    }
}
