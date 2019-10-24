package persistence.csv;

import model.AddressBook;
import model.Contact;
import persistence.AddressBookDAO;

import java.util.HashMap;

public class AddressBookCSV implements AddressBookDAO {
    private CSVReader reader;
    private CSVWriter writer;

    private String file_path_input = "ressources/csv/input";
    private String default_file_input = "addressbook.csv";
    private String full_path_input = file_path_input+"/"+ default_file_input;

    private String file_path_output = "ressources/csv/output";
    private String default_file_output = "addressbook.csv";
    private String full_path_output = file_path_output+"/"+ default_file_output;

    AddressBookCSV(){
        reader = new CSVReader();
        writer = new CSVWriter();
    }

    @Override
    public AddressBook load() {
        HashMap<String, Contact> contacts = reader.run(full_path_input);
        return new AddressBook(contacts);
    }

    @Override
    public void save(AddressBook addressBook) {
        writer.generateCsvFile(full_path_output, addressBook.getContacts());
    }
}
