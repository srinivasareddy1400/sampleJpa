package com.example.sampleJpa.repository;

import com.example.sampleJpa.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByNameContaining(String name);
    List<User> findByEmailContaining(String email);
    List<User> findByDateBetween(LocalDate startDate, LocalDate endDate);
}
