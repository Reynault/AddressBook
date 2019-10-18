package model.contact;

public class ContactFactory {
    public static Contact constructContact(String name, String surname, String email){
        return new Contact(name, surname, email);
    }
}
