package sk.pocsik.view;

import org.beryx.textio.TextIO;
import sk.pocsik.model.Company;
import sk.pocsik.model.Person;
import sk.pocsik.service.CompanyService;
import sk.pocsik.service.PersonService;

import java.util.List;

public abstract class BaseView {
    protected final TextIO textIO;
    protected final CompanyService companyService;
    protected final PersonService personService;

    public BaseView(TextIO textIO, CompanyService companyService, PersonService personService) {
        this.textIO = textIO;
        this.companyService = companyService;
        this.personService = personService;
    }

    protected void listPersons() {
        textIO.getTextTerminal().println();
        textIO.getTextTerminal().println("List of all persons in system");

        List<Person> persons = personService.getAll();
        for (int i = 0; i < persons.size(); i++) {
            this.writePersonToTerminal(i, persons.get(i));
        }
        textIO.getTextTerminal().println();
        textIO.getTextTerminal().println();
    }

    protected void writePersonToTerminal(Integer seqNo, Person person) {
        if (person == null) {
            return;
        }

        String companyName = person.getCompany() == null
                ? "Unknown company"
                : person.getCompany().getName();

        textIO.getTextTerminal().printf("[%d] %s: %s, %s (%s)%n", seqNo, person.getName(), person.getPhoneNumber(), person.getEmail(), companyName);
    }

    protected void listCompanies() {
        textIO.getTextTerminal().println();
        textIO.getTextTerminal().println("List of all companies in system");

        List<Company> companies = companyService.getAll();

        for (int i = 0; i < companies.size(); i++) {
            this.writeCompany(i, companies.get(i));
        }
        textIO.getTextTerminal().println();
        textIO.getTextTerminal().println();
    }

    protected void writeCompany(int i, Company company) {
        if (company == null) {
            return;
        }

        textIO.getTextTerminal().printf("[%d] %s, %s%n", i, company.getName(), company.getAddress());
    }
}
