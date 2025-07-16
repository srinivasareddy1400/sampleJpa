package com.example.sampleJpa.controller;

import com.example.sampleJpa.Entity.User;
import com.example.sampleJpa.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.findById(id).orElse(null);
    }

    @GetMapping("get")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    @PostMapping("save")
    public ResponseEntity<User> saveUser(@Valid @RequestBody User user) {
        User savedUser = userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        User updatedUser = userService.update(id, user);
        return ResponseEntity.ok(updatedUser);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.ok("User deleted successfully");
    }

    @GetMapping("/filterByName")
    public List<User> getUsersByName(@RequestParam String name) {
        return userService.findByNames(name);
    }

    @GetMapping("/filterByEmail")
    public List<User> getUsersByEmail(@RequestParam String email) {
        return userService.findByEmail(email);
    }

    @GetMapping("/filterByDateRange")
    public List<User> getUsersByDateRange(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {
        return userService.findUsersByDateRange(startDate, endDate);
    }

    @GetMapping("/pagination")

    public List<User> pagination(@RequestParam int page, @RequestParam int size) {
        return userService.getAllUsers(page, size);
    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<String> handleValidExceotions(MethodArgumentNotValidException ex) {
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
//    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handlemultipleex(MethodArgumentNotValidException ex) {
        StringBuilder errorMessage = new StringBuilder();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            errorMessage.append(error.getDefaultMessage()).append("\n");

        });
String finalErrorMessage=errorMessage.toString().trim();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).

                body(finalErrorMessage);

//    public Page<User> pagination(
//            @RequestParam int page, @RequestParam int size) {
//        return userService.getAllUsers(page, size);
    }

}