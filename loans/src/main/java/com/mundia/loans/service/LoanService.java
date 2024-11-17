package com.mundia.loans.service;

import com.mundia.loans.dto.LoanDTO;

import java.util.List;

public interface LoanService {
    LoanDTO createLoan(Long userId, Long bookId,LoanDTO loanDTO);
    LoanDTO getLoan(Long loanId);
    List<LoanDTO> getLoansByUser(Long userId);
    List<LoanDTO> getActiveLoans();
    List<LoanDTO> getAllLoans();
}
