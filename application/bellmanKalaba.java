package application;

import java.util.ArrayList;
import java.util.Stack;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import model.*;

public class bellmanKalaba {
	/** Zone de saisie du r�sultat */
	@FXML public static TextArea textArea;
    /**
     * Recherche du (des) chemin(s) optimal(aux) avec l'algorithme de Bellman-Kalaba
     * @param g le graphe dont on veut le chemin
     * @param details indique si l'affichage des détails de l'algo est souhaité
     */
	@SuppressWarnings("unchecked")
    public static void start(Graphe g, boolean details) {
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
            appendln("DETERMINATION DU (DES) CHEMIN(S) OPTIMAL(AUX)");
        }
        for (int i = 1 ; i < nbNiveaux ; i++) {
            if (details) {
                appendln("Niveau en traitement : " + i);
            }
            sommetsNivCourant = g.getNiveau(i).getSommets();
            /* recherche de la valeur (etiquette + poids) minimale pour chaque précédents du sommet courant s */
            for (Sommet s : sommetsNivCourant) {
                if (details) {
                    appendln("  Sommet en traitement : " + s);
                }
                precedents = s.getPrecedents();
                //défini une valeur de référence pour la recherche du minimum
                s.setEtiquette(precedents.get(0).getOrigine().getEtiquette() + precedents.get(0).getPoids());
                if (details) {
                    append("    Valeurs des precedents (etiquette + poids) :");
                }
                for (Arc precedent : precedents) {
                    //valeur à comparer
                    etiquetteTemporaire = precedent.getOrigine().getEtiquette() + precedent.getPoids();
                    if (details) {
                        append(" " + etiquetteTemporaire);
                    }
                    if (etiquetteTemporaire <= s.getEtiquette()) {
                        s.setEtiquette(etiquetteTemporaire);
                        //retient le chemin minimum pris
                        s.addPrecedentOptimal(precedent.getOrigine()) ;
                    }
                }
                if (details) {
                    appendln("");
                    appendln("    Etiquette determinee : " + s.getEtiquette());
                    append  ("    Sommets precedents des chemins optimaux :");
                    ArrayList<Sommet> precedentsOptimaux = s.getPrecedentsOptimaux();
                    for (Sommet p : precedentsOptimaux) {
                        append(" " + p);
                    }
                    appendln("");
                }
            }
        }
        
        /* Dépilage des chemins optimaux */
        if (details) {
            appendln("");
            appendln("DEPILAGE DES CHEMINS");
        }
        Sommet extremite; //le sommet courant lors du parcours inverse
        ArrayList<String> chemins = new ArrayList<>(); //chemins optimaux représentés par des chaînes de caractères
        
        int c; //indice du chemin courant
        ArrayList<Integer> indicesCheminsTraites = new ArrayList<>(); //indices des chemins déjà traiter 
                                                                      //(utiliser pour mettre c à jour)
        int nbChCrees = 0; //nombre de chemins alternatifs créés
        boolean continuer; //indique si le parcours des indices des chemins traités doit continuer ou non
        String base; //chemin de base utilisé pour créer les chemins alternatifs (cas d'embranchements)
                
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
            base = chemins.get(c);
            sommetsPrecedents = extremite.getPrecedentsOptimaux();
            //le dernier sommet précédent traité est le dernier à être inséré dans la pile
            //et donc le prochain à être traîté. Puisque qu'il est inséré dans le chemin 
            //courant, c n'a pas besoin de changer jusqu'au bout du chemin.
            for (int i = 0 ; i < sommetsPrecedents.size() ; i++) {
                sommetPrecedent = sommetsPrecedents.get(i);
                pile.add(sommetPrecedent);
                if (i == sommetsPrecedents.size() - 1) {//ajout du dernier / de l'unique sommet précédent au chemin courant
                    chemins.set(c, sommetPrecedent.toString() + chemins.get(c));
                } else {//duplication du chemin courant et ajout du sommet précédent
                    chemins.add(sommetPrecedent.toString() + base);
                    nbChCrees++;
                }
            }
            
            //à la fin du traitement du sommet, on détermine quelle valeur doit prendre c
            if (sommetsPrecedents.isEmpty()) {
                indicesCheminsTraites.add(c);
                continuer = true;
                //on prend le dernier chemin créé non traité
                for (int j = nbChCrees; j >= 0 && continuer ; j--) {
                    if (!indicesCheminsTraites.contains(j)) {
                        c = j;
                        continuer = false;
                    }
                }
            }
        }
        
        for (String chemin : chemins) {
            appendln(chemin);
        }
        
    }

    /**
     * Ajoute une ligne dans la boite de saisie
     * @param lineToDisplay ligne à afficher
     */
    public static void append(String lineToDisplay){
        textArea.setText(textArea.getText()+lineToDisplay);
    }

    /**
     * Ajoute une ligne dans la boite de saisie
     * @param lineToDisplay ligne à afficher
     */
    public static void appendln(String lineToDisplay){
    	append(lineToDisplay+"\n");
    }
}