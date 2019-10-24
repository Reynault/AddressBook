package persistence.csv;

import model.Contact;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

class CSVWriter {
    void generateCsvFile(String outputFileName, HashMap<String, Contact> contacts) {
        try {
            FileWriter writer = new FileWriter(outputFileName);
            String csvSeparator = ",";

            Iterator<String> iterator = contacts.keySet().iterator();
            Contact value;
            while (iterator.hasNext()){
                value = contacts.get(iterator.next());
                writer.append(value.getSurname());
                writer.append(csvSeparator);
                writer.append(value.getName());
                writer.append(csvSeparator);
                writer.append(value.getEmail());
                writer.append('\n');
            }

            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
