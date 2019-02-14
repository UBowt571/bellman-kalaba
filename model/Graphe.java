package model;

import java.util.ArrayList;

/**
 * Décrit un graphe orienté, sans circuit. Il est composé de niveaux, eux-même 
 * composés de sommets.
 * Il a été conceptualisé de sorte à pouvoir implémenter l'algorithme de 
 * Bellman-Kalaba.
 */
public class Graphe {
    
    /** L'identifiant du graphe */
    private int id;
    
    /** Le nom du graphe*/
    private String nom;
    
    /** Les niveaux du graphe */
    private ArrayList<Niveau> niveaux;
    
    /**
     * Constructeur
     * @param matriceAdjPonderees la matrice d'adjacences pondérées du graphe
     * @param niveauxSommets indication des niveaux pour chacun des sommets
     * @throws IllegalArgumentException s'il n'y a pas autant d'éléments dans les 
     * tables matriceAdjPonderees et niveauxSommets ou si des poids négatifs sont 
     * utilisés.
     */
    public Graphe(double[][] matriceAdjPonderees, int[] niveauxSommets) throws IllegalArgumentException {
        Sommet sommets[] = new Sommet[matriceAdjPonderees.length];
        if (matriceAdjPonderees.length != niveauxSommets.length) {
            throw new IllegalArgumentException("Les paramètres matriceAdjPonderees "
                    + "et niveauxSommets n'ont pas le même nombre d'éléments.");
        } else {
            /* Créer les sommets */
            for (int i = 0 ; i < sommets.length ; i++) {
                //0b1000001 = ASCII de 'A'
                char nomSommet = (char) (0b1000001 + i);
                sommets[i] = new Sommet(Character.toString(nomSommet));
            }
            
            /* Créer les arcs */
            for (int i = 0 ; i < matriceAdjPonderees.length ; i++) {
                ArrayList<Arc> suivants = new ArrayList<>();
                for (int j = 0 ; j < matriceAdjPonderees[i].length ; j++) {
                    if (matriceAdjPonderees[i][j] < 0) {
                        throw new IllegalArgumentException("Les poids de la matrice "
                            + "doivent être positifs (ou nul si inexistant).");
                    }
                    if (matriceAdjPonderees[i][j] > 0) {
                        suivants.add(new Arc(sommets[i], sommets[j], 
                            matriceAdjPonderees[i][j]));
                    }
                }
                sommets[i].setSuivants(suivants);
            }
            
            /* Affecte les sommets aux graphes pour chaque niveau */
            this.niveaux = new ArrayList<>();
            int niveau, nbNiveaux;
            for (int i = 0 ; i < sommets.length ; i++) {
                niveau = niveauxSommets[i];
                nbNiveaux = this.niveaux.size();
                //vérifie s'il y a assez de niveaux initialisés
                if (niveau >= nbNiveaux) {
                    //initialise les niveaux manquants
                    for (int j = nbNiveaux; j <= niveau ; j++) {
                        this.niveaux.add(new Niveau());
                    }
                }
                this.niveaux.get(niveau).addSommet(sommets[i]);
            }
            
        }
    }
    
    /** 
     * Accède à un niveau donné.
     * @return le niveau à atteindre
     */
    public Niveau getNiveau(int i) {
        return this.niveaux.get(i);
    }
    
    /** 
     * Donne le nombre de niveaux du graphe
     * @return le nombre de niveaux du graphe
     */
    public int getNbNiveaux() {
        return this.niveaux.size();
    }
	
}
