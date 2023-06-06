/*
 * Demineur.java                                      16 mai 2023
 * IUT Rodez, info1 2022-2023, pas de copyright ni "copyleft" 
 */
package projet.demineur;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.shape.Circle;

/** TODO comment class responsibility (SRP)
 * @author constant.nguyen
 * @author thomas.izard
 */
public class Demineur {

    /**Constant pour la taille des boutons de la grille*/
    private static final int TAILLE_BOUTON = 30;

    /**Menu de selection de la difficulté*/
    @FXML
    private ComboBox<String> choixNiveau;

    /**Fenêtre principale pour disposer les objets*/
    @FXML
    private BorderPane racine;

    /**Grille de jeu*/
    @FXML
    private GridPane grille;

    /** Menu en haut de la page avec choix de la difficulté,
     * indice, temps et nombre de mines
     */
    @FXML
    private GridPane menu;

    /**Nombre de mines restantes*/
    @FXML
    private Label nombreMines;

    /**Logo des mines dans le menu*/
    @FXML
    private Circle logoMines;

    /**Chronomètre de jeu*/
    @FXML
    private Label temps;

    /**Bouton pour demander un indice*/
    @FXML
    private Button indice;

    /**Grille / Tableau regroupant les 5 meilleurs temps*/
    @FXML
    private GridPane meilleursTemps;

    /**Tableau des coordonnées des boutons*/
    private Object[][] boutonsCoordonnees;

    @FXML
    private void initialize() {
        choixNiveau.getItems().addAll("Facile", "Intermédiaire", "Difficile");
        choixNiveau.setValue("Facile");
        grille.setAlignment(Pos.CENTER);
        selectionNiveau();
    }
    
    /** 
     * Méthode de création de grille de démineur avec une taille demandée
     * <p>
     * Elle fonction de cette manière :
     * <ul><li>Supprime la grille précédente</li>
     *     <li>Bloque la taille de la grille 
     *     selon la longueur et la hauteur</li>
     *     <li>Cree un tableau pour récupérer 
     *     les coordonnées des boutons</li>
     *     <li>Crée une grille avec des boutons cliquables</li>
     *     <li>Met une bordure à la grille et aux boutons</li></ul>
     * </p>
     * @param longueur un entier correspondant à la longueur de la grille 
     * @param hauteur un entier correspondant à la hauteur de la grille
     */
    private void creerGrille(int longueur, int hauteur) {       
        grille.getChildren().clear();
        grille.setMaxSize(longueur * TAILLE_BOUTON, hauteur * TAILLE_BOUTON);

        boutonsCoordonnees = new Object[longueur * hauteur][5];
        int i = 0;

        for (int ligne = 0; ligne < hauteur; ligne++) {
            for (int colonne = 0; colonne < longueur; colonne++) {
                Button bouton = new Button();
                bouton.setMinSize(TAILLE_BOUTON, TAILLE_BOUTON);
                bouton.setMaxSize(TAILLE_BOUTON, TAILLE_BOUTON);
                GridPane.setHgrow(bouton, Priority.ALWAYS);
                GridPane.setVgrow(bouton, Priority.ALWAYS);

                boutonsCoordonnees[i][0] = bouton;
                boutonsCoordonnees[i][1] = colonne;
                boutonsCoordonnees[i][2] = ligne;
                boutonsCoordonnees[i][3] = 0;
                boutonsCoordonnees[i][4] = i;

                grille.add(bouton, colonne, ligne);
                bouton.setBorder(Border.EMPTY);
                bouton.setStyle("-fx-border-color: black; -fx-border-width: 0.5px;");
                grille.setBorder(Border.EMPTY);
                grille.setStyle("-fx-border-color: black; -fx-border-width: 0.5px;");

                i++;
            }
        }
    }

    /**
     * Choix du niveau de difficulté par le joueur
     * Elle se déclenche selon le choix de la difficulté
     * dans le menu par le joueur
     */
    public void selectionNiveau() {
        switch(choixNiveau.getValue()) {
        case "Facile" :
            niveauFacile();
            break;
        case "Intermédiaire" :
            niveauIntermediaire();
            break;
        case "Difficile" :
            niveauDifficile();
            break;
        }
    }

    /**
     * Niveau de difficulté Facile
     * Création d'une grille de taille 9x9
     * Avec 10 mines au total
     */
    @FXML
    private void niveauFacile() {
        creerGrille(9,9);
        getBoutons();
        nombreMines.setText("10");
    }

    /**
     * Niveau de difficulté Intermédiaire
     * Création d'une grille de taille 16x16
     * Avec 40 mines au total
     */
    @FXML
    private void niveauIntermediaire() {
        creerGrille(16,16);
        getBoutons();
        nombreMines.setText("40");
    }

    /**
     * Niveau de difficulté Difficile
     * Création d'une grille de taille 30x16
     * Avec 99 mines au total
     */
    @FXML
    private void niveauDifficile() {
        creerGrille(30,16);
        getBoutons();
        nombreMines.setText("99");
    }

    List<Node> grilleBouton;


    /**
     * @param bouton 
     * @param event  
     * 
     */
    public void placerDrapeau(Button bouton) {
        
        bouton.setOnMouseClicked(event -> {
            
            Object[] boutonCourant = new Object[5];
            
            for (int i = 0; i < boutonsCoordonnees.length; i++) {
                if (bouton == boutonsCoordonnees[i][0]) {
                    boutonCourant = boutonsCoordonnees[i];
                }
            }
            
            if(event.getButton() == MouseButton.SECONDARY) {
                int j = (int) boutonCourant[3];
                j = (j + 1) % 3;
                boutonsCoordonnees[(int) boutonCourant[4]][3] = j;
                
                String res = j == 0 ? "" : j == 1 ? "D" : "?";       
                bouton.setText(res);
            } else if (event.getButton() == MouseButton.PRIMARY) {
                if ((int) boutonCourant[3] == 0) {
                    bouton.setStyle("-fx-background-color: #ffffff; -fx-border-color: black; -fx-border-width: 0.5px;");
                    bouton.setText("1");
                }
            }
        });

    }

    /**
     * @return truc
     * 
     */
    public List<Button> getBoutons() {
        List<Button> grilleBouton = new ArrayList<>();

        for (int i = 0; i < grille.getChildren().size(); i++) {
            if (grille.getChildren().get(i) instanceof Button) {
                Button bouton = (Button) grille.getChildren().get(i);
                grilleBouton.add(bouton); 
            }

        }
        for (Button bouton : grilleBouton) {
            placerDrapeau(bouton);
        }
        return grilleBouton;

    }

}

