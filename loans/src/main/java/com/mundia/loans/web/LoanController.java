package com.mundia.loans.web;

import com.mundia.loans.dto.LoanDTO;
import com.mundia.loans.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
@RequiredArgsConstructor
public class LoanController {

    private final LoanService loanService;

    @PostMapping("/add")
    public LoanDTO createLoan(@RequestBody LoanDTO loanDTO) {
        return loanService.createLoan(loanDTO.getUserId(),loanDTO.getBookId(),loanDTO);
    }

    @PutMapping("/{loanId}/return")
    public ResponseEntity<LoanDTO> returnLoan(@PathVariable Long loanId) {
        LoanDTO loanDTO = loanService.getLoan(loanId);
        return ResponseEntity.ok(loanDTO);
    }

    @GetMapping("/user/{userId}")
    public List<LoanDTO> getLoansByUser(@PathVariable Long userId) {
        return loanService.getLoansByUser(userId);
    }

    @GetMapping("/active")
    public ResponseEntity<List<LoanDTO>> getActiveLoans() {
        return ResponseEntity.ok(loanService.getActiveLoans());
    }

    @GetMapping
    public ResponseEntity<List<LoanDTO>> getAllLoans() {
        return ResponseEntity.ok(loanService.getAllLoans());
    }
}
