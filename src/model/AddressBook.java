package model;

import global.GlobalError;
import global.GlobalUpdate;
import model.contact.Contact;
import model.contact.ContactFactory;

import java.util.Collection;
import java.util.HashMap;
import java.util.Observable;

import static global.GlobalError.*;
import static global.GlobalUpdate.*;

public class AddressBook extends Observable {
    private HashMap<String, Contact> contacts;
    private Contact currentContact;

    public AddressBook() {
        this.contacts = new HashMap<>();
        currentContact = ContactFactory.getDefaultContact();
    }

    public GlobalError createContact(String name, String surname, String email) {
        if (!contacts.containsKey(email)) {
            contacts.put(email, ContactFactory.constructContact(
                    name, surname, email));
            update(CREATE);
            return SUCCESS;
        } else {
            return ALREADY_EXIST;
        }
    }

    public GlobalError removeContact(String email) {
        contacts.remove(email);
        if (email.equals(currentContact.getEmail())) {
            currentContact = ContactFactory.getDefaultContact();
        }
        update(DELETE);
        return SUCCESS;
    }

    public GlobalError hasContact(String email) {
        if (contacts.containsKey(email)) {
            return YES;
        } else {
            return NO;
        }
    }

    public Collection<Contact> getContacts() {
        return contacts.values();
    }

    public GlobalError askForContact(String email) {
        if (contacts.containsKey(email)) {
            currentContact = contacts.get(email);
            update(GET);
            return SUCCESS;
        } else {
            return NONEXISTANT;
        }
    }

    public Contact getContact(String email) {
        return currentContact;
    }

    public GlobalError changeContact(String emailBefore, String name, String surname, String email) {
        if (!email.equals(emailBefore) && contacts.containsKey(emailBefore)) {
            return ALREADY_EXIST_WHILE_MODIFY;
        } else {
            Contact change = contacts.get(emailBefore);
            change.setEmail(email);
            change.setName(name);
            change.setSurname(surname);
            update(CHANGE);
            return SUCCESS;
        }
    }

    private void update(GlobalUpdate code){
        setChanged();
        notifyObservers(code);
    }

    public GlobalError askForContacts() {
        update(GET_ALL);
        return SUCCESS;
    }
}
