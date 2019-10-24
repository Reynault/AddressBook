package persistence.csv;

import model.Contact;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

class CSVReader {

    HashMap<String, Contact> run(String inputFileName) {
        HashMap<String, Contact> values = new HashMap<>();

        BufferedReader br = null;
        String line;
        //séparateur du CSV
        String csvSeparator = ",";

        try {
            br = new BufferedReader(new FileReader(inputFileName));
            int i = 0;
            //on boucle sur chaque ligne du fichier
            while ((line = br.readLine()) != null) {
                i++;
                // On récupère la ligne que l'on découpe en fonction du séparateur, on
                // obtient un tableau de chaînes de caractères (pour chaque ligne)
                String[] contacts = line.split(csvSeparator);

                // Sauvegarde d'un contact
                values.put(contacts[2],
                        new Contact(
                                contacts[2],
                                contacts[1],
                                contacts[0]
                        )
                );
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return values;
    }

}
