/*
 * Modele.java                                    6 juin 2023
 * IUT Rodez, info1 2022-2023, pas de copyright ni "copyleft"
 */
package application.modele;

/** TODO comment class responsibility (SRP)
 * @author thomas.lemaire
 *
 */
public class Modele {

    /** les niveaux de la grille */
    private final Object[][] NIVEAUX = {{"Facile", 9, 9, 10},
                                        {"Intermédiaire", 16, 16, 40},
                                        {"Difficile", 16, 30, 99}};

    /** La grille de jeu */
    private Grille demineur;

    /** Le chronometre du jeu */
    private Chronometre chronometre = new Chronometre();

    /** La valeur du chronometre */
    private String valeurChonometre = "00:00:00";

    /** 
     * Constructeur du Modele
     */
    public Modele() {
        // jdk19 Auto-generated constructor stub
    }

    /**
     * <p>
     * Crée une grille de jeu avec :
     * <ul><li>La longueur sous la forme de nombre</li>
     *     <li>La hauteur sous la forme de nombre</li>
     *     <li>Le nombre de mines sous la forme de nombre</li></ul>
     * Renvoie ensuite cette grille
     * </p>
     * @param longueur
     * @param hauteur
     * @param nombreBombe
     * @return lui
     */
    public Modele setGrille(int longueur, int hauteur, int nombreBombe) {
        this.demineur = new Grille(longueur, hauteur, nombreBombe);
        return this;
    }

    /** @return grille de jeu de la partie en cours*/
    public Grille getGrille() {
        return this.demineur;
    }

    /**
     * Méthode de placement d'un drapeau
     * avec incrément du nombre de drapeau
     * @return 0
     */
    public Modele placerDrapeau() {
        this.demineur.incrementNombreDrapeau();
        return this;
    }

    /** @return le nombre de drapeau restant à placer*/
    public int getNombreDrapeauRestant() {
        return this.demineur.getNombreDrapeau();
    }

    /**
     * Reinitialise le chronometre du jeu
     * @return o
     */
    public Modele reinitialiserChronometre() {
        this.chronometre.reinitialiser();
        this.valeurChonometre = "00:00:00";
        return this;
    }

    /**
     * Lancement du chronomètre
     * @return 0 
     */
    public Chronometre demarrerChronometre() {
        this.chronometre.demarrer();

        while (this.chronometre.getEnCours()) {
            this.valeurChonometre 
                = this.chronometre.toString(this.chronometre.getMilliseconds());
            System.out.println(this.valeurChonometre );
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return chronometre;
    }

    /**
     * Arrêt du chronomètre
     * @return 0
     */
    public Modele arreterChronometre() {
        this.chronometre.arreter();
        return this;
    }

    /** @return la valeur du chronomètre en cours*/
    public Chronometre getChronometre() {
        return chronometre;
    }

    /** @return les différents niveaux que le démineur peut avoir */
    public Object[][] getNiveaux() {
        return this.NIVEAUX;
    }
}