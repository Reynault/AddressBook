package model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class AddressBook {
    private HashMap<String, Contact> contacts;

    public AddressBook(HashMap<String, Contact> contacts) {
        this.contacts = contacts;
    }

    public void addContact(Contact contact) {
        contacts.put(contact.getEmail(), contact);
    }

    public void removeContact(String email) {
        contacts.remove(email);
    }

    public Contact getContact(String email) {
        return contacts.get(email);
    }

    public void modifyContact(String oldEmail, String email, String name, String surname) {
        Contact contact = contacts.get(oldEmail);
        contacts.remove(oldEmail);
        contacts.put(email, contact);
        contact.setEmail(email);
        contact.setName(name);
        contact.setSurname(surname);
    }

    public boolean containsContact(String email) {
        return contacts.containsKey(email);
    }

    public String showContacts() {
        StringBuilder sb = new StringBuilder();

        Contact contact;
        Set<String> keys = contacts.keySet();
        Iterator<String> iterator = keys.iterator();

        sb.append("Contacts: \n\n");

        while (iterator.hasNext()) {
            contact = contacts.get(iterator.next());

            sb.append(contact.show());
            sb.append("\n");
        }


        return sb.toString();
    }

    public HashMap<String, Contact> getContacts() {
        return contacts;
    }
}
