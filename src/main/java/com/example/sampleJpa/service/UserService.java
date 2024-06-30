package com.example.sampleJpa.service;

import com.example.sampleJpa.Entity.User;
import com.example.sampleJpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User save(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public User update(Long id, User user) {
        Optional<User> existingUserOptional = userRepository.findById(id);
        if (existingUserOptional.isPresent()) {
            user.setId(id);
            return userRepository.save(user);
        } else {
            throw new RuntimeException("User not found with id: " + id);
        }
    }

    public List<User> findByNames(String name) {
        return userRepository.findByNameContaining(name);
    }

    public List<User> findByEmail(String email) {
        return userRepository.findByEmailContaining(email);
    }

    public List<User> findUsersByDateRange(LocalDate startDate, LocalDate endDate) {
        return userRepository.findByDateBetween(startDate, endDate);
    }


        public List<User> getAllUsers(int page, int size) {
            Pageable pageable = PageRequest.of(page, size);
            Page<User> userPage = userRepository.findAll(pageable);
            return userPage.getContent();
        }
    }

//    public Page<User> getAllUsers(int page, int size) {
//        return userRepository.findAll(PageRequest.of(page, size));


