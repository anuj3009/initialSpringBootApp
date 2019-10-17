package com.anuj.springBootStudy.initialbootapp.dao;

import com.anuj.springBootStudy.initialbootapp.dao.PersonDAO;
import com.anuj.springBootStudy.initialbootapp.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeDAO")
public class FakePersonDataAccessService<personDAO> implements PersonDAO {

    private static List<Person> DB = new ArrayList<>();

    @Override
    public int insertPerson(UUID id, Person person) {
        DB.add(new Person(id,person.getName()));
        return 1;
    }

    @Override
    public List<Person> selectAllPeople() {
        return DB;
    }

    @Override
    public int deletePersonById(UUID id) {
        Optional<Person> selected = selectPersonById(id);

        if(selected.isPresent()) {
            DB.remove(selected.get());
            return 1;
        }else {
            return  0;
        }
    }

    @Override
    public int updatePersonById(UUID id, Person updatePerson) {

        Optional<Person> selected = selectPersonById(id);
        System.out.println("Selected person is "+selected.get());

        return selected.map(person -> {
            int personIndex = DB.indexOf(person);
            System.out.println("index is "+personIndex);
            if (personIndex >= 0) {
                DB.set(personIndex,new Person(id, updatePerson.getName()));
                System.out.println(updatePerson.getName());
                return 1;
            } else {
                System.out.println("inside else "+DB);
                return 0;
            }
        }).orElse(0);





    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        return DB.stream()
                .filter(person -> person.getId().equals(id))
                .findFirst();
    }
}
