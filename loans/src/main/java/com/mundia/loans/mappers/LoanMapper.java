package com.mundia.loans.mappers;

import com.mundia.loans.dto.LoanDTO;
import com.mundia.loans.entity.Loan;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;


@Component
public class LoanMapper {
    public LoanDTO toDto(Loan loan){
        LoanDTO loanDTO = new LoanDTO();
        BeanUtils.copyProperties(loan,loanDTO);
        return loanDTO;
    }

    public Loan toEntity(LoanDTO loanDTO){
        Loan loan = new Loan();
        BeanUtils.copyProperties(loanDTO,loan);
        return loan;
    }
}
