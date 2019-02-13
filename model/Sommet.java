package model;

public class Sommet {
	public int id;
	public Double etiquette;
	private String nom;
	private Arc[] suivants=new Arc[10];
	private Arc[] precedents=new Arc[10];
	
	public Sommet(String nom){
		this.nom=nom;
	}
	
	public Arc[] getPrecedents(){
		return precedents;
	};
	
	public String toString(){
		return "Sommet "+nom;
	}
	
	public String getNom(){
		return nom;
	}
}
