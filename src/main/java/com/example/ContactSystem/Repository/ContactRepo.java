package com.example.ContactSystem.Repository;

import com.example.ContactSystem.Entity.Contacts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepo extends JpaRepository<Contacts, Long > {
}
