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

    @PutMapping("/{loanId}")
    public ResponseEntity<LoanDTO> updateLoan(@PathVariable Long loanId, @RequestBody LoanDTO loanDTO) {
        return ResponseEntity.ok(loanService.updateLoan(loanId, loanDTO));
    }

    @GetMapping
    public ResponseEntity<List<LoanDTO>> getAllLoans() {
        return ResponseEntity.ok(loanService.getAllLoans());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<LoanDTO>> getLoansByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(loanService.getLoansByUser(userId));
    }

    @GetMapping("/active")
    public ResponseEntity<List<LoanDTO>> getActiveLoans() {
        return ResponseEntity.ok(loanService.getActiveLoans());
    }

    @GetMapping("/{loanId}")
    public ResponseEntity<LoanDTO> getLoan(@PathVariable Long loanId) {
        return ResponseEntity.ok(loanService.getLoan(loanId));
    }

}
