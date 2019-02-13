package model;

public class Graphe {
	public final int numNiveaux=10;
	public final int sommetsParNiveaux=10;
	public Sommet[][] niveaux;
	public String nom;
	public int id;
	
	public Graphe(){
		niveaux= new Sommet[numNiveaux][sommetsParNiveaux];
	}
	
	public Sommet[] getNiveau(int numeroNiv){
		return niveaux[numeroNiv];
	};

	public int ajoutSommet(Sommet som,int niveau){
		if(niveau<0){
			System.out.println("err : niveau negatif");
			return 0;
		}else if (niveau==0){
			if(niveaux[0][0]!=null){
				System.out.println("Deja un sommet au niveau 0");
				return 0;
			}else{
				niveaux[niveau][0]=som;
				return 1;
			}
		}else{
			int i=0;
			while((i<niveaux[niveau].length)&&(niveaux[niveau][i]!=null)){
				i++;
			}
			niveaux[niveau][i]=som;
			return 1;
		}
	};
	
	public void ajoutArc(Sommet psommetDebut,Sommet psommetFin,Double poids){
		Arc nouvelArc = new Arc(psommetDebut,psommetFin,poids);
		int i =0;
		for(i =0;(i<psommetDebut.suivants.length)&&(psommetDebut.suivants[i]!=null);i++){

		}
		psommetDebut.suivants[i]=nouvelArc;
		i =0;
		for(i =0;(i<psommetFin.precedents.length)&&(psommetFin.precedents[i]!=null);i++){

		}
		psommetFin.precedents[i]=nouvelArc;
	};
	
	public Sommet getEntree(){}

	public void afficheNiveau(int niveau){
		for (int i=0;(i<niveaux[niveau].length)&&(niveaux[niveau][i]!=null);i++){
			System.out.println(niveaux[niveau][i]);
		}
	}
	
	private Sommet getSommetByName(String nomSommet){
		int j=0,i=0;
		for(i =0; (  i<niveaux.length  )  &&  (niveaux[i][0]!=null)  ;i++){
			
			for (j = 0; (j<niveaux[i].length)  &&  (niveaux[i][j]!=null)  &&  (niveaux[i][j].getNom()!=nomSommet)  ;j++  ){}
			
		}
		return niveaux[i][j];
	}
	
}
