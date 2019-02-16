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
	
	 private static final char DEFAULT_SEPARATOR = ',';
	 private static final int MAX_SOMMETS = 50;
	
	final FileChooser fileChooser = new FileChooser();
	fileChooser.getExtensionFilters().addAll(
	         new ExtensionFilter("CSV Files", "*.csv")
	         
			);


	@FXML
	protected void handleSubmitButtonAction(ActionEvent event) {
		System.out.println("coucou");
		FileChooser fileChooser = new FileChooser(new ExtensionFilter("Filter for .csv files","*.csv"));
		File selectedFile = fileChooser.showOpenDialog(null);
 
			if (selectedFile != null) {
    			System.out.println("File selected: " + selectedFile.getAbsolutePath());
    			readCsv(selectedFile);
			}
			else {
				System.out.println("File selection cancelled.");
			}
    }
	
	
	public void readCsv(File fileToRead){
		ArrayList<ArrayList<Integer>> matrice=new ArrayList<ArrayList<Integer>>();
		try {
			Scanner scanner = new Scanner(fileToRead);
			while (scanner.hasNext()) {
				String csvLine=scanner.nextLine();
				
				//if empty, return!
		        if (csvLine == null && csvLine.isEmpty()) {
		            return;
		        }
		        
		        int lastSeparatorIndex=0;
		        int nextSeparatorIndex=0;
		        for (int i=0;(csvLine.indexOf(DEFAULT_SEPARATOR,lastSeparatorIndex)>0)&&  (i<MAX_SOMMETS) ;  i++  ){
		        	nextSeparatorIndex = csvLine.indexOf(",",lastSeparatorIndex);
		        	System.out.println("valeur numero "+i+" : "+csvLine.substring(lastSeparatorIndex,nextSeparatorIndex));
		        	
		        	lastSeparatorIndex=nextSeparatorIndex+1;
		        }
		        	        }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
