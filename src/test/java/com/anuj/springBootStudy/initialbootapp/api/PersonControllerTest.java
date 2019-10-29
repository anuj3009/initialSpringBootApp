package com.anuj.springBootStudy.initialbootapp.api;

import com.anuj.springBootStudy.initialbootapp.model.Person;
import com.anuj.springBootStudy.initialbootapp.service.PersonService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonControllerTest {

    private MockMvc mockMvc;

    @Mock
    PersonService personService;

    @InjectMocks
    PersonController personController;

    @Before
    public void setUp() throws Exception {

        mockMvc = MockMvcBuilders.standaloneSetup(personController).build();

        Mockito.when(personService.getAllPeople()).thenReturn(makeTestPerson());


    }



    @Test
    public void getAllPeople() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/person"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    private List<Person> makeTestPerson() {
        List<Person> persons = new ArrayList<>();

        persons.add(new Person(UUID.randomUUID(),"Anuj Patait"));
        persons.add(new Person(UUID.randomUUID(),"Konica Patait"));

        return  persons;
    }
}