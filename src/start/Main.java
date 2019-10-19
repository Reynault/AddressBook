package start;

import controller.MainViewController;
import model.AddressBook;
import view.MainView;

public class Main {
    public static void main(String[] args) {
        AddressBook addressBook = new AddressBook();
        MainViewController controller = new MainViewController(addressBook);
        MainView mainView = new MainView(controller);

        // Adding observers
        addressBook.addObserver(mainView);

        addressBook.createContact("Bob", "Ross", "bob.ross@gmail.com");
        addressBook.createContact("Bob", "Marley", "bob.marley@gmail.com");

        // Getting existing contacts
        controller.askForContacts();
    }
}
