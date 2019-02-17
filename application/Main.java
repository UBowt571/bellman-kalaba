package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.*;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
            /** Routine par défaut du chargement du fichier .fxml */
			VBox root = (VBox)FXMLLoader.load(getClass().getResource("GraphDisplayer.fxml"));
			Scene scene = new Scene(root,637,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("BellmanKalaba"); 
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
    
    public static void main(String[] args) {
        launch(args);
    }
}
