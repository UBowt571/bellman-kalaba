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
			primaryStage.setTitle("Sample Application"); 
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
    
    public static void main(String[] args) {
        double[][] matrice = {
            {0,8,3,6,10},
            {0,0,0,0,2},
            {0,0,0,3,0},
            {0,2,0,0,4},
            {0,0,0,0,0}
        };
        int[] niveauxSommets = {0,3,1,2,4};
        Graphe g = new Graphe(matrice,niveauxSommets);
        
        //bellmanKalaba(g, true);
        
        /*circle=new Circle();
        circle.setCenterX(50.0);
        circle.setCenterY(20.0);
        circle.setRadius(10.0);
        circle.setFill(Paint.valueOf("red"));*/
        
        launch(args);
    }
}
