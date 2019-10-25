package start;

import model.AddressBook;
import model.Contact;
import persistence.AbstractDAOFactory;
import persistence.AddressBookDAO;
import persistence.FactoryTypes;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static int REMOVE_CONTACT = 100;
    private static int MODIFY_CONTACT = 200;

    private static AddressBook addressBook;
    private static AddressBookDAO addressBookDAO;
    private static Scanner sc;

    public static void main(String[] args) {

        addressBook = new AddressBook(new HashMap<>());
        addressBookDAO = AbstractDAOFactory.getFactory(
                FactoryTypes.SQL_DAO
        ).getAddressBookDAO();

        sc = new Scanner(System.in);

        boolean running = true;
        int selectedOption;

        while (running) {
            System.out.println("Action to perfom :\n" +
                    "1- Show contacts\n" +
                    "2- Add contact\n" +
                    "3- Remove contact\n" +
                    "4- Modify contact\n" +
                    "5- Save\n"+
                    "6- Load\n"+
                    "7- Exit");

            try {
                selectedOption = sc.nextInt();
                sc.nextLine();

            } catch (InputMismatchException e) {
                selectedOption = 5;
            }


            switch (selectedOption) {
                case 1:
                    showContact();
                    break;
                case 2:
                    addContact();
                    break;
                case 3:
                    changeContact(REMOVE_CONTACT);
                    break;
                case 4:
                    changeContact(MODIFY_CONTACT);
                    break;
                case 5:
                    save();
                    break;
                case 6:
                    load();
                    break;
                case 7:
                default:
                    running = false;
                    break;
            }
        }

    }

    private static void showContact() {
        System.out.println(addressBook.showContacts());
        System.out.println("<Press enter to continue>");
        sc.nextLine();
    }

    private static void addContact() {
        String submittedEmail, submittedName, submittedSurname;

        System.out.println("Create a contact - Email ?");

        submittedEmail = sc.nextLine();

        System.out.println("Name ?");

        submittedName = sc.nextLine();

        System.out.println("Surname ?");

        submittedSurname = sc.nextLine();

        addressBook.addContact(
                new Contact(
                        submittedEmail,
                        submittedName,
                        submittedSurname
                )
        );
    }

    private static void changeContact(int code) {
        boolean found = false;

        String submittedEmail, submittedNewEmail, submittedName, submittedSurname;
        int submittedValue;
        Contact contact;

        while (!found) {

            if (code == REMOVE_CONTACT) {
                System.out.println("Remove a contact - Email ?");
            } else if (code == MODIFY_CONTACT) {
                System.out.println("Modify a contact - Email ?");
            }

            submittedEmail = sc.nextLine();
            contact = addressBook.getContact(submittedEmail);

            if (contact != null) {

                System.out.println("This one ?\n1- yes\n2- no");
                System.out.println(contact.show());
                submittedValue = sc.nextInt();
                sc.nextLine();

                if (submittedValue == 1) {
                    found = true;

                    if (code == REMOVE_CONTACT) {
                        addressBook.removeContact(submittedEmail);

                    } else if (code == MODIFY_CONTACT) {
                        System.out.println("New email ?");
                        submittedNewEmail = sc.nextLine();

                        if (addressBook.containsContact(submittedEmail)) {
                            System.out.println("\nEmail already exists\n");
                        } else {
                            System.out.println("New name ?");
                            submittedName = sc.nextLine();
                            System.out.println("New surname ?");
                            submittedSurname = sc.nextLine();
                            addressBook.modifyContact(
                                    submittedEmail,
                                    submittedNewEmail,
                                    submittedName,
                                    submittedSurname
                            );
                        }
                    }
                }
            } else {
                System.out.println("\nContact not found\n");
                found = true;
            }
        }
    }

    private static void load(){
        addressBook = addressBookDAO.load();
    }

    private static void save(){
        addressBookDAO.save(addressBook);
    }
}
