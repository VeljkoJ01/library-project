package com.example.Library_Management_System.repository;


import com.example.Library_Management_System.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan,Long> {
}
