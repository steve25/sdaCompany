package sk.pocsik.service;

import sk.pocsik.model.Company;
import sk.pocsik.model.Person;

public class CompanyService extends BaseService<Company> {

    public void addEmployee(Company company, Person employee) {
        if (employee.getCompany() != null) {
            employee.getCompany().getPersons().remove(employee);
        }
        company.getPersons().add(employee);
        employee.setCompany(company);
    }

    public void removeEmployee(Company company, Person employee) {
        company.getPersons().remove(employee);
        employee.setCompany(null);
    }
}
