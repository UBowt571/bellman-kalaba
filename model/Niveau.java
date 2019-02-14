package model;

import java.util.ArrayList;

public class Niveau {
    
    private ArrayList<Sommet> sommets;
    
    /**
     * Constructeur sans paramètres
     */
    public Niveau() {
        this.sommets = new ArrayList<>();
    }
    
    /**
     * Constructeur
     * @param sommets
     */
    public Niveau(ArrayList<Sommet> sommets) {
        this.sommets = sommets;
    }
    
    /**
     * Getter sommets
     * @return sommets
     */
    public ArrayList<Sommet> getSommets() {
        return this.sommets;
    }
    
    /**
     * Setter sommets
     * @param sommets
     */
    public void setSommets(ArrayList<Sommet> sommets) {
        this.sommets = sommets;
    }
    
    /**
     * Ajoute un sommet au niveau
     * @param sommet 
     */
    public void addSommet(Sommet sommet) {
        this.sommets.add(sommet);
    }
    
}
