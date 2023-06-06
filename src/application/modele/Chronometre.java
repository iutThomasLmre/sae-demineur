/*
 * Chronometre.java                               23 ‎mai ‎2023
 * IUT Rodez, info1 2022-2023, pas de copyright ni "copyleft"
 */


package application.modele;

import java.lang.Thread;

/** 
 * TODO comment class responsibility (SRP)
 * @author thomas.lemaire
 *
 */
public class Chronometre implements Runnable {

    // Indique si le chronomètre est en cours d'exécution
    private boolean enCours;
    
    // Heure de départ du chronomètre
    private long heureDebut;  
    
    // Temps depuis lequel le chonometre a été lancé
    private long tempsEcoule; 

    /**
     * Constructeur de Chronometre.
     * Initialise le chronomètre à l'arrêt.
     */
    public Chronometre() {
        this.enCours = false;
    }

    /**
     * Démarre le chronomètre.
     * Si le chronomètre est déjà en cours, cette méthode n'a aucun effet.
     */
    public void demarrer() {
        if (!enCours) {
            this.enCours = true;
            this.heureDebut = System.currentTimeMillis();
            Thread thread = new Thread(this);
            thread.start();
        }
    }

    /**
     * Arrête le chronomètre.
     */
    public void arreter() {
        this.enCours = false;
    }

    /**
     * Réinitialise le chronomètre à zéro.
     * Le chronomètre doit être arrêté pour pouvoir le réinitialiser.
     */
    public void reinitialiser() {
        arreter();
        this.heureDebut = 0;
    }

    /**
     * Formatte le temps écoulé en heures:minutes:secondes.
     *
     * @param milliseconds Temps écoulé en millisecondes.
     * @return Temps écoulé formaté en heures:minutes:secondes.
     */
    public String toString(long milliseconds) {
        long secondes = milliseconds / 1000;
        long minutes = secondes / 60;
        long heures = minutes / 60;

        return String.format("%02d:%02d:%02d", heures % 24, minutes % 60, secondes % 60);
    }
    
    /**
     * TODO comment method role
     * @return .
     */
    public long getMilliseconds() {
    	long heureActuelle = System.currentTimeMillis();
    	this.tempsEcoule   = heureActuelle - this.heureDebut;
    	
    	return this.tempsEcoule;
    }
    
    /**
     * TODO comment method role
     * @return .
     */
    public boolean getEnCours() {
    	return this.enCours;
    }
    
    /**
     * Méthode exécutée dans le thread du chronomètre.
     * Calcule et affiche le temps écoulé à intervalles réguliers.
     */
    @Override
    public void run() {
        while (this.enCours) {
//            System.out.println(toString(getMilliseconds()));

            try {
                Thread.sleep(1000); // Attente d'une seconde
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Méthode principale pour tester le chronomètre.
     *
     * @param args Les arguments de la ligne de commande (non utilisés ici).
     */
    public static void main(String[] args) {
        Chronometre chronometre = new Chronometre();
        chronometre.demarrer();

        // Attente de 10 secondes avant d'arrêter le chronomètre
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Optionnel juste pour les tests
        
        chronometre.arreter();
    }
}