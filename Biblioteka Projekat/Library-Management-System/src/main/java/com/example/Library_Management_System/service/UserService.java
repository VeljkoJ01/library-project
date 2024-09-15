package com.example.Library_Management_System.service;

import com.example.Library_Management_System.entity.User;
import com.example.Library_Management_System.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);  // Pronađi korisnika po ID-u
    }

    public void addUser(User user) {
        userRepository.save(user);  // Sačuvaj korisnika u bazi
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);  // Izbriši korisnika iz baze po ID-u
    }

    public void updateUser(Long id, User user) {
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser != null) {
            existingUser.setName(user.getName());
            existingUser.setEmail(user.getEmail());
            userRepository.save(existingUser);  // Ažuriraj postojećeg korisnika
        }
    }

    public List<User> searchUsers(String query) {
        return userRepository.findByNameContainingOrEmailContaining(query, query);  // Pretraga po imenu ili email-u
    }
}