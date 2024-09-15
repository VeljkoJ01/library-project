package com.example.Library_Management_System.repository;

import com.example.Library_Management_System.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> {

    // Pretraga knjiga po naslovu ili nazivu kategorije
    List<Book> findByTitleContainingIgnoreCaseOrCategoryNameContainingIgnoreCase(String title, String category);
}
