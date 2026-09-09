package app.service;

import app.domain.DocumentTypeEnum;
import app.domain.Person;
import app.service.inputports.UserUseCase;

import java.util.List;

public class PersonService implements UserUseCase {
    @Override
    public Person createPerson(Integer id, String name, String lastName, DocumentTypeEnum documentType, String email, String password, boolean state, String ocuppation, double salary) {

        Person person = new Person(id, name, lastName, documentType, email, password, state, ocuppation, salary);

        return person;
    }

    @Override
    public Person getPersonById(Integer id) {
        return null;
    }

    @Override
    public List<Person> getAllPersons() {
        return List.of();
    }

    @Override
    public Person updatePerson(Integer id, String name, String lastName, DocumentTypeEnum documentType, String email, String password, boolean state, String ocuppation, double salary) {
        return null;
    }

    @Override
    public void deletePerson(Integer id) {

    }
}