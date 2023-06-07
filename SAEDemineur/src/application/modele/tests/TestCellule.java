/*
 * TestCellule.java                                      6 juin 2023
 * IUT Rodez, info1 2022-2023, pas de copyright ni "copyleft" 
 */
package application.modele.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import application.modele.Cellule;

import java.util.ArrayList;
import java.util.List;

/** TODO comment class responsibility (SRP)
 * @author Utilisateur
 *
 */
class TestCellule {
    /**
     * Fixture de text = jeu de test à l'image fixe 
     * utilisable dans plusieurs tests
     */
    private List<Cellule> correctes;
    
    private List<Cellule>voisins;
    /**
     * 
     */
    @BeforeEach
    public void testCelluleOk() {
        correctes = new ArrayList<>(15);
        correctes.add(new Cellule());
        correctes.add(new Cellule());
        correctes.add(new Cellule());
        correctes.add(new Cellule());
        correctes.add(new Cellule());
        correctes.add(new Cellule());
    }

    /**
     * Test method for {@link application.modele.Cellule#setValeur(java.lang.Integer)}.
     */
    @Test
    void testSetValeur() {
        final int[] VALEUR_OK = {0,1,2,3,4,5};
        for (int i = 0; i < correctes.size(); i++) {
            correctes.get(i).setValeur(i);
            assertEquals(VALEUR_OK[i], correctes.get(i).getValeur());
        }
        
        
    }

    /**
     * Test method for {@link application.modele.Cellule#setVoisin(application.modele.Cellule)}.
     */
    @Test
    void testSetVoisin() {
        
        
    }

    /**
     * Test method for {@link application.modele.Cellule#marquer()}.
     */
    @Test
    void testMarquer() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link application.modele.Cellule#getVoisins()}.
     */
    @Test
    void testGetVoisins() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link application.modele.Cellule#getValeur()}.
     */
    @Test
    void testGetValeur() {
        final int[] VALEUR_OK = {0,1,2,3,4,5};
        for (int i = 0; i < VALEUR_OK.length; i++) {
            assertEquals(VALEUR_OK[i], correctes.get(i).getValeur());
        }
    }

    /**
     * Test method for {@link application.modele.Cellule#getMarque()}.
     */
    @Test
    void testGetMarque() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link application.modele.Cellule#isBombe()}.
     */
    @Test
    void testIsBombe() {
        final Object[] VALEUR_MINE = {-8,-1,0,1,8};
        assertFalse(((Cellule) VALEUR_MINE[0]).isBombe());
        assertFalse(((Cellule) VALEUR_MINE[1]).isBombe());
        assertTrue(((Cellule) VALEUR_MINE[2]).isBombe());
        assertFalse(((Cellule) VALEUR_MINE[3]).isBombe());
        assertFalse(((Cellule) VALEUR_MINE[4]).isBombe());
    }

    /**
     * Test method for {@link application.modele.Cellule#toString()}.
     */
    @Test
    void testToString() {
        
        final String[] VALEUR_TEST = {"*", " "};
        assertEquals(VALEUR_TEST[0], correctes.get(0).toString());
        
        correctes.get(1).setValeur(-1);
        assertEquals(VALEUR_TEST[1], correctes.get(1).toString());
    }

}
