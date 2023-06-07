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
 * @author thomas.izard
 *
 */
public class TestModele {
    
    private List<Modele> correctes;
    private List<Chronometre> chronometresCorrectes;
    private List<Grille> grillesCorrectes;

    /**
     * 
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

    /**
     * 
     */
    @Test
    public void testSetGrille() {
        // Définir la grille avec des dimensions spécifiques
        correctes.get(0).setGrille(9, 9, 10);

        // Vérifier que la grille a été définie correctement
        Grille grille = correctes.get(0).getGrille();
        assertNotNull(grille);
        assertEquals(10, grille.getNombreMine());
    }
    
    /**
     * 
     */
    @Test
    public void testGetGrille() {
        Grille grille = new Grille(9, 9, 10);
        correctes.get(0).setGrille(9, 9, 10);

        Grille resultat = correctes.get(0).getGrille();

        assertEquals(grille.getNombreMine(), resultat.getNombreMine());
    }

    /**
     * 
     */
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
    
    /**
     * Test method for {@link application.modele.Grille#getNombreDrapeau()}.
     */
    @Test
    void testGetNombreDrapeauRestant() {
        final int[] NOMBRE_DRAPEAU = {9, 39, 98};
        
        for (int i = 0 ; i < NOMBRE_DRAPEAU.length; i++) {
            grillesCorrectes.get(i).incrementNombreDrapeau();
            assertEquals(NOMBRE_DRAPEAU[i], grillesCorrectes.get(i).getNombreDrapeau());
        }
    }

    /**
     * 
     */
    @Test
    public void testReinitialiserChronometre() {
        for (int i = 0; i < chronometresCorrectes.size(); i++) {
            chronometresCorrectes.get(i).reinitialiser();
            assertEquals(chronometresCorrectes.get(i).getMilliseconds(),System.currentTimeMillis());
        }
    }
    
    /**
     * Test method for {@link application.modele.Chronometre#demarrer()}.
     */
    @Test
    void testDemarrerChronometre() {
        for (int i = 0; i < chronometresCorrectes.size(); i++) {
            chronometresCorrectes.get(i).demarrer();
            assertTrue(chronometresCorrectes.get(i).getEnCours());
        }
    }
    
    /**
     * Test method for {@link application.modele.Chronometre#arreter()}.
     */
    @Test
    void testArreterChronometre() {
        for (int i = 0; i < chronometresCorrectes.size(); i++) {
            chronometresCorrectes.get(i).arreter();
            assertFalse(chronometresCorrectes.get(i).getEnCours());
        }
    }
}
