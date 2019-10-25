package persistence;


import model.AddressBook;

public interface AddressBookDAO {

    AddressBook load();

    void save(AddressBook addressBook);
}
