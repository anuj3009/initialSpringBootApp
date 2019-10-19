package com.anuj.springBootStudy.initialbootapp.service;

import com.anuj.springBootStudy.initialbootapp.dao.PersonDAO;
import com.anuj.springBootStudy.initialbootapp.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * This class will have all the business logic
 */
@Service
public class PersonService {

    private final PersonDAO personDao;

    @Autowired
    public PersonService(@Qualifier("fakeDAO") PersonDAO personDao) {
        this.personDao = personDao;

    }

    public int addPerson(Person person) {
        return  personDao.insertPerson(person);
    }

    public List<Person> getAllPeople() {
        return personDao.selectAllPeople();
    }

    public Optional<Person> getPersonById(UUID id) {
        return personDao.selectPersonById(id);
    }

    public int deletePerson(UUID id) {
        return personDao.deletePersonById(id);
    }

    public int updatePerson(UUID id, Person newPerson) {
        return personDao.updatePersonById(id,newPerson);
    }
}
