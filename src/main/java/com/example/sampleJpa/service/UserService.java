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

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

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
        User existingUser = userRepository.findById(id).orElseThrow(() ->
                new RuntimeException("User not found with id: " + id));
        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail()); // Update other fields as necessary
        return userRepository.save(existingUser);
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
    public void deleteAll() {
        userRepository.deleteAll();
    }

}
