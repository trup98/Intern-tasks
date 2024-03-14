package com.springmvc.unittestcase.service.impl;

import com.springmvc.unittestcase.entity.PersonEntity;
import com.springmvc.unittestcase.exception.PersonException;
import com.springmvc.unittestcase.repository.PersonRepository;
import com.springmvc.unittestcase.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;

    @Override
    public PersonEntity addPerson(PersonEntity personEntity) {
        return this.personRepository.save(personEntity);

    }

    @Override
    public List<PersonEntity> search() {
        return personRepository.findAll();
    }

    @Override
    public PersonEntity findPersonById(Long findById) {
        return this.personRepository.findById(findById).orElseThrow(() -> new RuntimeException("cannot find person"));

    }

    @Override
    public void deletePerson(Long deleteId) {
        PersonEntity personEntity = personRepository.findById(deleteId).orElseThrow(() -> new RuntimeException("Cannot Find Data"));
        this.personRepository.delete(personEntity);
        /*
          if (personRepository.findById(deleteId).isPresent()) {
                      this.personRepository.deleteById(deleteId);
                  } else {
                      throw new PersonException("Cannot Delete Person No Person Found");
                  }
         */
    }

    @Override
    public PersonEntity updatePersonById(Long updateId, PersonEntity personEntity) {

        PersonEntity entity = personRepository.findById(updateId).orElseThrow(() -> new PersonException("Data Not Found"));
        entity.setName(personEntity.getName());
        entity.setDetails(personEntity.getDetails());
        return this.personRepository.save(entity);

    }

}
