package com.example.sampleJpa.Entity;

import jakarta.persistence.*;
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

        private String name;
        private String address;
        private String email;

        @Column(name = "date")
        private LocalDate date;
    }


