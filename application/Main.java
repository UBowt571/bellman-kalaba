package application;
	
import java.util.ArrayList;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Paint;
import javafx.fxml.FXMLLoader;

import javafx.scene.shape.*;

import model.*;


public class Main extends Application {
	private Circle[] table;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			table = new Circle[2];
	        table[0]=new Circle();
	        table[0].setCenterX(50.0);
	        table[0].setCenterY(20.0);
	        table[0].setRadius(10.0);
	        table[0].setFill(Paint.valueOf("red"));
	        table[1]=new Circle();
	        table[1].setCenterX(20.0);
	        table[1].setCenterY(20.0);
	        table[1].setRadius(10.0);
	        table[1].setFill(Paint.valueOf("green"));
			System.out.println("start");
	        
			//BorderPaneCircles root = (BorderPaneCircles)FXMLLoader.load(getClass().getResource("GraphDisplayer.fxml"));
			//Creating a Group object  
		    Group root = new Group(table);
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Sample Application"); 
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		System.out.println("main");
		double[][] matrice = {
            {0,6,8,10},
            {0,0,2,0},
            {0,0,0,2},
            {0,0,0,0}
		};
		int[] niveauxSommets = {0,1,2,3};
		Graphe g = new Graphe(matrice,niveauxSommets);
        
		System.out.println(g.getNbNiveaux());
		Niveau currentNiv = g.getNiveau(2);
		Sommet premierSommet = currentNiv.getSommets().get(0);
		model.Arc premierArc = premierSommet.getPrecedents().get(0);

        System.out.println(premierArc.getOrigine().getNom());
		
		System.out.println(g.getNiveau(2)
            .getSommets().get(0)
            .getPrecedents().get(1).getOrigine().getNom());
        System.out.println(g.getNiveau(2)
            .getSommets().get(0)
            .getSuivants().get(0).getExtremite().getNom());
        
		launch(args);
	}
}
