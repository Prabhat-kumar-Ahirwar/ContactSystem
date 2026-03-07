package com.example.ContactSystem.Service;

import com.example.ContactSystem.Entity.Contacts;
import com.example.ContactSystem.Repository.ContactRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor

public class ContactService {
    private final ContactRepo contactRepo;

    public Contacts addContact(Contacts contacts) {
        return  contactRepo.save(contacts);
    }

    public List<Contacts> getAll() {
        return contactRepo.findAll();
    }
}
