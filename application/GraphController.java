package application;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class GraphController {
	
	/** Séparateur par défaut */
	private static final char DEFAULT_SEPARATOR = ',';
	
	/** Nombre de sommets maximum */
	private static final int MAX_SOMMETS = 50;


	/**
     * Bouton d'action pour rechercher un fichier de matrice
     */
	@FXML
	protected void handleSubmitButtonAction(ActionEvent event) {
		System.out.println("coucou");
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Selectionnez la matrice");
		fileChooser.getExtensionFilters().addAll(
			new ExtensionFilter("Text Files", "*.csv")
		);

		File selectedFile = fileChooser.showOpenDialog(null);
 
			if (selectedFile != null) {
    			System.out.println("File selected: " + selectedFile.getAbsolutePath());
    			readCsv(selectedFile);
			}
			else {
				System.out.println("File selection cancelled.");
			}
    }
	
	/**
     * Permet de lire un fichier .csv et de retourner une matrice de "double"
     * @param fileToRead fichier .csv à lire
     */
	public ArrayList<ArrayList<Double>> readCsv(File fileToRead){

		/** Matrice de taille dynamique */
		ArrayList<ArrayList<Double>> matrice=new ArrayList<ArrayList<Double>>();
		try {
			Scanner scanner = new Scanner(fileToRead);

			while (scanner.hasNext()) {
				String csvLine=scanner.nextLine();
				
				//if empty, return!
		        if (csvLine == null && csvLine.isEmpty()) {
		            return;
		        }
		        
		        matrice.add(csvToInt(csvLine));
		    }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
     * Retourne une ligne d'un tableau .csv sous forme de "double"
     * @param csvLine Ligne .csv à lire
     */
	public ArrayList<Double> csvToInt(String csvLine){
		ArrayList<Double> ligne = new ArrayList<Double>(); // Ligne à remplir

		int lastSeparatorIndex=0;	// Précédent séparateur
		int nextSeparatorIndex=0;	// Prochain séparateur
		for (int i=0;(csvLine.indexOf(DEFAULT_SEPARATOR,lastSeparatorIndex)>0)&&  (i<MAX_SOMMETS) ;  i++  ){

			nextSeparatorIndex = csvLine.indexOf(DEFAULT_SEPARATOR,lastSeparatorIndex);
			
			Double value = new Double(  csvLine.substring(lastSeparatorIndex,nextSeparatorIndex)  );
			
			ligne.add(value);
			
			lastSeparatorIndex=nextSeparatorIndex+1;
		}

		return ligne;
	}
	
}
