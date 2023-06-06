/*
 * TestGrille.java                                24 ‎mai ‎2023
 * IUT Rodez, info1 2022-2023, pas de copyright ni "copyleft"
 */


package application.modele;

import java.util.Scanner;

/** 
 * Tests unitaires de application.modele.Grille
 * @author thomas.lemaire
 */
public class TestGrille {

    static Grille plan;
    static Cellule[][] matricePlan;
    static Scanner entreeUtilisateur = new Scanner(System.in);

    /** TODO comment method role
     * @param args
     */
    public static void main(String[] args) {
        initialisationGrille();
    }

    /** TODO comment method role
     * 
     */
    public static void initialisationGrille() {
        int[] coordonnees = choisirCellule();
        
        int x = coordonnees[0],
            y = coordonnees[1];
        
        /*
         * Permet de forcément commencer sur une cellule qui n'a pas de bombe
         * autour d'elle
         */
        do {
            plan = new Grille(9, 9, 10);
            matricePlan = plan.getMatriceGrille();
        } while (matricePlan[y][x].getValeur() != 1);
        
        decouvrirCellule(matricePlan[y][x]);
        commencerJouer();
    }
    
    /**
     * <p> 
     * Lancement du jeu avec :
     * <ul><li>Lancement du chronomètre</li>
     *     <li>Création et affichage de la grille</li></ul>
     * Arrêt du jeu si l'utilisateur decouvre une mine
     * </p>
     */
    public static void commencerJouer() {
        Chronometre chronometre = new Chronometre();
        int[] coordonnees;
        
        int x,
            y;
        
        do {
            chronometre.demarrer();
            
            afficherGrille();
            coordonnees = choisirCellule();
            
            x = coordonnees[0];
            y = coordonnees[1];
            
            decouvrirCellule(matricePlan[y][x]);
        } while(matricePlan[y][x].getValeur() != 0);
        
        chronometre.arreter();
        System.out.println("perdu en : " 
                           + chronometre.toString(
                                         chronometre.getMilliseconds()));
    }
    
    /**
     * Choix d'une cellule avec ses coordonnées
     * @return les coordonnées de la cellule choisie
     */
    public static int[] choisirCellule() {
        int[] coordonnees = new int[2];
       
        System.out.print("Choisir un entier x : ");
        coordonnees[0] = entreeUtilisateur.nextInt();
        entreeUtilisateur.nextLine();
        System.out.print("Choisir un entier y : ");
        coordonnees[1] = entreeUtilisateur.nextInt();
        entreeUtilisateur.nextLine();

        return coordonnees;
    }
    
    /** 
     * Méthode permettant de découvrir une cellule
     * Découvre les cellules voisines si aucunes mines les entoure
     * @param cible la cellule que l'utilisateur souhaite découvrir
     */
    public static void decouvrirCellule(Cellule cible) {

        cible.setValeur(Math.abs(cible.getValeur()) * -1);

        if (Math.abs(cible.getValeur()) == 1) {
            for (int i = 0; i < cible.getVoisins().size(); i++) {
                if (cible.getVoisins().get(i).getValeur() == 1) {
                    decouvrirCellule(cible.getVoisins().get(i));
                } else {
                    cible.getVoisins().get(i).setValeur(
                          Math.abs(cible.getVoisins().get(i).getValeur()) * -1);
                }

            }
        }

    }

    /**
     * Affiche la grille de jeu dans la console
     */
    public static void afficherGrille() {

        for (int y = 0; y < matricePlan.length; y++) {
            for (int x = 0; x < matricePlan[y].length; x++) {
                System.out.print("\t" + matricePlan[y][x].getValeur());
            }
            System.out.print("\n");
        }
    }

}
