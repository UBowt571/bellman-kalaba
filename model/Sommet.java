package model;

import java.util.ArrayList;

/**
 * Décrit les sommets d'un graphe orienté.
 * Il a été conceptualisé de sorte à pouvoir implémenter l'algorithme de 
 * Bellman-Kalaba.
 */
public class Sommet {
    
    /** L'identifiant du sommet */
    private int id;
    
    /** Le nom du sommet*/
    private String nom;
    
    /** La valeur attribuée au sommet lors de l'algo */
    private String etiquette;
    
    /** Les arcs reliés aux sommets suivants */
    private ArrayList<Arc> suivants;
    
    /** Les arcs reliés aux sommets précédents */
    private ArrayList<Arc> precedents;
    
    /**
     * Constructeur
     * @param nom le nom du sommet. Préférez un nom court.
     */
    public Sommet(String nom) {
        this.nom = nom;
        this.suivants = new ArrayList<>();
        this.precedents = new ArrayList<>();
    }
    
    /**
     * Getter nom
     * @return nom
     */
    public String getNom() {
        return this.nom;
    }
    
    /**
     * Getter suivants
     * @return suivants
     */
    public ArrayList<Arc> getSuivants() {
        return this.suivants;
    }
    
    /**
     * Getter precedents
     * @return precedents
     */
    public ArrayList<Arc> getPrecedents() {
        return this.precedents;
    }
    
    /**
     * Setter de suivants.
     * Les précédents des extrémités des arcs sont ajoutés aux sommets correspondant 
     * en même temps.
     * @param suivants les sommets à définir en tant que suivants
     * @throws IllegalArgumentException si un des arcs de la liste n'a pas this pour origine.
     */
    public void setSuivants(ArrayList<Arc> suivants) throws IllegalArgumentException {
        Arc arcCourant;
        for (int i = 0 ; i < suivants.size() ; i++) {
            arcCourant = suivants.get(i);
            if (arcCourant.getOrigine() != this) {
                throw new IllegalArgumentException("Un des arcs suivants à définir "
                    + "n'a pas ce sommet pour origine.");
            }
            arcCourant.getExtremite().addPrecedent(arcCourant);
        }
        this.suivants = suivants;
    }
    
    /**
     * Ajoute un sommet précédent à la liste
     * @param precedent le sommet à définir en tant que precedent
     */
    private void addPrecedent(Arc precedent) {
        this.precedents.add(precedent);
    }
    
}
