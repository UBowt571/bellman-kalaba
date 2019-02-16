package application;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

import model.*;
import application.bellmanKalaba;


public class GraphController {
	
	/** Séparateur par défaut */
	private static final char DEFAULT_SEPARATOR = ',';

	/** Matrice actuelle à traiter */
	ArrayList<ArrayList<Double>> matrice;

	/** Matrice 1 ligne des niveaux de sommets */
	ArrayList<Integer> niveaux;

	/** Nombre de sommets maximum */
	private static final int MAX_SOMMETS = 50;
	
	/** Graphe � utiliser */
	private Graphe g;

	@FXML Label FileName;

	@FXML TextArea textArea;

	/**
     * Bouton d'action pour rechercher un fichier de matrice
     */
	@FXML
	protected void browseFiles(ActionEvent event) {

		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Selectionnez la matrice");
		fileChooser.getExtensionFilters().addAll(
			new ExtensionFilter("Text Files", "*.csv")
		);
		File folderToStart= new File(new String("C:\\Users\\Admin\\workspace-MARS(2)"));
		fileChooser.setInitialDirectory(folderToStart);

		File selectedFile = fileChooser.showOpenDialog(null);
 
			if (selectedFile != null) {
				System.out.println("File selected: " + selectedFile.getAbsolutePath());
				FileName.setText("File selected: "+selectedFile.getName());
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
		matrice=new ArrayList<ArrayList<Double>>();
		try {
			Scanner scanner = new Scanner(fileToRead);

			int nbNiveaux=MAX_SOMMETS;
			for(int i = 0;(  (scanner.hasNext()  )  &&  (i<nbNiveaux)  )  ;  i++  ){
				String csvLine=scanner.nextLine();
				
				//if empty, return!
		        if (csvLine == null && csvLine.isEmpty()) {
		            return null;
		        }
		        
				matrice.add(csvToDbl(csvLine));
				if(i==0){
					nbNiveaux=matrice.get(0).size();
				}
			}
			
			String csvLine=scanner.nextLine();
			csvLine=scanner.nextLine();
			niveaux=csvToInt(csvLine);
			

			return matrice;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return matrice;
	}

	/**
     * Retourne une ligne d'un tableau .csv sous forme de "double"
     * @param csvLine Ligne .csv à lire
     */
	public ArrayList<Double> csvToDbl(String csvLine){
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

	/**
     * Retourne une ligne d'un tableau .csv sous forme de "Integer"
     * @param csvLine Ligne .csv à lire
     */
	public ArrayList<Integer> csvToInt(String csvLine){
		ArrayList<Integer> ligne = new ArrayList<Integer>(); // Ligne à remplir

		int lastSeparatorIndex=0;	// Précédent séparateur
		int nextSeparatorIndex=0;	// Prochain séparateur
		for (int i=0;(csvLine.indexOf(DEFAULT_SEPARATOR,lastSeparatorIndex)>0)&&  (i<MAX_SOMMETS) ;  i++  ){

			nextSeparatorIndex = csvLine.indexOf(DEFAULT_SEPARATOR,lastSeparatorIndex);
			
			Integer value = new Integer(  csvLine.substring(lastSeparatorIndex,nextSeparatorIndex)  );
			
			ligne.add(value);
			
			lastSeparatorIndex=nextSeparatorIndex+1;
		}

		return ligne;
	}
	
	/**
     * Lance la résolution du chemin avec l'algorithme de Bellman - Kalaba
     */
	public void startBellmanKalaba(){
		System.out.println("Debut Algo bellmanK");
		g=new Graphe(fromDoubleArraylistToDoubleTable(matrice),fromIntegerArraylistToIntegerTable(niveaux));
		bellmanKalaba.textArea=this.textArea;
		bellmanKalaba.start(g, true);
	}

	/**
     * Converti d'un ArrayList en 2D à un tableau en 2D
     * @param input tableau à convertir
	 * @return tableau converti
     */
	public double[][] fromDoubleArraylistToDoubleTable(ArrayList<ArrayList<Double>> input){
		double[][] resultMatrix = new double[input.size()][input.size()];
		for (int i = 0; i<input.size();i++){
			for (int j = 0; j<input.get(i).size();j++){
				resultMatrix[i][j]=input.get(i).get(j);
			}
		}
		return resultMatrix;
	}

	/**
     * Converti d'un ArrayList à un tableau
     * @param input tableau à convertir
	 * @return tableau converti
     */
	public int[] fromIntegerArraylistToIntegerTable(ArrayList<Integer> input){
		int[] resulTable = new int[input.size()];
		for (int i = 0; i<input.size();i++){
				resulTable[i]=input.get(i);
		}
		return resulTable;
	}
}
