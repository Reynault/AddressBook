package model.contact;

public class ContactFactory {
    private static Contact defaultContact = new Contact("NONE", "NONE", "NONE");

    public static Contact constructContact(String name, String surname, String email){
        return new Contact(name, surname, email);
    }

    public static Contact getDefaultContact(){
        return defaultContact;
    }
}
