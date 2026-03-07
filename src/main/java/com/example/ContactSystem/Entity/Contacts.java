package com.example.ContactSystem.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "contacts")
public class Contacts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private long number;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(length = 50)
    private String relation;
}