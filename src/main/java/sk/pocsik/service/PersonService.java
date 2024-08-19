package sk.pocsik.service;

import sk.pocsik.model.Person;

public class PersonService extends BaseService<Person> {

    public void updatePerson(Person person, String name, String phoneNumber, String emailAddress) {
        person.setName(name);
        person.setPhoneNumber(phoneNumber);
        person.setEmail(emailAddress);
    }
}
