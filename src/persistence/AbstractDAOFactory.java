package persistence;

import persistence.csv.CsvDAOFactory;
import persistence.sql.SqlDAOFactory;

public abstract class AbstractDAOFactory {

    private static AbstractDAOFactory csv = new CsvDAOFactory();
    private static AbstractDAOFactory sql = new SqlDAOFactory();

    public static AbstractDAOFactory getFactory(FactoryTypes type) {
        AbstractDAOFactory res;
        switch (type) {
            case CSV_DAO:
                res = csv;
                break;

            case SQL_DAO:
                res = sql;
                break;

            default:
                res = csv;
        }

        return res;
    }

    public abstract AddressBookDAO getAddressBookDAO();

}
