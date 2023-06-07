/*
 * TestChronometre.java                                      6 juin 2023
 * IUT Rodez, info1 2022-2023, pas de copyright ni "copyleft" 
 */
package application.modele.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import application.modele.Chronometre;

/** Test de la classe Chronomètre
 * @author Utilisateur
 *
 */
class TestChronometre {
    
    private List<Chronometre> correctes;
    
    /**
     * Crée une liste de chronomètre qui servira pour nos tests
     */
    @BeforeEach
    public void testChronometreOk() {
        correctes = new ArrayList<>(15);
        correctes.add(new Chronometre());
        correctes.add(new Chronometre());
    }

    /**
     * Permet de tester la méthode démarrer
     */
    @Test
    void testDemarrer() {
        for (int i = 0; i < correctes.size(); i++) {
            correctes.get(i).demarrer();
            assertTrue(correctes.get(i).getEnCours());
        }
    }

    /**
     * Test que la méthode arreter foncionne correctement.
     */
    @Test
    void testArreter() {
        for (int i = 0; i < correctes.size(); i++) {
            correctes.get(i).arreter();
            assertFalse(correctes.get(i).getEnCours());
        }
    }

    /**
     * Vérifie que la méthode réinitialiser  remet bien le chronomètre à zéro
     */
    @Test
    void testReinitialiser() {
        for (int i = 0; i < correctes.size(); i++) {
            correctes.get(i).reinitialiser();
            assertEquals(correctes.get(i).getMilliseconds(),System.currentTimeMillis());
        }
    }

    /**
     * Test l'affichage du chronomètre
     */
    @Test
    void testToStringLong() {
        final String[] VALEUR_OK = {"00:00:00", "00:00:01",
                                    "00:01:00", "01:00:00"};
        final long[] VALEUR_MILLISECONDES_OK = {(long) 0.0,(long)1000.0,
                (long)60000.0,(long)3600000.0};
        for (int i = 0; i < VALEUR_OK.length; i++) {
            assertEquals(Chronometre.toString(VALEUR_MILLISECONDES_OK[i]), VALEUR_OK[i]);
        }
    }

    /**
     * Vérifie que la méthode getMilliseconds renvoie 
     * bien les millisecondes attendues
     */
    @Test
    void testGetMilliseconds() {
        for (int i = 0; i < correctes.size(); i++) {
            assertEquals(correctes.get(i).getMilliseconds(), System.currentTimeMillis());
        }
    }

    /**
     * Vérifie le boolean enCours renvoie true si le chronomètre est marche
     * et false si il est arrêter
     */
    @Test
    void testGetEnCours() {
        correctes.get(0).demarrer();
        assertTrue(correctes.get(0).getEnCours());
        assertFalse(correctes.get(1).getEnCours());
    }

}