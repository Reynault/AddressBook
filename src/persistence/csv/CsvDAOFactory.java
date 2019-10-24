package persistence.csv;

import persistence.AbstractDAOFactory;
import persistence.AddressBookDAO;

public class CsvDAOFactory extends AbstractDAOFactory {
    @Override
    public AddressBookDAO getAddressBookDAO() {
        return new AddressBookCSV();
    }
}
