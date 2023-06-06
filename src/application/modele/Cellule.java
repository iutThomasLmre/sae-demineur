/*
 * Cellule.java                                   17 ‎mai ‎2023
 * IUT Rodez, info1 2022-2023, pas de copyright ni "copyleft"
 */

package application.modele;

import java.util.ArrayList;
import java.util.List;

import application.modele.exceptions.IllegalValueException;

/**
 * La cellule est une composante de la grille du démineur, elle conciste à
 * gérer les états et les valeurs que peuvent être obtenus par les actions
 * de l'utilisateur sur la grille.
 * La cellule, a une valeur qui correspond au nombre de bombe par rapport
 * aux cellules voisines. Une valeur négative définit une cellule visible
 * par l'utilisateur.
 * @author thomas.lemaire
 */
public class Cellule {

    /** Tableau contenant tous les cellules voisines  */
    private List<Cellule> voisins;

    /** 
     * Valeur d'une cellule
     * Les valeurs possibles pour une cellule sont :
     *  <ul><li>0 : une bombe est présente sur la cellule.
     * </li><li>1 : aucune bombe est présente dans les cellules voisines.
     * </li><li>2 : une bombe est présente dans les voisins de la cellule.
     * </li><li>3 : deux bombes sont présentes dans les voisins de la cellule.
     * </li><li>...
     * </li><li>8 : huit bombes sont présentes dans les voisins de la cellule.
     * </li></ul>
     * Ces valeurs peuveut devenir négatives si elles sont découvertes par
     * le joueur. 
     * Initialement elles sont toutes positives ou nul (en cas de bombe).
     */
    private Integer valeur;

    /** 
     * Les marques correspondent aux différentes étapes d'état d'une cellule.
     * Les états possible sont :
     *  <ul><li>0 : la cellule n'est pas marquée, le joueur concidère la
     *              cellule comme étant sécurisée, sans bombe.
     * </li><li>1 : la cellule est marqué par un drapeau qui correspond 
     *              à une cellule contenant potentielement (selon le joueur) 
     *              à une bombe.
     * </li><li>2 : la cellule est marqué par une suppostion, le
     *              joueur n'étant pas certain d'y voir une mine,
     *              il marque la cellule comme étant dangeureuse.
     * </li></ul>
     */
    private int marque;

    /** Constructeur de la cellule */
    Cellule() {
        this.voisins = new ArrayList<Cellule>();
        this.valeur  = Integer.MAX_VALUE;
        this.marque  = 0;
    }

    /**
     * Administre une nouvelle valeur à la cellule
     * @param valeur , la valeur que la cellule va avoir
     */
    public void setValeur(Integer valeur) {
        if (valeur < -8 && valeur > 8) {
            throw new IllegalValueException();
        }
        this.valeur = valeur;
    }

    /** 
     * Ajoute un voisin à la liste des cellules voisines de la cellule
     * @param voisin , un nouveau voisin à ajouter à la liste
     */
    public void setVoisin(Cellule voisin) {
        this.voisins.add(voisin);
    }

    /** 
     * Cette méthode à pour but de définir la marque de la cellule.
     * La marque peut prendre plusieurs valeur :
     *  <ul><li>0 : La cellule peut être découverte et n'est pas protégée, et
     *              l'utilisateur ne cible pas la cellule comme étant une bombe.
     * </li><li>1 : La cellule est protégée et ne peut pas être découverte,
     *              l'utilisateur cible la cellule comme étant une bombe.
     * </li><li>2 : La cellule est protégée et ne peut pas être découverte,
     *              l'utilisateur suspect la cellule comme étant une bombe.
     * </li></ul>
     */
    public void marquer() {
        this.marque = ( this.marque + 1 ) % 3;
    }
    
    /** @return la liste des voisins de la cellules */
    public List<Cellule> getVoisins() {
        return this.voisins;
    }
    
    /** @return la valeur de la case */
    public Integer getValeur() {
        return this.valeur;
    }

    /** @return la valeur de la marque */
    public int getMarque() {
        return this.marque;
    }

    /** @return true si la cellule contient une bombe, false sinon */
    public boolean isBombe() {
        return this.valeur == 0;
    }
}
