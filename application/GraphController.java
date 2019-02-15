package application;


import java.io.File;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class GraphController {
	public model.Graphe currentGraph;
	
	//@FXML
	//Menu menuFile;
	
	final FileChooser fileChooser = new FileChooser();

	@FXML
	protected void handleSubmitButtonAction(ActionEvent event) {
		System.out.println("coucou");
		FileChooser fileChooser = new FileChooser();
		File selectedFile = fileChooser.showOpenDialog(null);
 
			if (selectedFile != null) {
 
    			System.out.println("File selected: " + selectedFile.getName());
			}
			else {
				System.out.println("File selection cancelled.");
			}
    }
}
