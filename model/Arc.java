package model;

public class Arc {
	public static int compteurID;
	public Double poids;
	public Sommet origine;
	public Sommet extremite;
	public int id;
	
	public Arc(Sommet psommetDebut,Sommet psommetFin,Double poids){
		this.sommetDebut=psommetDebut;
		this.sommetFin=psommetFin;
		this.poids=poids;
	}
}
