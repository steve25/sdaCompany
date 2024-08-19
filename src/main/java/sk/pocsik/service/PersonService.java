package sk.pocsik.service;

import sk.pocsik.model.Company;
import sk.pocsik.model.Person;

public class PersonService extends BaseService<Person> {

    public void updatePerson(Person person, String name, String phoneNumber, String emailAddress, Company company) {
        person.setName(name);
        person.setPhoneNumber(phoneNumber);
        person.setEmail(emailAddress);
        person.setCompany(company);
    }
}
