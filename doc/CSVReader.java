package start;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/* Classe de test qui charge et lit un fichier CSV, et en affiche le contenu */

public class CSVReader {

  public static void main(String[] args) {
	  CSVReader obj = new CSVReader();
    //valeur par défaut
    String csvFileName = "ressources/addressbook.csv";
    //on peut donner un autre fichier CSV en paramètre
    if(args.length>0) {
      csvFileName = args[0];
    }
	  obj.run(csvFileName);
  }

  public void run(String inputFileName) {
	  BufferedReader br = null;
	  String line = "";
    //séparateur du CSV
	  String csvSeparator = ",";
	  try {
		  br = new BufferedReader(new FileReader(inputFileName));
      int i=0;
      //on boucle sur chaque ligne du fichier
		  while ((line = br.readLine()) != null) {
        i++;
		    // on récupère la ligne que l'on découpe en fonction du séparateur, on
        // obtient un tableau de chaînes de caractères (pour chaque ligne)
			  String[] contacts = line.split(csvSeparator);
        //Affichage dans la foulée pour montrer le résultat
			  System.out.println("Contact #"+i+" : " + contacts[1] + " " + contacts[0] + " (" + contacts[2]+ ")");
        // on aurait pu stocker les informations dans une autre structure de
        // données un peu plus optimale, bien évidemment (List, Map, etc.)
        // Ici, on perd le tableau à chaque tour de boucle, il faut donc bien
        // penser à sauvegarder les données au fur et à mesure si on souhaite
        // faire autre chose que de l'affichage immédiat
		  }

	  } catch (FileNotFoundException e) {
		  e.printStackTrace();
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

	  System.out.println("Done.");
  }

}
