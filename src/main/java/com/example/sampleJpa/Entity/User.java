package com.example.sampleJpa.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public User(Long id) {
        this.id = id;
    }

        @NotNull(message = "Name is required")
//    @NotBlank(message = "Name cannot be blank")
    private String name;

    private String address;

//    @NotNull(message = "Email cannot be empty")
//    @NotBlank(message = "Email cannot be blank")
//    @Email(message = "Email should be valid")
    private String email;

//    @NotNull(message = "Date cannot be empty")
    private LocalDate date;

    public User(LocalDate date) {
        this.date = date;
    }

    public User(String name) {
        this.name = name;
    }
    public User(String name, String address, String email, LocalDate date) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.date = date;
    }
public User(String name ,String email){
        this.name=name;
        this.email=email;
}
}
