package com.example.Library_Management_System.controller;

import com.example.Library_Management_System.entity.Book;
import com.example.Library_Management_System.entity.Category;
import com.example.Library_Management_System.entity.Publisher;
import com.example.Library_Management_System.entity.User;
import com.example.Library_Management_System.service.BookService;
import com.example.Library_Management_System.service.CategoryService;
import com.example.Library_Management_System.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;



}
