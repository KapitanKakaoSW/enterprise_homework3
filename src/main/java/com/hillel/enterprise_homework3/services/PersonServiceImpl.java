package com.hillel.enterprise_homework3.services;

import com.hillel.enterprise_homework3.dtos.PersonDTO;
import com.hillel.enterprise_homework3.exceptions.PersonNotFoundException;
import com.hillel.enterprise_homework3.models.PersonModel;
import com.hillel.enterprise_homework3.repositories.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public void addPerson(PersonDTO personDTO) {
        PersonModel person = new PersonModel(
                personDTO.getFirstName(),
                personDTO.getLastName(),
                personDTO.getPhoneNumber(),
                personDTO.getEmail());
        personRepository.getPersons().put(person.getPersonId(), person);
    }

    @Override
    public Collection<PersonModel> getAllPersons() {
        return personRepository.getPersons().values();
    }

    @Override
    public PersonModel getPersonById(Integer id) throws PersonNotFoundException {
        if (personRepository.getPersons().containsKey(id)) {
            return personRepository.getPersons().get(id);
        } else {
            throw new PersonNotFoundException(id);
        }
    }

    @Override
    public void updatePersonById(Integer id, PersonDTO personDTO) throws PersonNotFoundException {
        if (personRepository.getPersons().containsKey(id)) {
            PersonModel person = personRepository.getPersons().get(id);
            person.setFirstName(personDTO.getFirstName());
            person.setLastName(personDTO.getLastName());
            person.setPhoneNumber(personDTO.getPhoneNumber());
            person.setEmail(personDTO.getEmail());
        } else {
            throw new PersonNotFoundException(id);
        }
    }

    @Override
    public void deletePersonById(Integer id) throws PersonNotFoundException {
        if (personRepository.getPersons().containsKey(id)) {
            personRepository.getPersons().remove(id);
        } else {
            throw new PersonNotFoundException(id);
        }
    }
}
