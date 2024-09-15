package com.example.Library_Management_System.controller;



import com.example.Library_Management_System.entity.*;
import com.example.Library_Management_System.repository.PublisherRepository;
import com.example.Library_Management_System.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.util.List;

@Controller
public class PageController {

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @Autowired
    private LoanService loanService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private PublisherService publisherService;


    @GetMapping("/")
    public String homePage() {
        return "index";
    }

    @GetMapping("/users")
    public String manageUsers(Model model) {
        List<User> users = userService.getAllUsers();  // Povlači sve korisnike iz baze
        model.addAttribute("users", users);
        return "users";  // mapira na users.html
    }

    @GetMapping("/books")
    public String showOrSearchBooks(@RequestParam(value = "query", required = false) String query, Model model) {
        List<Book> books;

        // Ako je pretraga prazna, prikaži sve knjige
        if (query == null || query.trim().isEmpty()) {
            books = bookService.getAllBooks();
        } else {
            books = bookService.searchBooks(query);
        }

        model.addAttribute("books", books);
        return "available-books";  // Thymeleaf template za prikaz knjiga
    }



    @GetMapping("/books/reserve")
    public String reserveBookPage(Model model) {
        List<Loan> loans = loanService.getAllLoans();  // Preuzmi sve pozajmljene knjige
        model.addAttribute("loans", loans);  // Dodaj pozajmice u model
        return "reserve-book";  // Vraća stranicu sa tabelom pozajmica
    }

    @GetMapping("/books/new-reservation")
    public String showNewReservationForm(Model model) {
        model.addAttribute("loan", new Loan());  // Kreiraj prazan objekat Loan
        model.addAttribute("books", bookService.getAllBooks());  // Prikaži sve knjige za izbor
        model.addAttribute("users", userService.getAllUsers());  // Prikaz svih korisnika

        return "new-reservation-form";  // Prikazuje formu za unos rezervacije
    }

    @PostMapping("/books/save-reservation")
    public String saveReservation(
            @RequestParam("bookId") Long bookId,
            @RequestParam("userId") Long userId,
            @RequestParam("loanDate") String loanDate,
            @RequestParam("returnDate") String returnDate) {

        // Pronađi knjigu po ID-u
        Book book = bookService.getBookById(bookId);
        User user = userService.getUserById((userId));

        // Kreiraj novu rezervaciju (Loan)
        Loan loan = new Loan();
        loan.setBook(book);
        loan.setUser(user);
        loan.setLoanDate(LocalDate.parse(loanDate));
        loan.setReturnDate(LocalDate.parse(returnDate));

        // Ovde možeš dodati korisnika ako koristiš autentikaciju
        // loan.setUser(userService.getCurrentUser());

        // Sačuvaj rezervaciju
        loanService.addLoan(loan);

        return "redirect:/books";  // Nakon unosa, preusmeri nazad na listu knjiga
    }



    @GetMapping("/categories")

    public String showCategoriesPage(Model model) {
        List<Category> categories = categoryService.getAllCategories();  // Preuzmi sve kategorije
        model.addAttribute("categories", categories);  // Dodaj kategorije u model
        return "category";  // Vraća Thymeleaf template category.html
    }

    @GetMapping("/categories/edit/{id}")
    public String showEditCategoryForm(@PathVariable("id") Long id, Model model) {
        Category category = categoryService.getCategoryById(id);  // Pronađi kategoriju po ID-u
        model.addAttribute("category", category);  // Dodaj kategoriju u model
        return "edit-category";  // Prikazuje formu za izmenu
    }
    @PostMapping("/categories/edit/{id}")
    public String saveEditedCategory(@PathVariable("id") Long id, @RequestParam("name") String name) {
        // Pronađi postojeću kategoriju
        Category category = categoryService.getCategoryById(id);
        // Ažuriraj naziv kategorije
        category.setName(name);
        // Sačuvaj ažuriranu kategoriju
        categoryService.saveCategory(category);

        return "redirect:/categories";  // Preusmeri nazad na stranicu sa kategorijama
    }

    @GetMapping("/add-book-publisher")
    public String showAddBookForm(Model model) {
        // Dodaj kategorije i izdavače samo ako su potrebni za formu
        List<Category> categories = categoryService.getAllCategories(); // Sve kategorije
        List<Publisher> publishers = publisherService.getAllPublishers(); // Svi izdavači

        model.addAttribute("categories", categories); // Prosledi kategorije u model
        model.addAttribute("publishers", publishers); // Prosledi izdavače u model

        return "add-book-publisher"; // Prikaz forme
    }

    // POST metoda za obradu dodavanja knjige
    @PostMapping("/add-book-publisher/submit")
    public String addNewBook(@RequestParam("title") String title,
                             @RequestParam("author") String author,
                             @RequestParam("categoryId") Long categoryId,
                             @RequestParam("publisherId") Long publisherId) {

        bookService.addBook(title, author, categoryId, publisherId); // Dodaj novu knjigu
        return "redirect:/books"; // Preusmeri na stranicu sa svim knjigama
    }

    @PostMapping("/submit-publisher")
    public String addNewPublisher(@RequestParam("publisherName") String publisherName) {
        Publisher publisher = new Publisher();
        publisher.setName(publisherName);
        publisherService.addPublisher(publisher); // Dodaj novog izdavača

        return "redirect:/add-book-publisher"; // Preusmeri nazad na formu
    }
}