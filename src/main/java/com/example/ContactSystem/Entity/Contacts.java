package com.example.ContactSystem.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.processing.Pattern;

@Entity
@Getter
@Setter
@Table(name = "contacts")
public class Contacts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
    private long number;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(length = 50)
    private String relation;
}