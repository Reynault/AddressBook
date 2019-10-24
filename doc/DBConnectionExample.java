package start;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import org.apache.derby.jdbc.ClientDriver;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBConnectionExample {

    public static void main(String[] args) {
        Connection c = null;
        Statement stmt = null;
        PreparedStatement stmt2 = null;
        try {
            c = getConnection();
        } catch (SQLException e) {
            System.out.println("SQL-getconnection: " + e.getMessage());
        }
        try {

            String queryStringSelect = "SELECT * FROM JCB.CONTACT";
            String queryStringSelect2 = "SELECT * FROM JCB.CONTACT WHERE nom=?";
            //requête hardcodée
            String queryStringInsert = "INSERT INTO JCB.CONTACT (CONTACT.nom, CONTACT.prenom, CONTACT.email) VALUES ('TOTO', 'toto', 't.toto@toto.fr')";
            //requête paramétrée, mieux
            String queryStringInsert2 = "INSERT INTO Contact (nom, prenom, email) VALUES (?, ?, ?)";
            //Hardcodées, à vous de les paramétrer au besoin
            String queryStringUpdate = "UPDATE contact SET nom='Toto' WHERE nom='TOTO'";
            String queryStringDelete = "DELETE from contact where contact.nom='Toto'";

            // Création et exécution de la requete
            //Cas des requêtes hardcodées :
            stmt = c.createStatement();
            //Cas des requêtes paramétrées : traité plus loin dans le code

            /* Consultation */
            // Le résultat est stocké dans un ResultSet
            ResultSet rs = stmt.executeQuery(queryStringSelect);
            //On peut utiliser d'autres structures de données pour sauvegarder les informations
            //List<String> resultList = new ArrayList<String>();
            while (rs.next()) {
                //test = test + " " +rsg.getString("nom");
                // Exemple 1 : on affiche immédiatement les résultats
                System.out.println(rs.getString("prenom") + " " + rs.getString("nom") + " (" + rs.getString("email") + ")");
                // Exemple 2 : on stocke les noms
                //resultList.add(rsg.getString("nom"));
            }//par la suite on utilise printResulSet qui fait exactement la même chose
            rs.close();
            // Exemple 2 : on affiche pour vérifier que les noms ont bien été stockés
            //System.out.println(resultList);

            /* Insertion */
            System.out.println("\nPhase d'insertion de TOTO/toto/t.toto@toto.fr (INSERT)");
            //insertion avec une requête hardcodée
            //INSERT INTO JCB.CONTACT (CONTACT.nom, CONTACT.prenom, CONTACT.email) VALUES ('TOTO', 'toto', 't.toto@toto.fr')
            int res = stmt.executeUpdate(queryStringInsert);
            //insertion avec la requête paramétrée
            //INSERT INTO Contact (nom, prenom, email) VALUES (?, ?, ?)
            stmt2 = c.prepareStatement(queryStringInsert2);
            stmt2.setString(1, "TITI");
            stmt2.setString(2, "titi");
            stmt2.setString(3, "titi@titi.fr");
            //on l'exécute plus tard.
            stmt2.executeUpdate();

            // Vérification de l'insertion (avec une requête paramétrée)
            System.out.println("\nVérification de la mise à jour après les INSERT :");
            stmt2 = c.prepareStatement(queryStringSelect2);
            stmt2.setString(1, "TOTO");
            rs = stmt2.executeQuery();
            rs.close();

            /* Mise à jour */
            System.out.println("\nPhase de mise à jour de l'élément inséré précédemment (UPDATE)");
            //UPDATE contact SET nom='Toto' WHERE nom='TOTO';
            stmt.executeUpdate(queryStringUpdate);
            // Vérification de la mise à jour
            System.out.println("\nVérification de la mise à jour après UPDATE:");
            rs = stmt.executeQuery(queryStringSelect);
            printResultSet(rs);
            rs.close();

            /* Suppression */
            System.out.println("\nPhase de suppression de l'élément inséré précédemment (DELETE)");
            //DELETE from contact where contact.nom='TOTO';
            stmt.executeUpdate(queryStringDelete);
            // Vérification de la mise à jour
            System.out.println("\nVérification de la mise à jour après DELETE :");
            rs = stmt.executeQuery(queryStringSelect);
            printResultSet(rs);
            rs.close();
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (c != null) {
                    c.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static Connection getConnection() throws SQLException {
        Connection c = null;
        // Etablissement de la connection
        DriverManager.registerDriver(new org.apache.derby.jdbc.ClientDriver());
        //adapter cette partie en fonction du login, mot de passe et nom de la
        //base
        c = DriverManager.getConnection("jdbc:derby://localhost:1527/addressbook;create=true", "toto", "toto");
        //Autre version
        //c = DerbyPersistenceProvider.getInstance().getConnection();
        return c;
    }

    private static void printResultSet(ResultSet rs) {
        try {
            while (rs.next()) {
                System.out.println(rs.getString("prenom") + " " + rs.getString("nom") + " (" + rs.getString("email") + ")");
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
        }
    }
}
