package sk.pocsik;

import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import sk.pocsik.service.CompanyService;
import sk.pocsik.service.PersonService;
import sk.pocsik.view.CompanyView;
import sk.pocsik.view.MainView;
import sk.pocsik.view.PersonView;

public class Main {
    public static void main(String[] args) {
        TextIO textIO = TextIoFactory.getTextIO();

        PersonService personService = new PersonService();
        CompanyService companyService = new CompanyService();

        PersonView personView = new PersonView(textIO, companyService, personService);
        CompanyView companyView = new CompanyView(textIO, companyService, personService);
        MainView mainView = new MainView(textIO, personView, companyView);

        mainView.showMenu();
    }
}