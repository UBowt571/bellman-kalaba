package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("GraphDisplayer.fxml"));
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		double[][] matrice = {
            {0,6,8,10},
            {0,0,2,0},
            {0,0,0,2},
            {0,0,0,0}
		};
		int[] niveauxSommets = {0,1,2,3};
		Graphe g = new Graphe(matrice,niveauxSommets);
        
        System.out.println(g.getNbNiveaux());
        System.out.println(g.getNiveau(2)
            .getSommets().get(0)
            .getPrecedents().get(0).getOrigine().getNom());
        System.out.println(g.getNiveau(2)
            .getSommets().get(0)
            .getPrecedents().get(1).getOrigine().getNom());
        System.out.println(g.getNiveau(2)
            .getSommets().get(0)
            .getSuivants().get(0).getExtremite().getNom());
		launch(args);
	}
}
