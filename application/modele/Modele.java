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
     * TODO comment initial state
     */
    public Modele() {
        // jdk19 Auto-generated constructor stub
    }

    /**
     * TODO comment method role
     * @param longueur
     * @param hauteur
     * @param nombreBombe
     * @return lui
     */
    public Modele setGrille(int longueur, int hauteur, int nombreBombe) {
        this.demineur = new Grille(longueur, hauteur, nombreBombe);
        return this;
    }

    /** 
     * TODO comment method role
     * @return grille
     */
    public Grille getGrille() {
        return this.demineur;
    }

    /**
     * TODO comment method role
     * @return 0
     */
    public Modele placerDrapeau() {
        this.demineur.incrementNombreDrapeau();
        return this;
    }

    /**
     * TODO comment method role
     * @return 0
     */
    public int getNombreDrapeauRestant() {
        return this.demineur.getNombreDrapeau();
    }

    /**
     * TODO comment method role
     * @return o
     */
    public Modele reinitialiserChronometre() {
        this.chronometre.reinitialiser();
        this.valeurChonometre = "00:00:00";
        return this;
    }

    /**
     * TODO comment method role
     * @return 0 
     */
    public Modele demarrerChronometre() {
        this.chronometre.demarrer();

        while (this.chronometre.getEnCours()) {
            this.valeurChonometre 
                = this.chronometre.toString(this.chronometre.getMilliseconds());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return this;
    }

    /**
     * TODO comment method role
     * @return 0
     */
    public Modele arreterChronometre() {
        this.chronometre.arreter();
        return this;
    }

    /**
     * TODO comment method role
     * @return 2
     */
    public String getChronometre() {
        return this.valeurChonometre;
    }

    /** @return les différents niveaux que le démineur peut avoir */
    public Object[][] getNiveaux() {
        return this.NIVEAUX;
    }
}