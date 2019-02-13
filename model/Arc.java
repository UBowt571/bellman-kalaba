package model;

public class Arc {
	public Double poids;
	public Sommet sommetDebut;
	public Sommet sommetFin;
	public int id;
	
	public Arc(Sommet psommetDebut,Sommet psommetFin,Double poids){
		
		this.sommetDebut=psommetDebut;
		this.sommetFin=psommetFin;
		this.poids=poids;
		
	}
}
