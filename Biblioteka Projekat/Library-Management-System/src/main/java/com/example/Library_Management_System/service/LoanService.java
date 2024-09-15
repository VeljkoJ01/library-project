package com.example.Library_Management_System.service;
import com.example.Library_Management_System.entity.Loan;
import com.example.Library_Management_System.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    public List<Loan> getAllLoans() {
        return loanRepository.findAll();  // Preuzmi sve pozajmljene knjige
    }

    public void addLoan(Loan loan) {
        loanRepository.save(loan);
    }
}
