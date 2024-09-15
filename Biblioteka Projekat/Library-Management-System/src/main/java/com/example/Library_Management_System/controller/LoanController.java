package com.example.Library_Management_System.controller;
import com.example.Library_Management_System.entity.Loan;
import com.example.Library_Management_System.repository.LoanRepository;
import com.example.Library_Management_System.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loans")
public class LoanController {

    @Autowired
    private LoanRepository loanRepository;

    public List<Loan> getAllLoans() {
        return loanRepository.findAll();  // Preuzmi sve pozajmljene knjige
    }
}
