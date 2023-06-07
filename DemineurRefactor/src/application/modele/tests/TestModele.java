package application.modele.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import application.modele.Chronometre;
import application.modele.Grille;
import application.modele.Modele;

/**
 * Test de la classe Modele
 * @author Utilisateur
 */
public class TestModele {
    
    private List<Modele> correctes;
    private List<Chronometre> chronometresCorrectes;
    private List<Grille> grillesCorrectes;

    /**
     * Crée plusieurs liste d'objet Modele, Chronomètre et Grille
     * Qui serviront pour les tests
     */
    @BeforeEach
    public void testModeleOK() {
        correctes = new ArrayList<>(15);
        correctes.add(new Modele());
        
        chronometresCorrectes = new ArrayList<>(15);
        chronometresCorrectes.add(new Chronometre());
        chronometresCorrectes.add(new Chronometre());
        
        grillesCorrectes = new ArrayList<>(3);
        grillesCorrectes.add(new Grille(9,9,10));
        grillesCorrectes.add(new Grille(16,16,40));
        grillesCorrectes.add(new Grille(30,16,99));
    }

    /** Vérifie que la méthode setGrille fonctionne correctement */
    @Test
    public void testSetGrille() {
        // Définir la grille avec des dimensions spécifiques
        correctes.get(0).setGrille(9, 9, 10);

        // Vérifier que la grille a été définie correctement
        Grille grille = correctes.get(0).getGrille();
        assertNotNull(grille);
        assertEquals(10, grille.getNombreMine());
    }
    
    /** Vérifie le renvoie de la grille par la méthode getGrille */
    @Test
    public void testGetGrille() {
        Grille grille = new Grille(9, 9, 10);
        correctes.get(0).setGrille(9, 9, 10);

        Grille resultat = correctes.get(0).getGrille();

        assertEquals(grille.getNombreMine(), resultat.getNombreMine());
    }

    /** Test de la méthode placerDrapeau */
    @Test
    public void testPlacerDrapeau() {
        // Définir la grille avec des dimensions spécifiques
        correctes.get(0).setGrille(10, 10, 10);

        // Placer un drapeau
        correctes.get(0).placerDrapeau();

        // Vérifier que le nombre de drapeaux restants a été mis à jour
        int nombreDrapeauRestant = correctes.get(0).getNombreDrapeauRestant();
        assertEquals(9, nombreDrapeauRestant);
    }
    
    /** Vérifie le renvoie du nombre de drapeau restant par la méthode getNombreDrapeauRestant */
    @Test
    void testGetNombreDrapeauRestant() {
        final int[] NOMBRE_DRAPEAU = {9, 39, 98};
        
        for (int i = 0 ; i < NOMBRE_DRAPEAU.length; i++) {
            grillesCorrectes.get(i).incrementNombreDrapeau();
            assertEquals(NOMBRE_DRAPEAU[i], grillesCorrectes.get(i).getNombreDrapeau());
        }
    }

    /**
     * Vérifie que la méthode réinitialiser  remet bien le chronomètre à zéro
     */
    @Test
    public void testReinitialiserChronometre() {
        for (int i = 0; i < chronometresCorrectes.size(); i++) {
            chronometresCorrectes.get(i).reinitialiser();
            assertEquals(chronometresCorrectes.get(i).getMilliseconds(),System.currentTimeMillis());
        }
    }
    
    /**
     * Test que la méthode démarrer fonctionne correctement.
     */
    @Test
    void testDemarrerChronometre() {
        for (int i = 0; i < chronometresCorrectes.size(); i++) {
            chronometresCorrectes.get(i).demarrer();
            assertTrue(chronometresCorrectes.get(i).getEnCours());
        }
    }
    
    /**
     * Test que la méthode arreter fonctionne correctement.
     */
    @Test
    void testArreterChronometre() {
        for (int i = 0; i < chronometresCorrectes.size(); i++) {
            chronometresCorrectes.get(i).arreter();
            assertFalse(chronometresCorrectes.get(i).getEnCours());
        }
    }
}