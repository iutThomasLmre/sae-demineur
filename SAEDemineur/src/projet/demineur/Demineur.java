/*
 * Demineur.java                                      16 mai 2023
 * IUT Rodez, info1 2022-2023, pas de copyright ni "copyleft" 
 */
package projet.demineur;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/** TODO comment class responsibility (SRP)
 * @author constant.nguyen
 *
 */
public class Demineur {

    private static final int BUTTON_SIZE = 30;

    @FXML
    private ComboBox<String> choixNiveau;

    @FXML
    private BorderPane root;

    @FXML
    private GridPane grille;

    @FXML
    private GridPane menu;

    @FXML
    private Label nombreMines;

    @FXML
    private Circle logoMines;

    @FXML
    private Label temps;

    @FXML
    private Button indice;

    @FXML
    private GridPane meilleursTemps;

    private Object[][] boutonsCoordonnees;

    @FXML
    private void initialize() {
        choixNiveau.getItems().addAll("Facile", "Intermédiaire", "Difficile");
        choixNiveau.setValue("Facile");
        grille.setAlignment(Pos.CENTER);
        selectionNiveau();
        // Créer un StackPane pour centrer la grille
    }
    private void createGrid(int longueur, int hauteur) {       
        grille.setMaxSize(longueur * BUTTON_SIZE, hauteur * BUTTON_SIZE);

        boutonsCoordonnees = new Object[longueur * hauteur][5];
        int i = 0;

        for (int row = 0; row < hauteur; row++) {
            for (int col = 0; col < longueur; col++) {
                Button button = new Button();
                button.setMinSize(BUTTON_SIZE, BUTTON_SIZE);
                button.setMaxSize(BUTTON_SIZE, BUTTON_SIZE);
                GridPane.setHgrow(button, Priority.ALWAYS);
                GridPane.setVgrow(button, Priority.ALWAYS);

                boutonsCoordonnees[i][0] = button;
                boutonsCoordonnees[i][1] = col;
                boutonsCoordonnees[i][2] = row;
                boutonsCoordonnees[i][3] = 0;
                boutonsCoordonnees[i][4] = i;

                grille.add(button, col, row);
                button.setBorder(Border.EMPTY);
                button.setStyle("-fx-border-color: black; -fx-border-width: 0.5px;");
                grille.setBorder(Border.EMPTY);
                grille.setStyle("-fx-border-color: black; -fx-border-width: 0.5px;");

                i++;
            }
        }
    }

    private void deleteGrid() {
        // Supprimer tous les boutons du GridPane
        grille.getChildren().clear();
    }


    /**
     * TODO comment method role
     *
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

    @FXML
    private void niveauFacile() {
        deleteGrid();
        createGrid(9,9);
        System.out.println("facile");
        getBoutons();
        nombreMines.setText("10");
    }

    @FXML
    private void niveauIntermediaire() {
        deleteGrid();
        createGrid(16,16);
        System.out.println("medium");
        getBoutons();
        nombreMines.setText("40");
    }

    @FXML
    private void niveauDifficile() {
        deleteGrid();
        createGrid(30,16);
        System.out.println("hard");
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

