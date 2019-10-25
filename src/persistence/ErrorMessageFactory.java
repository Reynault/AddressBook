package persistence;

public class ErrorMessageFactory{

    private static String SQL_CONNECTION_ERROR = "Erreur lors de la connexion";
    private static String SQL_LOADING_ERROR = "Erreur lors du chargement des données";
    private static String SQL_SAVING_ERROR = "Erreur lors de la sauvegarde des données";

    public static String getConnectionError(){
        return SQL_CONNECTION_ERROR;
    }
    public static String getLoadingError(){ return SQL_LOADING_ERROR; }
    public static String getSavingError(){ return SQL_SAVING_ERROR; }
}
