/*
 * Grille.java                                    17 ‎mai ‎2023
 * IUT Rodez, info1 2022-2023, pas de copyright ni "copyleft"
 */

package application.modele;

import application.modele.exceptions.ErreurGenerationGrille;

/** 
 * La grille est l'élément principal du démineur
 * Elle gère sa taille, la proportion et le nombre de mines et de drapeaux
 * Elle s'oocupe également de la création de cette grille
 * et comprend le placement des mines dans la grille
 * Elle détermine si toutes les mines ont été trouvées
 * c'est à dire si tout les drapeaux ont été placés
 * @author thomas.lemaire
 */
public class Grille {
    
    /** Longueur maximale d'une grille de démineur */
    private final static int LONGUEUR_MAX  = 128;
    /** Hauteur maximale d'une grille de démineur */
    private final static int HAUTEUR_MAX   = 128;
    /** Coefficient maximum du nombre de bombe d'un démineur */
    private final static double COEF_BOMBE = 0.8;
    
    /** Matrice qui va contenir le démineur */
    private Cellule[][] matriceGrille;
    
    /** Taille en longueur de la matrice de jeu */
    private int longueurGrille;
    
    /** Taille en hauteur de la matrice de jeu */
    private int hauteurGrille;
        
    /** Nombre de bombe étant dans la matrice de jeu */
    private int nombreMine;
    
    /** Nombre de drapeau placer par le joueur */
    private int nombreDrapeau = 0;

    /**
     * Constructeur de Grille
     * @param longueur
     * @param hauteur
     * @param nombreBombe
     */
    Grille(int longueur, int hauteur, int nombreBombe) {
        if (!isValide(longueur, hauteur, nombreBombe)) {
            throw new ErreurGenerationGrille
                ("Impossible de generer un demineur avec les parametres suivants"
                 + " :\n" + "\t- longueur : " + longueur
                 + "\n\t- hauteur  : " + hauteur
                 + "\n\t- nombre de bombe : " + nombreBombe);
        }
        
        this.longueurGrille = longueur;
        this.hauteurGrille  = hauteur;
        this.nombreMine    = nombreBombe;

        this.matriceGrille = new Cellule[hauteurGrille][longueurGrille];

        creerGrille();
    }
    
    /**
     * Prédicat de vérification de la validité du démineur crée
     * @param longueur la longueur de la grille
     * @param hauteur la hauteur de la grille
     * @param nombreBombe le nombre de mines / bombes
     * @return true si le démineur est valide, false sinon
     */
    private boolean isValide(int longueur, int hauteur, int nombreBombe) {
        return    longueur > 1 && longueur <= LONGUEUR_MAX
               && hauteur  > 1 && hauteur  <= HAUTEUR_MAX
               && nombreBombe > 0 
               && nombreBombe < longueur * hauteur * COEF_BOMBE;
    }

    /** 
     * Méthode permettant la création de la matrice de la grille avec les 
     * bonnes dimmensions, au préalable choisis par l'utilisateur.
     */
    private void creerGrille() {
        for (int y = 0; y < hauteurGrille; y++) {
            for (int x = 0; x < longueurGrille; x++) {
                matriceGrille[y][x] = new Cellule();
            }
        }

        placerMine();
        definirValeurCellule();
    }

    /**
     * Méthode qui permet de définir les emplacements des différentes bombes
     * en faisant attention que toutes les bombes soient bien positionnées
     * et qu'aucune ne soit placée, là ou déjà une autre est placée.
     * @return coordonneesBombes tableau de coordonnées des bombes [y, x]
     */
    private int[][] definirMine() {

        int resteBombe;
        int numeroBombe;

        boolean bombeDejaPlacee;

        int[][] coordonneesBombes = new int[nombreMine][2];

        resteBombe = nombreMine;

        while (resteBombe > 0) {
            int y = (int)(Math.random() * hauteurGrille);
            int x = (int)(Math.random() * longueurGrille);

            bombeDejaPlacee = false;

            for (int i = 0; i < coordonneesBombes.length; i++) {
                bombeDejaPlacee 
                = coordonneesBombes[i][0] == y && coordonneesBombes[i][1] == x ?
                        true : false || bombeDejaPlacee;
            }          

            if (!bombeDejaPlacee) {
                numeroBombe = nombreMine - resteBombe;
                coordonneesBombes[numeroBombe][0] = y;
                coordonneesBombes[numeroBombe][1] = x;
                resteBombe--;
            }
        }

        return coordonneesBombes;
    }

    /**
     * Méthode qui permet de placer les mines / bombes
     * selon les emplacements fournis par la méthode definirMine()
     */
    private void placerMine() {
        
        int [][] coordonneesBombes = definirMine();
        int[] coordonneesBombeCourante = new int[2];

        for (int i = 0; i < coordonneesBombes.length; i++) {
            coordonneesBombeCourante = coordonneesBombes[i];
            matriceGrille[coordonneesBombeCourante[0]]
                    [coordonneesBombeCourante[1]].setValeur(0);
        }
    }

    /**
     * Méthode définissant les valeurs de chaque cellule
     * c'est à dire attribuant à chaque cellule un nombre
     * correspondant au nombre de mines sur les case voisines
     * Ce nombre est compris entre 1 et 9
     */
    private void definirValeurCellule() {

        // Les coordonnées des potentiels voisins de la cellule
        int[][] voisinsCoordonnees = {{-1,-1}, {0,-1}, {1,-1},
                                      {-1, 0},         {1, 0},
                                      {-1, 1}, {0, 1}, {1, 1}};

        for (int y = 0; y < hauteurGrille; y++) {
            for (int x = 0; x < longueurGrille; x++) {
                if (!matriceGrille[y][x].isBombe()) {
                    int nbBombeProximite = 1;

                    for (int i = 0; i < voisinsCoordonnees.length; i++) {
                        try {
                            Cellule voisin = matriceGrille
                                    [y + voisinsCoordonnees[i][1]]
                                            [x + voisinsCoordonnees[i][0]];

                            if (voisin.isBombe()) {
                                nbBombeProximite++;
                            }

                            matriceGrille[y][x].setVoisin(voisin);
                        } catch (ArrayIndexOutOfBoundsException e) {
                        }
                    }

                    matriceGrille[y][x].setValeur(nbBombeProximite);
                }
            }
        }

    }

    /** @return la matrice de la grille du démineur*/
    public Cellule[][] getMatriceGrille() {
        return matriceGrille;
    }
    
    /** @return le nombre de mines présentes dans la grille*/
    public int getNombreMine() {
        return this.nombreMine;
    }
    
    /** 
     * @throws IllegalArgumentException 
     * si tout les drapeaux ont été placés
     * @return la grille après avoir ajouter un drapeau
     * */
    public Grille incrementNombreDrapeau() {
        if (this.nombreDrapeau >= this.nombreMine) {
            throw new IllegalArgumentException(
                    "Vous avez placez tous vos drapeaux");
        }
        this.nombreDrapeau++;
        return this;
    }
    
    /** @return le nombre de drapeaux restants à placer par le joueur */
    public int getNombreDrapeau() {
        return this.nombreMine - this.nombreDrapeau;
    }
}
