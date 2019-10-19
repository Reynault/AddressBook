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

    public GlobalError createContact(String name, String surname, String email){
        GlobalError error;
        error = addressBook.createContact(name, surname, email);
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

    public GlobalError askForContacts(){
        GlobalError error;
        error = addressBook.askForContacts();
        return error;
    }
}
