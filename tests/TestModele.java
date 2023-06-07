/*
 * TestModele.java                                      6 juin 2023
 * IUT Rodez, info1 2022-2023, pas de copyright ni "copyleft" 
 */
package application.modele.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import application.modele.*;

/** TODO comment class responsibility (SRP)
 * @author Utilisateur
 *
 */
class TestModele {
    
    Modele grille1, 
           grille2,
           grille3;
    
    Grille testGrille1,
           testGrille2, 
           testGrille3;

    /**
     * Test method for {@link application.modele.Modele#Modele()}.
     */
    @Test
    void testModele() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link application.modele.Modele#setGrille(int, int, int)}.
     */
    @Test
    void testSetGrille() {
         testGrille1 = new Grille(9, 9, 10);
         testGrille2 = new Grille(16, 16, 40);
         testGrille3 = new Grille(16, 30, 99);
         
         assertEquals(grille1.setGrille(9, 9, 10), testGrille1);
         assertEquals(grille2.setGrille(16, 16, 40), testGrille1);
         assertEquals(grille3.setGrille(6, 30, 99), testGrille1);
    }

    /**
     * Test method for {@link application.modele.Modele#getGrille()}.
     */
    @Test
    void testGetGrille() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link application.modele.Modele#placerDrapeau()}.
     */
    @Test
    void testPlacerDrapeau() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link application.modele.Modele#getNombreDrapeauRestant()}.
     */
    @Test
    void testGetNombreDrapeauRestant() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link application.modele.Modele#reinitialiserChronometre()}.
     */
    @Test
    void testReinitialiserChronometre() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link application.modele.Modele#demarrerChronometre()}.
     */
    @Test
    void testDemarrerChronometre() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link application.modele.Modele#arreterChronometre()}.
     */
    @Test
    void testArreterChronometre() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link application.modele.Modele#getChronometre()}.
     */
    @Test
    void testGetChronometre() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link application.modele.Modele#getNiveaux()}.
     */
    @Test
    void testGetNiveaux() {
        fail("Not yet implemented");
    }

}
