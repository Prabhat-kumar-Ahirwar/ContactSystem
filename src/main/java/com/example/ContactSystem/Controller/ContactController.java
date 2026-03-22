package com.example.ContactSystem.Controller;

import com.example.ContactSystem.Entity.Contacts;
import com.example.ContactSystem.Service.ContactService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contacts")
@AllArgsConstructor
public class ContactController {

    private final ContactService contactService;

    // Add Contact
    @PostMapping("/add")
    public ResponseEntity<Contacts> addContact(@Valid @RequestBody Contacts contacts){
        Contacts contact = contactService.addContact(contacts);
        return new ResponseEntity<>(contact , HttpStatus.CREATED);
    }

    // Get All Contacts with Pagination
    @GetMapping("/all")
    public ResponseEntity<Page<Contacts>> getAllContacts(
            @RequestParam int page,
            @RequestParam int size){

        Page<Contacts> contacts = contactService.getAll(page, size);
        return ResponseEntity.ok(contacts);
    }

    // Delete Contact
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteByid(@PathVariable long id){
        contactService.remove(id);
        return ResponseEntity.noContent().build();
    }

    // Get Contact by ID
    @GetMapping("/{id}")
    public ResponseEntity<Contacts> getContactByid(@PathVariable long id){
        Contacts contacts = contactService.getById(id);
        return ResponseEntity.ok(contacts);
    }

    // Update Contact
    @PutMapping("/{id}")
    public ResponseEntity<Contacts> updateContact(
            @PathVariable long id,
            @Valid @RequestBody Contacts contacts){

        Contacts updatecontact = contactService.updateContact(id,contacts);
        return ResponseEntity.ok(updatecontact);
    }

    // Get Contacts by Relation
    @GetMapping("/relation/{relation}")
    public ResponseEntity<List<Contacts>> getContactByRelation(@PathVariable String relation) {
        List<Contacts> contacts = contactService.getByRelation(relation);
        return ResponseEntity.ok(contacts);
    }

    // Search by Name with Pagination
    @GetMapping("/search/{name}")
    public ResponseEntity<Page<Contacts>> getByName(
            @PathVariable String name,
            @ParameterObject Pageable pageable){

        Page<Contacts> contacts = contactService.getByName(name,pageable);
        return ResponseEntity.ok(contacts);
    }
    @GetMapping("/search/phone/{number}")
    public ResponseEntity<List<Contacts>> getByNumber(@PathVariable String number){
        List<Contacts> contacts = contactService.getByNumber(number);
        return ResponseEntity.ok(contacts);
    }
    @GetMapping("/search/email/{email}")
    public ResponseEntity<List<Contacts>> getByEmail(
            @PathVariable
            @Pattern(
                    regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$",
                    message = "Invalid email format"
            ) String email) {

        List<Contacts> contacts = contactService.getByEmail(email);
        return ResponseEntity.ok(contacts);
    }
    @GetMapping("/sorted")
    public ResponseEntity<List<Contacts>> getSortedContacts(
            @RequestParam(defaultValue = "name") String field){

        List<Contacts> contacts = contactService.getSorted(field);
        return ResponseEntity.ok(contacts);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countContacts(){
        long count = contactService.countContacts();
        return ResponseEntity.ok(count);
    }
}