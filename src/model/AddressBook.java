package model;

import model.contact.Contact;
import model.contact.ContactFactory;

import java.util.*;

import static model.GlobalError.ALREADY_EXIST_WHILE_MODIFY;
import static model.GlobalError.SUCCESS;

public class AddressBook {
    private HashMap<String, Contact> contacts;

    public AddressBook() {
        this.contacts = new HashMap<>();
    }

    public void addContact(String name, String surname, String email){
        if(!contacts.containsKey(email)){
            contacts.put(email, ContactFactory.constructContact(
                    name, surname, email));
        }
    }

    public void removeContact(String email){
        contacts.remove(email);
    }

    public boolean hasContact(String email){
        return contacts.containsKey(email);
    }

    public Collection<Contact> getContacts(){
        return contacts.values();
    }

    public Contact getContact(String email){
        return contacts.get(email);
    }

    public GlobalError changeContact(String emailBefore, String name, String surname, String email){
        if(!email.equals(emailBefore) && contacts.containsKey(emailBefore)){
            return ALREADY_EXIST_WHILE_MODIFY;
        }else{
            Contact change = contacts.get(emailBefore);
            change.setEmail(email);
            change.setName(name);
            change.setSurname(surname);
            return SUCCESS;
        }
    }
}
