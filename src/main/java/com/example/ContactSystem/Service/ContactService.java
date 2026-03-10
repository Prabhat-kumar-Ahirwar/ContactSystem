package com.example.ContactSystem.Service;

import com.example.ContactSystem.Entity.Contacts;
import com.example.ContactSystem.ExceptionHandler.ContactNotFoundException;
import com.example.ContactSystem.ExceptionHandler.DuplicateContactException;
import com.example.ContactSystem.Repository.ContactRepo;
import lombok.AllArgsConstructor;
import org.aspectj.apache.bcel.generic.RET;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ContactService {

    private final ContactRepo contactRepo;

    public Contacts addContact(Contacts contacts) {
        if(contactRepo.existsByNumber(contacts.getNumber())){
            throw new DuplicateContactException("Phone number already exists");
        }

        return contactRepo.save(contacts);
    }

    public Page<Contacts> getAll(int page, int size){
        Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());
        return contactRepo.findAll(pageable);
    }
    public Contacts getById(long id) {
        return contactRepo.findById(id)
                .orElseThrow(() -> new ContactNotFoundException("Contact not found with id " + id));

    }

    public void remove(long id) {
        Contacts contact = contactRepo.findById(id)
                .orElseThrow(() -> new ContactNotFoundException("Contact not found with id " + id));
        contactRepo.delete(contact);
    }

    public Contacts updateContact(long id, Contacts contacts) {

        Contacts contact = contactRepo.findById(id)
                .orElseThrow(() -> new ContactNotFoundException("Contact not found with id " + id));

        if (contacts.getName() != null)
            contact.setName(contacts.getName());

        if (contacts.getNumber() != null)
            contact.setNumber(contacts.getNumber());

        if (contacts.getRelation() != null)
            contact.setRelation(contacts.getRelation());

        return contactRepo.save(contact);
    }

    public List<Contacts> getByRelation(String relation) {
        return contactRepo.findByRelation(relation);
    }

    public List<Contacts> getByName(String name) {
        return contactRepo.findByNameContaining(name);
    }

    public List<Contacts> getByNumber(int number) {
        return contactRepo.findByNumberContaining(number);
    }
}