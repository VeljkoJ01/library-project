package com.example.Library_Management_System.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;


    @ManyToOne
    private Category category;

    @ManyToOne
    private Publisher publisher;

    @OneToMany(mappedBy = "book")
    private Set<Loan> loans;

    @OneToMany(mappedBy = "book")
    private Set<Reservation> reservations;

    @OneToMany(mappedBy = "book")
    private Set<Review> reviews;
}
