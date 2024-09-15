package com.example.Library_Management_System.service;

import com.example.Library_Management_System.entity.Book;


import com.example.Library_Management_System.entity.Category;
import com.example.Library_Management_System.entity.Publisher;
import com.example.Library_Management_System.repository.BookRepository;
import com.example.Library_Management_System.repository.CategoryRepository;
import com.example.Library_Management_System.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();  // Preuzmi sve knjige iz baze
    }

    // Pretraga knjiga po naslovu ili kategoriji
    public List<Book> searchBooks(String query) {
        return bookRepository.findByTitleContainingIgnoreCaseOrCategoryNameContainingIgnoreCase(query, query);
    }

    public Book getBookById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.orElse(null);  // Vraća knjigu ako postoji, inače vraća null
    }

    public void addBook(String title, String author, Long categoryId, Long publisherId) {
        // Pronađi kategoriju i izdavača po ID-u
        Category category = categoryRepository.findById(categoryId).orElse(null);
        Publisher publisher = publisherRepository.findById(publisherId).orElse(null);

        // Kreiraj novu knjigu
        Book newBook = new Book();
        newBook.setTitle(title);
        newBook.setAuthor(author);
        newBook.setCategory(category);
        newBook.setPublisher(publisher);

        // Sačuvaj knjigu u bazi
        bookRepository.save(newBook);
    }
}
