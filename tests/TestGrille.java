/*
 * TestGrille.java                                      6 juin 2023
 * IUT Rodez, info1 2022-2023, pas de copyright ni "copyleft" 
 */
package application.modele.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import application.modele.Grille;
import application.modele.Cellule;

/** TODO comment class responsibility (SRP)
 * @author Utilisateur
 *
 */
class TestGrille {

    /**
     * Fixture de text = jeu de test à l'image fixe 
     * utilisable dans plusieurs tests
     */
    private List<Grille> correctes;

    /**
     * 
     */
    @BeforeEach
    public void testGrilleOk() {
        correctes = new ArrayList<>(3);
        correctes.add(new Grille(9,9,10));
        correctes.add(new Grille(16,16,40));
        correctes.add(new Grille(30,16,99));
    }


    /**
     * Test method for {@link application.modele.Grille#getMatriceGrille()}.
     */
    @Test
    void testGetMatriceGrille() {
        final Cellule[][] GRILLE_FACILE = {
                {new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(),new Cellule(), new Cellule(), new Cellule()}, 
                {new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(),new Cellule(), new Cellule(), new Cellule()},
                {new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(),new Cellule()},
                {new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(),new Cellule()},
                {new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(),new Cellule()}, 
                {new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(),new Cellule()}, 
                {new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(),new Cellule()}, 
                {new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(),new Cellule()},
                {new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(),new Cellule()}};
        
        final Cellule[][] GRILLE_INTERMEDIAIRE = {
                {new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(),new Cellule()}, 
                {new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(),new Cellule()},
                {new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(),new Cellule()},
                {new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(),new Cellule()},
                {new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(),new Cellule()}, 
                {new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(),new Cellule()}, 
                {new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(),new Cellule()}, 
                {new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(),new Cellule()},
                {new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(),new Cellule()},
                {new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(),new Cellule()}, 
                {new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(),new Cellule()},
                {new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(),new Cellule()},
                {new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(),new Cellule()},
                {new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(),new Cellule()}, 
                {new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(),new Cellule()}, 
                {new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(), new Cellule(),new Cellule()}
                };



        assertEquals(GRILLE_FACILE, correctes.get(0).getMatriceGrille());
        assertEquals(GRILLE_INTERMEDIAIRE, correctes.get(1).getMatriceGrille()); 


    }

    /**
     * Test method for {@link application.modele.Grille#getNombreMine()}.
     */
    @Test
    void testGetNombreMine() {
        final int[] NOMBRES_MINESOK = {10, 40, 99};
        
        for (int i = 0; i < NOMBRES_MINESOK.length; i++) {
            assertEquals(NOMBRES_MINESOK[i], correctes.get(i).getNombreMine());
        }
    }

    /**
     * Test method for {@link application.modele.Grille#getNombreDrapeau()}.
     */
    @Test
    void testGetNombreDrapeau() {
        final int[] NOMBRE_DRAPEAU = {1, 1, 1};
        
        for (int i = 0 ; i < NOMBRE_DRAPEAU.length; i++) {
            correctes.get(2).incrementNombreDrapeau();
            assertEquals(NOMBRE_DRAPEAU[i], correctes.get(i).getNombreDrapeau());
        }
    }
    
    
    
    /**
     * Test method for {@link application.modele.Grille#getNombreDrapeau()}.
     */
    @Test
    void testIncrementNombreDrapeau() {
        final int NOMBRES_DRAPEAU[] = {1, 2, 3, 4};
        for (int i = 0; i < NOMBRES_DRAPEAU.length; i++) {
            assertEquals(NOMBRES_DRAPEAU[i], correctes.get(0).incrementNombreDrapeau());
        }
        
        
    }
    
    /**
     * Test method for {@link application.modele.Grille#getNombreDrapeau()}.
     */
    @Test
    void testDecrementNombreDrapeau() {
        final int NOMBRES_DRAPEAU[] = {3, 2, 1, 0};
        
        try {
            correctes.get(1).decrementNombreDrapeau();
            fail("Une IllegalArgumentException est censé être la");
          }catch(IllegalArgumentException aExp){
            assert(aExp.getMessage().contains("Vous avez placez tous vos drapeaux"));
          }
        
        for (int i = 0; i < NOMBRES_DRAPEAU.length; i++) {
            correctes.get(1).incrementNombreDrapeau();
        }
        
        for (int i = 0; i < NOMBRES_DRAPEAU.length; i++) {
            assertEquals(NOMBRES_DRAPEAU[i], correctes.get(1).decrementNombreDrapeau());
        }
        
        
    }

}
