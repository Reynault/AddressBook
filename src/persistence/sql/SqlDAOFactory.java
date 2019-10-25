package persistence.sql;

import persistence.AbstractDAOFactory;
import persistence.AddressBookDAO;

public class SqlDAOFactory extends AbstractDAOFactory {
    @Override
    public AddressBookDAO getAddressBookDAO() {
        return AddressBookSQL.getInstance();
    }
}
