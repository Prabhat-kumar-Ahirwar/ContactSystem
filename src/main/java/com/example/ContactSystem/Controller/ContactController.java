package com.example.ContactSystem.Controller;

import com.example.ContactSystem.Entity.Contacts;
import com.example.ContactSystem.Service.ContactService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;
import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ContactController {

    private final ContactService contactService;

    @PostMapping("/add")
    public ResponseEntity<Contacts> addContact(@Valid @RequestBody Contacts contacts){
        Contacts contact = contactService.addContact(contacts);
        return new ResponseEntity<>(contact , HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Contacts>> getAllContacts(){
        List<Contacts> contacts = contactService.getAll();
        return new ResponseEntity<>(contacts , HttpStatus.CREATED);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteByid(@PathVariable long id){
        contactService.remove(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("{id}")
    public ResponseEntity<Contacts> getContactByid(@PathVariable long id){
        Contacts contacts = contactService.getById(id);
        return new ResponseEntity<>(contacts , HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Contacts> updateContact(@PathVariable long id , @RequestBody Contacts contacts){
        Contacts updatecontact = contactService.updateContact(id,contacts);
        return ResponseEntity.ok(updatecontact);
    }
    @GetMapping("/relation/{relation}")
    public ResponseEntity<List<Contacts>> getContactByRelation(@PathVariable String relation) {
        List<Contacts> contacts = contactService.getByRelation(relation);
        return ResponseEntity.ok(contacts);
    }

    @GetMapping("search/{name}")
    public ResponseEntity<List<Contacts>> getByName(@PathVariable String name){
        List<Contacts> contacts = contactService.getByName(name);
        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }

    @GetMapping("search/phone/{number}")
    public ResponseEntity<List<Contacts>> getByNumber(@PathVariable int number){
        List<Contacts> contacts = contactService.getByNumber(number);
        return new ResponseEntity<>(contacts,HttpStatus.OK);
    }
}
