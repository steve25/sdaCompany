package sk.pocsik.view;

import org.beryx.textio.TextIO;
import sk.pocsik.view.menoOptions.MainMenuOptions;

public class MainView implements ApplicationMenu{
    private final TextIO textIO;
    private final CompanyView companyView;
    private final PersonView personView;

    public MainView(TextIO textIO, PersonView personView, CompanyView companyView) {
        this.textIO = textIO;
        this.companyView = companyView;
        this.personView = personView;
    }

    @Override
    public void showMenu() {
        MainMenuOptions selectedOption = null;
        textIO.getTextTerminal().println("\n----------------------\nMain Menu\n----------------------\n");

        while (selectedOption != MainMenuOptions.EXIT) {
            selectedOption = textIO.newEnumInputReader(MainMenuOptions.class)
                    .withAllValuesNumbered()
                    .withDefaultValue(MainMenuOptions.EXIT)
                    .read("Choose an option: ");

            switch (selectedOption) {
                case PERSON -> personView.showMenu();
                case COMPANY -> companyView.showMenu();
                case EXIT -> textIO.dispose();
            }
        }
    }
}
