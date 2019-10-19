package controller;

import global.GlobalError;
import model.AddressBook;

public class MainViewController {
    private AddressBook addressBook;

    public boolean StringSanitizer(String toSanitize){

        return true;
    }

    public MainViewController(AddressBook addressBook) {
        this.addressBook = addressBook;
    }

    public GlobalError createContact(String email, String name, String surname){
        GlobalError error;
        error = addressBook.createContact(email, name, surname);
        return error;
    }

    public GlobalError removeContact(String email){
        GlobalError error;
        error = addressBook.removeContact(email);
        return error;
    }

    public GlobalError modify(String emailBefore, String name, String surname, String email){
        GlobalError error;
        error = addressBook.changeContact(emailBefore, name, surname, email);
        return error;
    }

    public GlobalError askForContact(String email){
        GlobalError error;
        error = addressBook.askForContact(email);
        return error;
    }
}
