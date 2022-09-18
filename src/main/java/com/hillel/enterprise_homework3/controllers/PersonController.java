package com.hillel.enterprise_homework3.controllers;

import com.hillel.enterprise_homework3.dtos.PersonDTO;
import com.hillel.enterprise_homework3.exceptions.NotFoundException;
import com.hillel.enterprise_homework3.models.PersonModel;
import com.hillel.enterprise_homework3.services.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
@RequestMapping("/persons")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping(value = "/add")
    public ResponseEntity<String> addPerson(@RequestBody PersonDTO personDTO) {
        personService.addPerson(personDTO);
        return new ResponseEntity<>(personDTO.getFirstName() + " " + personDTO.getLastName() + " is registered",
                HttpStatus.OK);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<Collection<PersonModel>> getAllPersons() {
        return new ResponseEntity<>(personService.getAllPersons(), HttpStatus.OK);
    }

    @GetMapping(value = "/person")
    public ResponseEntity<PersonModel> getPersonById(@RequestParam Integer id) throws Exception {
        return new ResponseEntity<>(personService.getPersonById(id), HttpStatus.OK);
    }

    @PutMapping(value = "/update")
    public ResponseEntity<String> updatePersonById(@RequestParam Integer id, @RequestBody PersonDTO personDTO)
            throws NotFoundException {
        personService.updatePersonById(id, personDTO);
        return new ResponseEntity<>("Person with id " + id + " is updated", HttpStatus.OK);
    }

    @DeleteMapping(value = "/remove")
    public ResponseEntity<String> deletePersonById(@RequestParam Integer id) throws Exception {
        personService.deletePersonById(id);
        return new ResponseEntity<>("Person with id " + id + "is deleted", HttpStatus.OK);
    }
}
