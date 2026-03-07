package com.example.ContactSystem.Controller;

import com.example.ContactSystem.Entity.Contacts;
import com.example.ContactSystem.Service.ContactService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class ContactController {

    private final ContactService contactService;

    @PostMapping("/add")
    public ResponseEntity<Contacts> addContact(@RequestBody Contacts contacts){
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
}
