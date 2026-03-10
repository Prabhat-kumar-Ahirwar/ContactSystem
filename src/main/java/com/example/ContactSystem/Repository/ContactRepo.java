package com.example.ContactSystem.Repository;

import com.example.ContactSystem.Entity.Contacts;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepo extends JpaRepository<Contacts, Long > {
        List<Contacts> findByRelation(String relation);

        boolean existsByNumber(@Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits") @NotBlank(message = "Phone number is required") String number);

        List<Contacts> findByNameContaining(String name);

        List<Contacts> findByNumberContaining(int number);
}
