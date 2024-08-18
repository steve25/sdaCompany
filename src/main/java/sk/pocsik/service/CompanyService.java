package sk.pocsik.service;

import sk.pocsik.model.Company;
import sk.pocsik.model.Person;

public class CompanyService extends BaseService<Company> {

    public void addEmployee(Company company, Person employee) {
        if (employee.getCompany() != null) {
            employee.getCompany().persons().remove(employee);
        }
        company.persons().add(employee);
        employee.setCompany(company);
    }

    public void removeEmployee(Company company, Person employee) {
        company.persons().remove(employee);
        employee.setCompany(null);
    }
}
