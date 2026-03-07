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


    public void remove(long id) {
        Contacts contacts = contactRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Contact Not Found"));
        contactRepo.deleteById(id);
    }

    public Contacts getById(long id) {
        Contacts contacts = contactRepo.findById(id)
                .orElseThrow(()-> new RuntimeException("Contact Not Found"));
        return contactRepo.getById(id);
    }

    public Contacts updateContact(long id, Contacts contacts) {
        Contacts contact = contactRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Contact Not Found"));
        if (contacts.getName() != null)
            contact.setName(contacts.getName());
        if (contacts.getNumber() != 0)
            contact.setNumber(contacts.getNumber());
        if (contacts.getRelation() != null)
            contact.setRelation(contacts.getRelation());
        return contactRepo.save(contact);
    }
}
