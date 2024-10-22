package com.microservices.loans.Controller;

import com.microservices.loans.Constants.LoansConstants;
import com.microservices.loans.Dto.LoansDto;
import com.microservices.loans.Dto.ResponseDto;
import com.microservices.loans.Service.LoansService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
public class LoansController {

    private final LoansService loansService;
    public LoansController(LoansService loansService) {
        this.loansService = loansService;
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createLoan(@RequestParam String mobileNumber) {

        loansService.createLoans(mobileNumber);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(LoansConstants.STATUS_201, LoansConstants.MESSAGE_201));
    }

    @GetMapping("/fetch")
    public ResponseEntity<LoansDto> fetchLoan(@RequestParam String mobileNumber) {

      LoansDto loansDto=  loansService.fetchLoan(mobileNumber);

        return ResponseEntity.status(HttpStatus.OK)
                .body(loansDto);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateLoan(@RequestParam LoansDto loansDto) {
        boolean isUpdated=loansService.updateLoan(loansDto);
        if(isUpdated) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(LoansConstants.STATUS_200, LoansConstants.MESSAGE_200));
        }else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(LoansConstants.STATUS_417,LoansConstants.MESSAGE_417_UPDATE));
        }

    }

}
