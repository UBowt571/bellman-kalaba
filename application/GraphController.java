package application;

public class GraphController {
	public model.Graphe currentGraph;
	public GraphController(){
		System.out.println("debut");
		this.currentGraph=new model.Graphe();
		currentGraph.ajoutSommet(new model.Sommet("A"),0);
		currentGraph.ajoutSommet(new model.Sommet("B"),1);
		currentGraph.ajoutSommet(new model.Sommet("C"),2);
		currentGraph.ajoutSommet(new model.Sommet("D"),3);
		currentGraph.ajoutSommet(new model.Sommet("E"),4);
		currentGraph.ajoutSommet(new model.Sommet("F"),2);
		
		currentGraph.ajoutArc("A", "B", 5.0);
		
		for(int i=0;currentGraph.niveaux[i][0]!=null;i++){
			
			currentGraph.afficheNiveau(i);
			
		}
		
	}
	
	
	
	
	
}
