package sk.pocsik.view;

import org.beryx.textio.TextIO;
import sk.pocsik.model.Company;
import sk.pocsik.model.Person;
import sk.pocsik.service.CompanyService;
import sk.pocsik.service.PersonService;
import sk.pocsik.view.menoOptions.PersonMenuOptions;

public class PersonView extends BaseView implements ApplicationMenu {
    public PersonView(TextIO textIO, CompanyService companyService, PersonService personService) {
        super(textIO, companyService, personService);
    }

    @Override
    public void showMenu() {
        PersonMenuOptions selectedOption = null;
        super.textIO.getTextTerminal().println("\n----------------------\nPerson Menu\n----------------------\n");

        while (selectedOption != PersonMenuOptions.BACK) {
            selectedOption = textIO.newEnumInputReader(PersonMenuOptions.class)
                    .withAllValuesNumbered()
                    .withDefaultValue(PersonMenuOptions.BACK)
                    .read("Choose an option");

            switch (selectedOption) {
                case ADD_PERSON -> addPerson();
                case UPDATE_PERSON -> updatePerson();
                case REMOVE_PERSON -> removePerson();
                case LIST_PERSONS -> listPersons();
                case BACK -> textIO.getTextTerminal().println("Back to main menu");
            }
        }
    }

    private void updatePerson() {
        Company company = null;

        textIO.getTextTerminal().println();
        textIO.getTextTerminal().println("Updating a person");
        listPersons();
        Integer personIndex = textIO.newIntInputReader()
                .withMinVal(0)
                .withMaxVal(personService.getAll().size() - 1)
                .read("Enter the number of the person to update");
        Person person = personService.getAt(personIndex);
        textIO.getTextTerminal().println("Updating person: " + person.getName());
        textIO.getTextTerminal().print("Current details: ");
        writePersonToTerminal(personIndex, person);
        String name = textIO.newStringInputReader()
                .withDefaultValue(person.getName())
                .withInputTrimming(true)
                .read("Enter name");
        String phoneNumber = textIO.newStringInputReader()
                .withDefaultValue(person.getPhoneNumber())
                .withInputTrimming(true)
                .read("Enter phone number");
        String emailAddress = textIO.newStringInputReader()
                .withDefaultValue(person.getEmail())
                .withInputTrimming(true)
                .read("Enter email address");

        boolean isNeedCompany = textIO.newBooleanInputReader()
                .withDefaultValue(false)
                .read("Wanna change a company?");

        if (isNeedCompany && !companyService.getAll().isEmpty()) {
            listCompanies();
            int companyIndex = textIO.newIntInputReader()
                    .withMinVal(0)
                    .withMaxVal(companyService.getAll().size() - 1)
                    .withDefaultValue(companyService.getIndex(person.getCompany()))
                    .read("Enter the number of the company");
            company = companyService.getAt(companyIndex);
        } else {
            textIO.getTextTerminal().println();
            textIO.getTextTerminal().println("No company found in system.");
            textIO.getTextTerminal().println();
        }

        personService.updatePerson(person, name, phoneNumber, emailAddress, company);
    }

    private void removePerson() {
        textIO.getTextTerminal().println();
        textIO.getTextTerminal().println("Removing a person");
        listPersons();
        int personIndex = textIO.newIntInputReader()
                .withMinVal(0)
                .withMaxVal(personService.getAll().size() - 1)
                .read("Enter index of person to delete");
        try {
            personService.remove(personIndex);
        } catch (IllegalArgumentException e) {
            textIO.getTextTerminal().println("Entered person does not exist");
        }
    }

    private void addPerson() {
        Company company = null;

        textIO.getTextTerminal().println("Add a new person");
        String name = textIO.newStringInputReader().read("Enter name");
        String phone = textIO.newStringInputReader().read("Enter phone number");
        String email = textIO.newStringInputReader().read("Enter email");
        boolean isNeedCompany = textIO.newBooleanInputReader()
                .withDefaultValue(false)
                .read("Add a person to company?");

        if (isNeedCompany && !companyService.getAll().isEmpty()) {
            listCompanies();
            int companyIndex = textIO.newIntInputReader()
                    .withMinVal(0)
                    .withMaxVal(companyService.getAll().size() - 1)
                    .read("Enter the number of the company");
            company = companyService.getAt(companyIndex);
        } else {
            textIO.getTextTerminal().println();
            textIO.getTextTerminal().println("No company found in system.");
            textIO.getTextTerminal().println();
        }
        Person person = Person.builder()
                .name(name)
                .phoneNumber(phone)
                .email(email)
                .company(company)
                .build();
        companyService.addEmployee(company, person);

        personService.create(person);
    }
}
