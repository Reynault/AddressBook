package persistence.sql;

import model.AddressBook;
import model.Contact;
import persistence.AddressBookDAO;
import persistence.ErrorMessageFactory;

import java.sql.*;
import java.util.HashMap;
import java.util.Iterator;

public class AddressBookSQL implements AddressBookDAO {

    private Connection c;
    private String username;
    private String password;
    private String databaseName;

    private String getAllQuery = "SELECT * FROM CONTACT";
    private String getAContactQuery = "SELECT * FROM CONTACT" +
            "   WHERE email=?";
    private String insertContactQuery = "" +
            "INSERT INTO Contact (nom, prenom, email) " +
            "   VALUES (?, ?, ?)";
    private String updateContactQuery = "" +
            "UPDATE contact SET nom=?, prenom=? WHERE email=?";
    private String deleteContactQuery = "" +
            "DELETE from contact where contact.email=?";

    private static AddressBookSQL instance;

    public static AddressBookSQL getInstance() {
        if (instance == null) {
            instance = new AddressBookSQL();
        }
        return instance;
    }

    private AddressBookSQL() {
        try {
            // Creating database connection
            DriverManager.registerDriver(new org.apache.derby.jdbc.ClientDriver());

            username = "root";
            password = "root";
            databaseName = "addressbook";

            c = DriverManager.getConnection("jdbc:derby://localhost:1527/" + databaseName + ";create=true",
                    username,
                    password);

        } catch (SQLException e) {
            System.out.println(
                    ErrorMessageFactory.getConnectionError() +
                            ": " + e.getMessage()
            );
        }
    }

    @Override
    public AddressBook load() {
        AddressBook addressBook = new AddressBook();
        Contact contact;
        try {
            // Fetching contacts from database in a form of
            // an hashmap<Email, contact>
            HashMap<String, Contact> contacts = new HashMap<>();

            Statement stmt = c.createStatement();

            stmt.executeQuery(getAllQuery);

            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                contact = new Contact(
                        rs.getString("email"),
                        rs.getString("nom"),
                        rs.getString("prenom")
                );

                contacts.put(rs.getString("email"),
                        contact);
            }

            addressBook = new AddressBook(contacts);

        } catch (SQLException e) {
            System.out.println(ErrorMessageFactory.getLoadingError() +
                    ": " + e.getMessage());
        }
        return addressBook;
    }

    @Override
    public void save(AddressBook addressBook) {
        HashMap<String, Contact> contacts = addressBook.getContacts();

        Iterator<String> keys;
        String currentKey;
        Contact currentContact;
        PreparedStatement stmt;

        try {
            stmt = c.prepareStatement(getAllQuery);
            ResultSet rs = stmt.executeQuery();

            // Deleting contacts
            while(rs.next()){
                currentKey = rs.getString("email");
                // If the database contains something that is not
                // in the addressBook, it must be removed
                if(!addressBook.containsContact(currentKey)){
                    stmt = c.prepareStatement(deleteContactQuery);
                    stmt.setString(1, currentKey);
                    stmt.execute();
                }
            }

            keys = contacts.keySet().iterator();
            // Inserting and updating every new contact
            while (keys.hasNext()) {
                currentKey = keys.next();
                currentContact = contacts.get(currentKey);

                // Checking if already exists in the database
                stmt = c.prepareStatement(
                        getAContactQuery
                );

                stmt.setString(1,
                        currentContact.getEmail());

                rs = stmt.executeQuery();

                if (rs.next()) {
                    // If the contact is in the DB, updating

                    stmt = c.prepareStatement(updateContactQuery);

                    stmt.setString(1, currentContact.getName());
                    stmt.setString(2, currentContact.getSurname());
                    stmt.setString(3, currentContact.getEmail());

                    stmt.execute();

                } else {
                    // Otherwise, inserting

                    stmt = c.prepareStatement(insertContactQuery);

                    stmt.setString(1, currentContact.getName());
                    stmt.setString(2, currentContact.getSurname());
                    stmt.setString(3, currentContact.getEmail());

                    stmt.execute();

                }
            }

        } catch (SQLException e) {
            System.out.println(ErrorMessageFactory.getSavingError() +
                    ": " + e.getMessage());
        }

    }
}
