package com.example.Library_Management_System.controller;

import com.example.Library_Management_System.entity.User;
import com.example.Library_Management_System.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public String listUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    // Prikaz forme za dodavanje novog korisnika
    @GetMapping("/add")
    public String showAddUserForm(Model model) {
        model.addAttribute("user", new User());
        return "add-user";
    }

    // Dodavanje novog korisnika
    @PostMapping("/add")
    public String addUser(@RequestParam("name") String name,
                          @RequestParam("email") String email) {
        User newUser = new User();
        newUser.setName(name);
        newUser.setEmail(email);

        userService.addUser(newUser);

        return "redirect:/users";
    }

    // Brisanje korisnika
    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }

    @GetMapping("/edit/{id}")
    public String showEditUserForm(@PathVariable Long id, Model model) {
        User user = userService.getUserById(id);  // Pronađi korisnika po ID-u
        model.addAttribute("user", user);  // Dodaj korisnika u model za prikaz
        return "edit-user";  // Thymeleaf template za izmenu korisnika
    }

    // Ažuriranje korisnika
    @PostMapping("/edit/{id}")
    public String editUser(@PathVariable Long id, @ModelAttribute User user) {
        userService.updateUser(id, user);  // Ažuriraj korisnika u bazi
        return "redirect:/users";  // Preusmeri nazad na listu korisnika nakon izmene
    }

    @GetMapping("/search")
    public String searchUsers(@RequestParam("query") String query, Model model) {
        List<User> users = userService.searchUsers(query);  // Poziva servis za pretragu
        model.addAttribute("users", users);  // Dodaj rezultate pretrage u model
        return "users";  // Vrati isti Thymeleaf template sa rezultatima pretrage
    }

}
