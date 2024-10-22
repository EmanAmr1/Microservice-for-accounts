package com.microservices.loans.Service;

import com.microservices.loans.Dto.LoansDto;

public interface LoansService {

    public void createLoans(String mobileNumber);

    public LoansDto fetchLoan(String mobileNumber);

    public boolean updateLoan(LoansDto loansDto);

    public boolean deleteLoan(String mobileNumber);
}
