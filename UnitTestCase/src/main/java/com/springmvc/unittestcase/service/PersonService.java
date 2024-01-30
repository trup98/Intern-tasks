package com.springmvc.unittestcase.service;

import com.springmvc.unittestcase.entity.PersonEntity;

import java.util.List;
import java.util.Optional;

public interface PersonService {
    PersonEntity addPerson(PersonEntity personEntity);

    List<PersonEntity> search();

    PersonEntity findPersonById(Long findById);

    void deletePerson(Long deleteId);

    PersonEntity updatePersonById(Long updateId, PersonEntity personEntity);
}
