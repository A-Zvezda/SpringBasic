package ru.geekbrains.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.persist.Person;
import ru.geekbrains.service.PersonService;

import java.util.List;

@RequestMapping("/api/v1/person")
@RestController
public class PersonResource {

    private final PersonService personService;

    @Autowired
    public PersonResource(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping(path = "/all", produces = "application/json")
    public List<Person> findAll() {
        return personService.findAll();
    }

    @GetMapping(path = "/{id}/id", produces = "application/json")
    public Person findById(@PathVariable("id") long id) {
        return personService.findById(id)
                .orElseThrow(() -> new NotFoundException ("Person not found"));
    }

    @PostMapping
    public void createPerson(@RequestBody Person person) {
        if (personService.findById(person.getId()).isPresent()) {
            throw new AlreadyExistException("Id field found in create request");
        }
        personService.save(person);
    }

    @PutMapping
    public void updatePerson(@RequestBody Person person) {
        personService.save(person);
    }

    @DeleteMapping(path = "/{id}/id", produces = "application/json")
    public void deletePerson(@PathVariable("id") long id) {
        personService.deleteById(id);
    }

//    @ExceptionHandler
//    public ResponseEntity<String> notFoundExceptionHandler(NotFoundException exception) {
//        return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
//    }
//
//    @ExceptionHandler
//    public ResponseEntity<String>  illegalArgumentException(IllegalArgumentException exception) {
//        return new ResponseEntity<>(exception.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
//    }
}