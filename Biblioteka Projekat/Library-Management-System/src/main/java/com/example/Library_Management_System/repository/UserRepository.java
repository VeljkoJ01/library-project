package com.example.Library_Management_System.repository;


import com.example.Library_Management_System.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {

    List<User> findByNameContainingOrEmailContaining(String name, String email);

}
