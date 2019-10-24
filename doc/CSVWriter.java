import java.io.FileWriter;
import java.io.IOException;

/* 
 * Classe de test qui écrit un fichier CSV 
 * Cette classe n'est pas du tout générique et devrait être adaptée
 */

public class CSVWriter {

  public static void main(String[] args) {
    //Nom du fichier de sortie par défaut
    String output = "output.csv";
    if(args.length>0) {
      output = args[0];
    }
	  generateCsvFile(output); 
  }

  private static void generateCsvFile(String outputFileName) {
	  try {
	    FileWriter writer = new FileWriter(outputFileName);

	    String csvSeparator = ",";

	    writer.append("Bar");
	    writer.append(csvSeparator);
	    writer.append("Foo");
	    writer.append(csvSeparator);
	    writer.append("foo.bar@test.net");
	    writer.append('\n');

	    writer.flush();
	    writer.close();
	  } catch(IOException e) {
	    e.printStackTrace();
    } 
  }
}
