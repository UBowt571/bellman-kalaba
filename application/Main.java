package application;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;
import model.*;

public class Main {
    
    /**
     * Recherche du (des) chemin(s) optimal(aux) avec l'algorithme de Bellman-Kalaba
     * @param g le graphe dont on veut le chemin
     * @param details indique si l'affichage des détails de l'algo est souhaité
     */
	@SuppressWarnings("unchecked")
    public static void bellmanKalaba(Graphe g, boolean details) {
        Sommet entree; //le sommet de niveau 0 du graphe g 
        int nbNiveaux; //le nombre de niveaux du graphe
        ArrayList<Sommet> sommetsNivCourant; //les sommets du niveau en cours de traitement
        ArrayList<Arc> precedents; //les arcs reliant les précédents du sommet en traitement
        double etiquetteTemporaire; //utilisé pour déterminer l'étiquette du sommet en traitement 
        
        /* Initialisation */
        entree = g.getEntree();
        nbNiveaux = g.getNbNiveaux();
        entree.setEtiquette(0);
        
        /* Parcours des sommets niveau par niveau */
        if (details) {
            System.out.println("DETERMINATION DU (DES) CHEMIN(S) OPTIMAL(AUX)");
        }
        for (int i = 1 ; i < nbNiveaux ; i++) {
            if (details) {
                System.out.println("Niveau en traitement : " + i);
            }
            sommetsNivCourant = g.getNiveau(i).getSommets();
            /* recherche de la valeur (etiquette + poids) minimale pour chaque précédents du sommet courant s */
            for (Sommet s : sommetsNivCourant) {
                if (details) {
                    System.out.println("  Sommet en traitement : " + s);
                }
                precedents = s.getPrecedents();
                //défini une valeur de référence pour la recherche du minimum
                s.setEtiquette(precedents.get(0).getOrigine().getEtiquette() + precedents.get(0).getPoids());
                if (details) {
                    System.out.print("    Valeurs des precedents (etiquette + poids) :");
                }
                for (Arc precedent : precedents) {
                    //valeur à comparer
                    etiquetteTemporaire = precedent.getOrigine().getEtiquette() + precedent.getPoids();
                    if (details) {
                        System.out.print(" " + etiquetteTemporaire);
                    }
                    if (etiquetteTemporaire <= s.getEtiquette()) {
                        s.setEtiquette(etiquetteTemporaire);
                        //retient le chemin minimum pris
                        s.addPrecedentOptimal(precedent.getOrigine()) ;
                    }
                }
                if (details) {
                    System.out.println();
                    System.out.println("    Etiquette determinee : " + s.getEtiquette());
                    System.out.print  ("    Sommets precedents des chemins optimaux :");
                    ArrayList<Sommet> precedentsOptimaux = s.getPrecedentsOptimaux();
                    for (Sommet p : precedentsOptimaux) {
                        System.out.print(" " + p);
                    }
                    System.out.println();
                }
            }
        }
        
        /* Dépilage des chemins optimaux */
        if (details) {
            System.out.println();
            System.out.println("DEPILAGE DES CHEMINS");
        }
        Sommet extremite; //le sommet courant lors du parcours inverse
        ArrayList<String> chemins = new ArrayList<>(); //chemins optimaux représentés par des chaînes de caractères
        
        int c; //indice du chemin courant
        ArrayList<Integer> indicesCheminsTraites = new ArrayList<>(); //indices des chemins déjà traiter 
                                                                      //(utiliser pour mettre c à jour)
        int nbChCrees = 0; //nombre de chemins alternatifs créés
        String chInitial; //chemin de base utilisé pour créer les chemins alternatifs (cas d'embranchements)
                
        int i; //indice de sommet précédent
        ArrayList<Sommet> sommetsPrecedents; //les sommets précédents l'extrémité courante lors de la construction du chemin
        Sommet sommetPrecedent; //sommet précédent de extremite en traitement
        
        Stack<Sommet> pile = new Stack<>(); //pile des sommets à traiter
        
        /* Initialisation */
        extremite = g.getSortie();
        pile.add(extremite);
        chemins.add("");
        c = 0;
        //concatène le sommet au chemin courant
        chemins.set(c, extremite.toString() + chemins.get(c));
        
        /* 
         * Pour chaque sommet de la pile, on insère les précédents dans la pile
         * et on duplique le chemin en y ajoutant un précédent, excepté pour le 
         * dernier sommet traité ou s'il n'y en a qu'un seul qui est ajouté au 
         * chemin courant.
         */
        while (!pile.isEmpty()) {
            extremite = pile.pop();
            chInitial = chemins.get(c);
            sommetsPrecedents = extremite.getPrecedentsOptimaux();
            //le dernier sommet précédent traité est le dernier à être inséré dans la pile
            //et donc le prochain à être traîté. Puisque qu'il est inséré dans le chemin 
            //courant, c n'a pas besoin de changer.
            for (i = sommetsPrecedents.size() - 1 ; i >= 0 ; i--) {
                sommetPrecedent = sommetsPrecedents.get(i);
                pile.add(sommetPrecedent);
                if (i == 0) {//ajout du dernier / de l'unique sommet précédent au chemin courant
                    chemins.set(c, sommetPrecedent.toString() + chemins.get(c));
                } else {//duplication du chemin courant et ajout du sommet précédent
                    chemins.add(sommetPrecedent.toString() + chInitial);
                    nbChCrees++;
                }
            }
            
            //à la fin du traitement du sommet, on détermine quelle valeur doit prendre c
            if (sommetsPrecedents.isEmpty()) {
                indicesCheminsTraites.add(c);
                if (c < nbChCrees) {
                    c = nbChCrees;
                } else {
                    boolean continuer = true;
                    for (int j = nbChCrees; j >= 0 && continuer ; j--) {
                        if (!indicesCheminsTraites.contains(j)) {
                            c = j;
                            continuer = false;
                        }
                    }
                }
            }
        }
        
        for (String chemin : chemins) {
            System.out.println(chemin);
        }
        
    }
    
    public static void main(String[] args) {
//        Sommet sommetA = new Sommet("A");
//        Sommet sommetB = new Sommet("B");
//        ArrayList<Arc> suivants = new ArrayList<>();
//        
//        suivants.add(new Arc(sommetA,sommetB,2));
//        
//        sommetA.setSuivants(suivants);
//        
//        System.out.println(sommetA.getSuivants().get(0).getExtremite().getNom());
//        System.out.println(sommetB.getPrecedents().get(0).getOrigine().getNom());
//        System.out.println("OK");

//        double[][] matrice = {
//            {0,6,8,10},
//            {0,0,2,0},
//            {0,0,0,2},
//            {0,0,0,0}
//        };
        double[][] matrice = {
            {0,8,3,6,10},
            {0,0,0,0,2},
            {0,0,0,3,0},
            {0,2,0,0,4},
            {0,0,0,0,0}
        };
        int[] niveauxSommets = {0,3,1,2,4};
        Graphe g = new Graphe(matrice,niveauxSommets);
        
        bellmanKalaba(g, true);
//        System.out.println(g.getNbNiveaux());
//        System.out.println(g.getNiveau(2)
//            .getSommets().get(0)
//            .getPrecedents().get(0).getOrigine());
//        System.out.println(g.getNiveau(2)
//            .getSommets().get(0)
//            .getPrecedents().get(1).getOrigine());
//        System.out.println(g.getNiveau(2)
//            .getSommets().get(0)
//            .getSuivants().get(0).getExtremite());
//        System.out.println(g.getNiveau(3)
//            .getSommets().get(0).getEtiquette());
//        System.out.println(g.getNiveau(3)
//            .getSommets().get(0)
//            .getPrecedentsOptimaux().get(0));
//        System.out.println(g.getNiveau(3)
//            .getSommets().get(0)
//            .getPrecedentsOptimaux().get(1));

        
        
    }
}
