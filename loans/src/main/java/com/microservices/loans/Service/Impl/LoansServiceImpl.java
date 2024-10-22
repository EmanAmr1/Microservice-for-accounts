package com.microservices.loans.Service.Impl;

import com.microservices.loans.Constants.LoansConstants;
import com.microservices.loans.Dto.LoansDto;
import com.microservices.loans.Entity.Loans;
import com.microservices.loans.Exception.LoanAlreadyExistsException;
import com.microservices.loans.Repo.LoansRepo;
import com.microservices.loans.Service.LoansService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class LoansServiceImpl implements LoansService {

    private final LoansRepo loansRepo;
    public LoansServiceImpl(LoansRepo loansRepo) {
        this.loansRepo = loansRepo;
    }

    public void createLoans(String mobileNumber){

        Optional<Loans> existLoan = loansRepo.findByMobileNumber(mobileNumber);
        if(existLoan.isPresent()){
            throw new LoanAlreadyExistsException("Loan already exists with mobile number "+mobileNumber);
        }

        loansRepo.save(createNewLoan(mobileNumber));
    }

    private Loans createNewLoan(String mobileNumber){
        Loans newLoan = new Loans();
        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        newLoan.setLoanNumber(Long.toString(randomLoanNumber));
        newLoan.setMobileNumber(mobileNumber);
        newLoan.setLoanType(LoansConstants.HOME_LOAN);
        newLoan.setTotalLoan(LoansConstants.NEW_LOAN_LIMIT);
        newLoan.setAmountPaid(0);
        newLoan.setOutstandingAmount(LoansConstants.NEW_LOAN_LIMIT);
        return newLoan;
    }
}