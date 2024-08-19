package sk.pocsik.service;

import sk.pocsik.model.Person;

public class PersonService extends BaseService<Person> {

    public void updatePerson(Integer personIndex, String name, String phoneNumber, String emailAddress) {
        Person person = super.getAt(personIndex);

        person.setName(name);
        person.setPhoneNumber(phoneNumber);
        person.setEmail(emailAddress);

        super.update(personIndex, person);
    }
}
