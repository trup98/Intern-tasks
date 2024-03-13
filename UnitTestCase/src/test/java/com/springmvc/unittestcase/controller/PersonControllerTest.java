package com.springmvc.unittestcase.controller;


import com.springmvc.unittestcase.entity.PersonEntity;
import com.springmvc.unittestcase.service.PersonService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class PersonControllerTest {
    @Mock
    PersonService personService;

    @InjectMocks
    PersonController personController;

    @Test
    void addPerson() {
//       Request
        PersonEntity mockPerson = new PersonEntity();
//        Mock
        mockPerson.setId(4L);
        mockPerson.setName("svsdfbdgbgdg");
        mockPerson.setDetails("HYcvcvvo");

//        Mocking
        when(personService.addPerson(mockPerson)).thenReturn(mockPerson);
//        Test
        PersonEntity person = personController.addPerson(mockPerson);
//        Result
        verify(personService, times(1)).addPerson(person);
        assertEquals(mockPerson, person);

    }

    @Test
    void deletePerson() {
//        Request
        Long pesonId = 1L;
//        Mock
//        Mocking
        doNothing().when(personService).deletePerson(pesonId);
//        Test
        personController.deletePerson(pesonId);
//        Result
        verify(personService, times(1)).deletePerson(pesonId);
    }

    @Test
    void updatePerson() {
//        Request
        Long personId = 1L;
//        Mock
        PersonEntity personEntity = new PersonEntity(1L, "adadc", "acadsc");
//        Mocking
        when(personService.updatePersonById(personId, personEntity)).thenReturn(personEntity);
//        Test
        PersonEntity entity = personController.updatePerson(personId, personEntity);
//        Result
        verify(personService, times(1)).updatePersonById(personId, personEntity);
//        assertEquals("adadc", entity.getName());
        assertEquals(personEntity, entity);
    }
}