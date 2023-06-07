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

/** 
 * Classe de test qui permet de vérifier le bon fonctionnement
 * de notre classe Cellule et de ses méthodes
 * @author Utilisateur
 *
 */
class TestCellule {
    /**
     * Fixture de text = jeu de test à l'image fixe 
     * utilisable dans plusieurs tests
     */
    private List<Cellule> correctes;

    /**
     * Crée une liste de cellule correctes que l'on ajoute à une liste
     * qui nous servira de test
     */
    @BeforeEach
    public void testCelluleOk() {
        correctes = new ArrayList<>(5);
        correctes.add(new Cellule());
        correctes.add(new Cellule());
        correctes.add(new Cellule());
        correctes.add(new Cellule());
        correctes.add(new Cellule());
        correctes.add(new Cellule());

    }

    /**
     * Test la méthode setValeur
     * qui a pour but d'attribuer une valeur  à une cellule.
     * On effectue ces tests en vérifiant que la valeur que l'on a attribué
     * est bien égale à la valeur attendue mise dans un tableau
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
     * Test de la méthode setVoisins.
     * On crée les voisins de notre Cellule et on vérifie
     * avec l'aide d'une autre liste que la liste des Voisins est bien la même
     * que la liste des voisins voulus.
     */
    @Test
    void testSetVoisin() {
        Cellule voisin1 = new Cellule();
        Cellule voisin2 = new Cellule();

        correctes.get(0).setVoisin(voisin1);
        correctes.get(0).setVoisin(voisin2);

        List<Cellule> voisinsAttendus = new ArrayList<>();
        voisinsAttendus.add(voisin1);
        voisinsAttendus.add(voisin2);

        List<Cellule> voisinsObtenus = correctes.get(0).getVoisins();

        assertArrayEquals(voisinsAttendus.toArray(), voisinsObtenus.toArray());

    }


    /**
     * Test la méthode marquer
     * qui a pour but d'attribuer une marque à une cellule.
     * On effectue ces tests en vérifiant que la marque que l'on a attribué
     * est bien égale à la marque attendue mise dans un tableau
     */
    @Test
    void testMarquer() {
        final int[] MARQUE = {1,2,0,1,2,0};
        for (int i = 0; i < MARQUE.length; i++) {
            assertEquals(correctes.get(0).marquer(),MARQUE[i]);
        }
    }

    /**
     * Test de la méthode getVoisins.
     * On crée les voisins de notre Cellule et on vérifie
     * avec l'aide d'une autre liste que la liste des Voisins est bien la même
     * que la liste des voisins voulus.
     */
    @Test
    void testGetVoisins() {
        Cellule voisin1 = new Cellule();
        Cellule voisin2 = new Cellule();

        correctes.get(0).setVoisin(voisin1);
        correctes.get(0).setVoisin(voisin2);

        List<Cellule> voisinsAttendus = new ArrayList<>();
        voisinsAttendus.add(voisin1);
        voisinsAttendus.add(voisin2);

        List<Cellule> voisinsObtenus = correctes.get(0).getVoisins();

        assertArrayEquals(voisinsAttendus.toArray(), voisinsObtenus.toArray());
    }


    
    /**
     * Test la méthode getValeur
     * qui a pour but de renvoyer la valeur d'une cellule.
     * On effectue ces tests en vérifiant que la valeur que l'on a attribué
     * est bien égale à la valeur renvoyé.
     */
    @Test
    void testGetValeur() {
        final int[] VALEUR_OK = {0,1,2,3,4,5};
        for (int i = 0; i < VALEUR_OK.length; i++) {
            correctes.get(i).setValeur(i);
        }      
        for (int i = 0; i < VALEUR_OK.length; i++) {
            assertEquals(VALEUR_OK[i], correctes.get(i).getValeur());
        }
    }

    /**
     * Test de la méthode getMarque
     * Vérifie que la méthode getMarque renvoie bien les marques attendues
     * qui sont renseignées dans un tableau.
     */
    @Test
    void testGetMarque() {
        final int[] MARQUE = {1,2,0,1,2,0};
        for (int i = 0; i < MARQUE.length; i++) {
            correctes.get(1).marquer();
            assertEquals(correctes.get(1).getMarque(),MARQUE[i]);
        }

    }

    /**
     * Vérifie que la méthode isBombe renvoie bien true 
     * quand la valeur est zéro.
     */
    @Test
    void testIsBombe() {

        // Cas où la valeur est 0
        correctes.get(0).setValeur(0);
        assertTrue(correctes.get(0).isBombe());

        // Cas où la valeur n'est pas 0
        correctes.get(0).setValeur(5);
        assertFalse(correctes.get(0).isBombe());
    }


    /**
     * Vérifie le renvoie de la méthode toString avec le résultat voulu
     */
    @Test
    void testToString() {

        final String[] VALEUR_TEST = {"*", " "};
        correctes.get(0).setValeur(0);
        correctes.get(1).setValeur(-1);

        for (int i =0; i < VALEUR_TEST.length -1 ; i++) {
            assertEquals(VALEUR_TEST[i], correctes.get(i).toString());
        }
    }

}
