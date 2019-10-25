package persistence;

import persistence.csv.CsvDAOFactory;
import persistence.sql.SqlDAOFactory;

public abstract class AbstractDAOFactory {

    public static AbstractDAOFactory getFactory(FactoryTypes type) {
        AbstractDAOFactory res;
        switch (type) {
            case SQL_DAO:
                res = new SqlDAOFactory();
                break;

            case CSV_DAO:
            default:
                res = new CsvDAOFactory();
        }

        return res;
    }

    public abstract AddressBookDAO getAddressBookDAO();

}
