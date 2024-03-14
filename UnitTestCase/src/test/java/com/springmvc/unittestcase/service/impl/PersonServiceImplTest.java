package com.springmvc.unittestcase.service.impl;


import com.springmvc.unittestcase.entity.PersonEntity;
import com.springmvc.unittestcase.repository.PersonRepository;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonServiceImplTest {
    @Mock
    PersonRepository personRepository;
    @InjectMocks
    PersonServiceImpl personServiceimpl;

    @Test
    void addPerson() {
//        Request
        PersonEntity personEntity = new PersonEntity();
//        Mock
        personEntity.setId(1L);
        personEntity.setName("sdcd");
        personEntity.setDetails("adscsd");
//        Mocking
        when(personRepository.save(personEntity)).thenReturn(personEntity);
//        Test
        PersonEntity entity = personServiceimpl.addPerson(personEntity);
//        Result
        verify(personRepository, Mockito.times(1)).save(personEntity);
        assertEquals(personEntity, entity);
    }

    @Test
    void search() {
//        Request
        List<PersonEntity> mockList = new ArrayList<>();
//        Mock
        mockList.add(new PersonEntity(1L, "scsdc", "scsdc"));
//        Mocking
        when(personRepository.findAll()).thenReturn(mockList);
//        Test
        List<PersonEntity> entityList = personServiceimpl.search();
//        Result
        verify(personRepository, times(1)).findAll();
        assertEquals(mockList, entityList);
    }

    @Test
    void findPersonById() {
//        Request
        Long mockId = 1L;
//        Mock
        PersonEntity personEntity = new PersonEntity(1L, "csdc", "csc");
//        Mocking
        when(personRepository.findById(mockId)).thenReturn(Optional.of(personEntity));
//        Test
        PersonEntity entity = personServiceimpl.findPersonById(mockId);
//        Result
        verify(personRepository, Mockito.times(1)).findById(mockId);
        assertEquals(personEntity, entity);
    }

    @Test
    void deletePerson() {
//        Request
        Long mockId = 1L;
//        Mock
        PersonEntity mockEntity = new PersonEntity(mockId, "dcdsc", "scsd");
//        Mocks
        when(personRepository.findById(mockId)).thenReturn(Optional.of(mockEntity));
        doNothing().when(personRepository).delete(mockEntity);
//        Test
        personServiceimpl.deletePerson(mockId);
//        Result
        verify(personRepository, times(1)).delete(mockEntity);

    }

    @Test
    void updatePersonById() {
//        Request
        Long mockId = 1L;
        PersonEntity mockPerson = new PersonEntity(mockId, "casdcd", "Csdcsd");
//        Mocks
        PersonEntity dbPerson = new PersonEntity(mockId,"patel","anything");
//        Mocking
        when(personRepository.findById(mockId)).thenReturn(Optional.of(dbPerson));
        when(personRepository.save(dbPerson)).then(i->i.getArgument(0));
//        Test
        PersonEntity actualPerson = personServiceimpl.updatePersonById(mockId, mockPerson);
//        Result
        verify(personRepository,Mockito.times(1)).findById(mockId);
        verify(personRepository, Mockito.times(1)).save(dbPerson);
        assertEquals(dbPerson, actualPerson);
    }
}