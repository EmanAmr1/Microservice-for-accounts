package com.microservices.loans.Mapper;

import com.microservices.loans.Dto.LoansDto;
import com.microservices.loans.Entity.Loans;

public class LoansMapper {

    public LoansDto mapLoansToDto(Loans loans, LoansDto loansDto) {
        loansDto.setLoanType(loans.getLoanType());
        loansDto.setLoanNumber(loans.getLoanNumber());
        loansDto.setAmountPaid(loans.getAmountPaid());
        loansDto.setTotalLoan(loans.getTotalLoan());
        loansDto.setOutstandingAmount(loans.getOutstandingAmount());
        loansDto.setMobileNumber(loans.getMobileNumber());
        return loansDto;
    }

    public Loans mapDtoToLoans(LoansDto loansDto , Loans loans) {
        loans.setLoanType(loansDto.getLoanType());
        loans.setLoanNumber(loansDto.getLoanNumber());
        loans.setAmountPaid(loansDto.getAmountPaid());
        loans.setTotalLoan(loansDto.getTotalLoan());
        loans.setOutstandingAmount(loansDto.getOutstandingAmount());
        loans.setMobileNumber(loansDto.getMobileNumber());
        return loans;
    }
}
