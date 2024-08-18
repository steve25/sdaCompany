package sk.pocsik.view;

import org.beryx.textio.TextIO;
import sk.pocsik.model.Person;
import sk.pocsik.service.PersonService;
import sk.pocsik.view.menoOptions.PersonMenuOptions;

import java.util.List;

public class PersonView implements ApplicationMenu {

    private final TextIO textIO;
    private final PersonService personService;

    public PersonView(TextIO textIO, PersonService personService) {
        this.textIO = textIO;
        this.personService = personService;
    }

    @Override
    public void showMenu() {
        PersonMenuOptions selectedOption = null;
        textIO.getTextTerminal().println("\n----------------------\nPerson Menu\n----------------------\n");

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
        // TODO add company
        personService.update(personIndex, Person.builder()
                .name(name)
                .phoneNumber(phoneNumber)
                .email(emailAddress)
                .build());
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

    private void listPersons() {
        textIO.getTextTerminal().println();
        textIO.getTextTerminal().println("List of all persons in system");

        List<Person> persons = personService.getAll();
        for (int i = 0; i < persons.size(); i++) {
            this.writePersonToTerminal(i, persons.get(i));
        }
        textIO.getTextTerminal().println();
        textIO.getTextTerminal().println();
    }

    private void writePersonToTerminal(Integer seqNo, Person person) {
        if (person == null) {
            return;
        }
        String companyName;
        if (person.getCompany() == null) {
            companyName = "Unknown company";
        } else {
            companyName = person.getCompany().name();
        }
        textIO.getTextTerminal().printf("[%d] %s: %s, %s (%s)%n", seqNo, person.getName(), person.getPhoneNumber(), person.getEmail(), companyName);
    }

    private void addPerson() {
        textIO.getTextTerminal().println("Add a new person");
        String name = textIO.newStringInputReader().read("Enter name");
        String phone = textIO.newStringInputReader().read("Enter phone number");
        String email = textIO.newStringInputReader().read("Enter email");

        personService.create(Person.builder()
                .name(name)
                .phoneNumber(phone)
                .email(email)
                .build());
    }
}
