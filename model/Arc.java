package model;

/**
 * Décrit les arcs d'un graphe orienté.
 * Il a été conceptualisé de sorte à pouvoir implémenter l'algorithme de 
 * Bellman-Kalaba.
 */
public class Arc {
    
    /** L'identifiant de l'arc */
    private int id;
    
    /** L'origine de l'arc */
    private Sommet origine;
    
    /** L'extremite de l'arc */
    private Sommet extremite;
    
    /** Le poids de l'arc. Strictement positif */
    private double poids;
    
    /**
     * Constructeur
     * @param origine
     * @param extremite
     * @param poids
     * @throws IllegalArgumentException si poids négatif ou nul
     */
    public Arc(Sommet origine, Sommet extremite, double poids) throws IllegalArgumentException {
        if (poids <= 0) {
            throw new IllegalArgumentException("Le poids d'un arc doit être strictement positif.");
        }
        this.origine = origine;
        this.extremite = extremite;
        this.poids = poids;
    }
    
    /**
     * Getter origine
     * @return origine
     */
    public Sommet getOrigine() {
        return this.origine;
    }
    
    /**
     * Getter extremite
     * @return extremite
     */
    public Sommet getExtremite() {
        return this.extremite;
    }
    
    /**
     * Getter poids
     * @return poids
     */
    public double getPoids() {
        return this.poids;
    }
    
}