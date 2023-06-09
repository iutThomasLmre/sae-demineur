/*
 * TestGrille.java                                      6 juin 2023
 * IUT Rodez, info1 2022-2023, pas de copyright ni "copyleft" 
 */
package application.modele.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import application.modele.Grille;
import application.modele.Cellule;

/** 
 * Test de la classe Grille
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
     * Création d'une liste de Grille qui servira pour nos tests
     */
    @BeforeEach
    public void testGrilleOk() {
        correctes = new ArrayList<>(3);
        correctes.add(new Grille(9,9,10));
        correctes.add(new Grille(16,16,40));
        correctes.add(new Grille(30,16,99));
    }


    /**
     * Test que la méthode getMatriceGrille renvoie bien la matrice recherchée
     */
    @Test
    public void testGetMatriceGrille() {  
        // Obtenir la matrice de la grille
        Cellule[][] matrice = correctes.get(0).getMatriceGrille();
        
        // Vérifier que la matrice n'est pas nulle
        assertNotNull(matrice);
        
        // Vérifier que la taille de la matrice est correcte
        assertEquals(9, matrice.length);
        assertEquals(9, matrice[0].length);
    }

    /**
     * Vérifie que la méthode nombreMine renvoie bien le nombre de mines voulues
     * présentes dans un tableau.
     */
    @Test
    void testGetNombreMine() {
        final int[] NOMBRES_MINESOK = {10, 40, 99};
        
        for (int i = 0; i < NOMBRES_MINESOK.length; i++) {
            assertEquals(NOMBRES_MINESOK[i], correctes.get(i).getNombreMine());
        }
    }

    /**
     * test le renvoie du nombre de drapeau avec le résultat attendus présent
     * dans un tableau
     */
    @Test
    void testGetNombreDrapeau() {
        final int[] NOMBRE_DRAPEAU = {9, 39, 98};
        
        for (int i = 0 ; i < NOMBRE_DRAPEAU.length; i++) {
            correctes.get(i).incrementNombreDrapeau();
            assertEquals(NOMBRE_DRAPEAU[i], correctes.get(i).getNombreDrapeau());
        }
    }
    
    
    
    /** Vérifie que le nombre de drapeau augmente */
    @Test
    void testIncrementNombreDrapeau() {
        final int NOMBRES_DRAPEAU[] = {9, 8, 7};
        for (int i = 0; i < NOMBRES_DRAPEAU.length; i++) {
            assertEquals(NOMBRES_DRAPEAU[i], correctes.get(0).incrementNombreDrapeau().getNombreDrapeau());
        }
        
        
    }
    
    /** Vérifie que le nombre de drapeau diminue */
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
            assertEquals(correctes.get(1).decrementNombreDrapeau(), correctes.get(1).decrementNombreDrapeau());
        }
        
        
    }
    
}