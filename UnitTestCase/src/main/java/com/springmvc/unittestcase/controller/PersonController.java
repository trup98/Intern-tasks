package com.springmvc.unittestcase.controller;

import com.springmvc.unittestcase.entity.PersonEntity;
import com.springmvc.unittestcase.service.PersonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/person")
public class PersonController {
    private final PersonService personService;

    @PostMapping("/add")
    public PersonEntity addPerson(@Valid @RequestBody PersonEntity personEntity) {
        return this.personService.addPerson(personEntity);
    }

    @GetMapping("/search")
    public List<PersonEntity> searchAll() {
        return this.personService.search();
    }

    @GetMapping("/searchById/id/{findById}")
    public PersonEntity findById(@PathVariable Long findById) {
        return this.personService.findPersonById(findById);
    }

    @DeleteMapping("delete/id/{deleteId}")
    public void deletePerson(@PathVariable Long deleteId) {
        this.personService.deletePerson(deleteId);
    }

    @PutMapping("/update/{updateId}")
    public PersonEntity updatePerson(@Valid @PathVariable Long updateId, @RequestBody PersonEntity personEntity) {
        return this.personService.updatePersonById(updateId, personEntity);
    }
}
