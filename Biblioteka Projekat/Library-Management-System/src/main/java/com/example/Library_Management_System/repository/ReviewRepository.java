package com.example.Library_Management_System.repository;


import com.example.Library_Management_System.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review,Long> {
}
