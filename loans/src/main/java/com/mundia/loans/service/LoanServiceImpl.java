package com.mundia.loans.service;

import com.mundia.loans.dto.BookDTO;
import com.mundia.loans.dto.LoanDTO;
import com.mundia.loans.dto.UserDTO;
import com.mundia.loans.entity.Loan;
import com.mundia.loans.mappers.LoanMapper;
import com.mundia.loans.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class LoanServiceImpl implements LoanService{
        private final LoanRepository loanRepository;
        private final LoanMapper loanMapper;
        private final WebClient webClient;

    private UserDTO fetchUser(Long userId) {
        return webClient
                .get()
                .uri("http://localhost:8081/api/users/" + userId)
                .retrieve()
                .bodyToMono(UserDTO.class)
                .block();
    }

    private BookDTO fetchBook(Long bookId) {
        return webClient
                .get()
                .uri("http://localhost:8080/api/books/" + bookId)
                .retrieve()
                .bodyToMono(BookDTO.class)
                .block();
    }




    public LoanDTO createLoan(Long userId, Long bookId,LoanDTO loanDTO) {
            UserDTO user = fetchUser(userId); // Fetch user details from UsersService
            if (user == null) { // If user does not exist
                throw new RuntimeException("User not found with ID: " + userId); // Custom exception handling
            }
            BookDTO book = fetchBook(bookId);
            if (book == null) { // If book does not exist
                throw new RuntimeException("Book not found with ID: " + bookId); // Custom exception handling
            }
            Loan loan = loanMapper.toEntity(loanDTO);
            loan.setUserId(userId);
            loan.setBookId(bookId);
            Loan savedLoan = loanRepository.save(loan);

            LoanDTO savedLoanDTO = loanMapper.toDto(savedLoan);
            savedLoanDTO.setUserName(user.getName());
            savedLoanDTO.setBookTitle(book.getTitle());

            return savedLoanDTO;
        }


    public LoanDTO updateLoan(Long loanId, LoanDTO loanDTO) {
        // Fetch the loan to be updated
        Loan existingLoan = loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan not found with ID: " + loanId));

        // Update fields
        existingLoan.setLoanDate(loanDTO.getLoanDate());
        existingLoan.setReturnDate(loanDTO.getReturnDate());

        // Save the updated loan
        Loan updatedLoan = loanRepository.save(existingLoan);

        // Map to DTO and return
        return loanMapper.toDto(updatedLoan);
    }


    @Override
        public LoanDTO getLoan(Long loanId) {
            Loan loan = loanRepository.findById(loanId)
                    .orElseThrow(() -> new RuntimeException("Loan not found with ID: " + loanId));

            // Fetch user and book details
            UserDTO user = fetchUser(loan.getUserId());
            BookDTO book = fetchBook(loan.getBookId());

            // Map to LoanDTO
            LoanDTO loanDTO = loanMapper.toDto(loan);

            // Populate additional details
            loanDTO.setUserName(user.getName());
            loanDTO.setBookTitle(book.getTitle());

            return loanDTO;
        }

        @Override
        public List<LoanDTO> getLoansByUser(Long userId) {
            return loanRepository.findByUserId(userId).stream()
                    .map(loan -> {
                        // Fetch user and book details
                        UserDTO user = fetchUser(loan.getUserId());
                        BookDTO book = fetchBook(loan.getBookId());

                        // Map loan to LoanDTO
                        LoanDTO loanDTO = loanMapper.toDto(loan);

                        // Populate additional details
                        loanDTO.setUserName(user.getName());
                        loanDTO.setBookTitle(book.getTitle());

                        return loanDTO;
                    })
                    .collect(Collectors.toList());
        }

        @Override
        public List<LoanDTO> getActiveLoans() {
            return loanRepository.findByReturnDateIsNull().stream()
                    .map(loan -> {

                        UserDTO user = fetchUser(loan.getUserId());
                        BookDTO book = fetchBook(loan.getBookId());


                        LoanDTO loanDTO = loanMapper.toDto(loan);
                        loanDTO.setUserName(user.getName());
                        loanDTO.setBookTitle(book.getTitle());

                        return loanDTO;
                    })
                    .collect(Collectors.toList());

        }

        @Override
        public List<LoanDTO> getAllLoans() {
            return loanRepository.findAll().stream()
                    .map(loan -> {

                        UserDTO user = fetchUser(loan.getUserId());
                        BookDTO book = fetchBook(loan.getBookId());


                        LoanDTO loanDTO = loanMapper.toDto(loan);
                        loanDTO.setUserName(user.getName());
                        loanDTO.setBookTitle(book.getTitle());

                        return loanDTO;
                    })
                    .collect(Collectors.toList());

        }
}
