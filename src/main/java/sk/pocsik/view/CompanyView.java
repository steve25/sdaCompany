package sk.pocsik.view;

import org.beryx.textio.TextIO;
import sk.pocsik.model.Company;
import sk.pocsik.model.Person;
import sk.pocsik.service.CompanyService;
import sk.pocsik.service.PersonService;
import sk.pocsik.view.menoOptions.CompanyMenuOptions;

import java.util.List;

public class CompanyView implements ApplicationMenu {
    private final TextIO textIO;
    private final CompanyService companyService;
    private final PersonService personService;

    public CompanyView(TextIO textIO, CompanyService companyService, PersonService personService) {
        this.textIO = textIO;
        this.companyService = companyService;
        this.personService = personService;
    }


    @Override
    public void showMenu() {
        CompanyMenuOptions selectedOption = null;
        textIO.getTextTerminal().println("\n----------------------\nPerson Menu\n----------------------\n");

        while (selectedOption != CompanyMenuOptions.BACK) {
            selectedOption = textIO.newEnumInputReader(CompanyMenuOptions.class)
                    .withAllValuesNumbered()
                    .withDefaultValue(CompanyMenuOptions.BACK)
                    .read("Choose an option: ");

            switch (selectedOption) {
                case ADD_COMPANY -> addCompany();
                case UPDATE_COMPANY -> updateCompany();
                case REMOVE_COMPANY -> removeCompany();
                case LIST_COMPANIES -> listCompanies();
                case ADD_PERSON -> addEmployee();
                case REMOVE_PERSON -> removeEmployee();
                case BACK -> textIO.getTextTerminal().println("Back to main menu");
            }
        }
    }

    private void addEmployee() {
        textIO.getTextTerminal().println();
        textIO.getTextTerminal().println("Adding an employee");
        listCompanies();
        Integer companyIndex = textIO.newIntInputReader()
                .withMinVal(0)
                .withMaxVal(companyService.getAll().size() - 1)
                .read("Enter the number of the company to add an employee to");
        Company company = companyService.getAt(companyIndex);
        textIO.getTextTerminal().println("Adding an employee to company: " + company.name());
        var persons = personService.getAll();
        textIO.getTextTerminal().println("List of all persons in system");
        for (int i = 0; i < persons.size(); i++) {
            textIO.getTextTerminal().printf("[%d] %s%n", i, persons.get(i).getName());
        }
        Integer personIndex = textIO.newIntInputReader()
                .withMinVal(0)
                .withMaxVal(persons.size() - 1)
                .read("Enter the number of the person to add to company");
        companyService.addEmployee(company, persons.get(personIndex));
    }

    private void removeEmployee() {
        textIO.getTextTerminal().println();
        textIO.getTextTerminal().println("Removing a person");
        listCompanies();
        int companyIndex = textIO.newIntInputReader()
               .withMinVal(0)
               .withMaxVal(companyService.getAll().size() - 1)
               .read("Enter the number of the company to remove an employee");

        Company company = companyService.getAt(companyIndex);
        List<Person> employees = company.persons();

        printEmployeesOfCompany(employees);
        Integer employeeIndex = textIO.newIntInputReader()
                .withMinVal(0)
                .withMaxVal(employees.size() - 1)
                .read("Enter the number of the employee to remove");
        Person employee = employees.get(employeeIndex);
        companyService.removeEmployee(company, employee);
    }

    private void printEmployeesOfCompany(List<Person> employees) {
        for (int i = 0; i < employees.size(); i++) {
            textIO.getTextTerminal().printf("[%d] %s%n", i, employees.get(i).getName());
        }
    }

    private void updateCompany() {
        textIO.getTextTerminal().println();
        textIO.getTextTerminal().println("Updating a company");
        listCompanies();
        Integer companyIndex = textIO.newIntInputReader()
                .withMinVal(0)
                .withMaxVal(companyService.getAll().size() - 1)
                .read("Enter the number of the company to update");
        Company company = companyService.getAll().get(companyIndex);
        textIO.getTextTerminal().println("Updating company: " + company.name());
        textIO.getTextTerminal().print("Current details: ");
        writeCompany(companyIndex, company);
        String name = textIO.newStringInputReader()
                .withDefaultValue(company.name())
                .withInputTrimming(true)
                .read("Enter name");
        String address = textIO.newStringInputReader()
                .withDefaultValue(company.address())
                .withInputTrimming(true)
                .read("Enter address");
        // TODO add company
        companyService.update(companyIndex, Company.builder()
                .name(name)
                .address(address)
                .build());
    }

    private void removeCompany() {
        textIO.getTextTerminal().println();
        textIO.getTextTerminal().println("Removing a company");
        listCompanies();
        int index = textIO.newIntInputReader()
                .withMinVal(0)
                .withMaxVal(companyService.getAll().size() - 1)
                .read("Enter index of company to delete");
        try {
            companyService.remove(index);
        } catch (IllegalArgumentException e) {
            textIO.getTextTerminal().println("Entered company does not exist");
        }
    }

    private void listCompanies() {
        textIO.getTextTerminal().println();
        textIO.getTextTerminal().println("List of all persons in system");

        List<Company> companies = companyService.getAll();
        for (int i = 0; i < companies.size(); i++) {
            this.writeCompany(i, companies.get(i));
        }
        textIO.getTextTerminal().println();
        textIO.getTextTerminal().println();
    }

    private void writeCompany(int i, Company company) {
        if (company == null) {
            return;
        }

        textIO.getTextTerminal().printf("[%d] %s, %s%n", i, company.name(), company.address());
    }

    private void addCompany() {
        textIO.getTextTerminal().println("Add a new Company");
        String name = textIO.newStringInputReader().read("Enter name");
        String address = textIO.newStringInputReader().read("Enter address");

        companyService.create(Company.builder()
                .name(name)
                .address(address)
                .build());
    }
}
