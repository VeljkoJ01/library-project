package com.example.Library_Management_System.repository;


import com.example.Library_Management_System.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {
}
