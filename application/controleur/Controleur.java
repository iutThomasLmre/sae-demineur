/*
 * Demineur.java                                      16 mai 2023
 * IUT Rodez, info1 2022-2023, pas de copyright ni "copyleft" 
 */
package application.controleur;

import java.util.ArrayList;
import java.util.List;

import application.modele.Modele;
import application.modele.Cellule;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.shape.Circle;

/** TODO comment class responsibility (SRP)
 * @author constant.nguyen
 *
 */
public class Controleur {

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
    
    private int longueur;
    private int hauteur;
    private Integer mines;

    @FXML
    private Circle logoMines;

    @FXML
    private Label temps;

    @FXML
    private Button indice;

    @FXML
    private GridPane meilleursTemps;

    private static final int BUTTON_SIZE = 30;

    private Object[][] boutonsCoordonnees;
    
    private Modele modele = new Modele();
    
    Object[][] niveauxJeu;
    
    @FXML
    private void initialize() {       
        niveauxJeu = modele.getNiveaux();
        
        for (int i = 0; i < niveauxJeu.length; i++) {
            choixNiveau.getItems().add((String) niveauxJeu[i][0]);
        }
        
        choixNiveau.setValue((String) niveauxJeu[0][0]);

        grille.setAlignment(Pos.CENTER);
        
        
        selectionNiveau();
        // Créer un StackPane pour centrer la grille
    }
    
    /**
     * TODO comment method role
     * @param longueur
     * @param hauteur
     */
    private void createGrid(int longueur, int hauteur) {       
        premiereCaseDecouverte = false;
        grille.getChildren().clear();
        
        grille.setMaxSize(longueur * BUTTON_SIZE, hauteur * BUTTON_SIZE);

        boutonsCoordonnees = new Object[longueur * hauteur][3];
        int i = 0;

        for (int row = 0; row < hauteur; row++) {
            for (int col = 0; col < longueur; col++) {
                Button button = new Button();
                button.setMinSize(BUTTON_SIZE, BUTTON_SIZE);
                button.setMaxSize(BUTTON_SIZE, BUTTON_SIZE);
                GridPane.setHgrow(button, Priority.ALWAYS);
                GridPane.setVgrow(button, Priority.ALWAYS);

                boutonsCoordonnees[i][0] = button;
                boutonsCoordonnees[i][1] = modele.getGrille().getMatriceGrille()[row][col];
                boutonsCoordonnees[i][2] = i;

                grille.add(button, col, row);
                button.setBorder(Border.EMPTY);
                button.setStyle("-fx-border-color: black; -fx-border-width: 0.5px;");
                grille.setBorder(Border.EMPTY);
                grille.setStyle("-fx-border-color: black; -fx-border-width: 0.5px;");

                i++;
            }
        }
        
        getBoutons();
    }
    
    /**
     * TODO comment method role
     *
     */

    public void selectionNiveau() {
        
        for (int i = 0; i < niveauxJeu.length; i++) {
            if(choixNiveau.getValue() == (String) niveauxJeu[i][0]) {
                this.hauteur  = (int)     niveauxJeu[i][1];
                this.longueur = (int)     niveauxJeu[i][2];
                this.mines    = (Integer) niveauxJeu[i][3]; 
            }
        }
        
        this.nombreMines.setText(this.mines.toString());
        
        modele.setGrille(longueur, hauteur, mines);
        createGrid(longueur, hauteur);
    }

    boolean premiereCaseDecouverte = false;
    
    /**
     * @param bouton 
     * @param event  
     * 
     */
    public void clickBoutons(Button bouton) {
                
        bouton.setOnMouseClicked(event -> {
     
            Object[] boutonCourant = new Object[3];
            Cellule celluleCourante;
            
            for (int i = 0; i < boutonsCoordonnees.length; i++) {
                if (bouton == boutonsCoordonnees[i][0]) {
                    boutonCourant = boutonsCoordonnees[i];
                }
            }
            
            celluleCourante = (Cellule) boutonCourant[1];
            
            if (!premiereCaseDecouverte) {
                premierCoups(celluleCourante);
            }
            
            if(event.getButton() == MouseButton.SECONDARY && celluleCourante.getValeur() >= 0) {
                int marque = (int) celluleCourante.marquer();
                
                String res = marque == 0 ? "" : marque == 1 ? "D" : "?";   
                
                if (marque == 1) {
                    modele.getGrille().incrementNombreDrapeau();
                    nombreMines.setText(((Integer) modele.getGrille().getNombreDrapeau()).toString());
                } else if (marque == 2) {
                    modele.getGrille().decrementNombreDrapeau();
                    nombreMines.setText(((Integer) modele.getGrille().getNombreDrapeau()).toString());
                }
                
                bouton.setText(res);
            } else if (event.getButton() == MouseButton.PRIMARY) {
                
                if ((int) celluleCourante.getMarque() == 0) {
                    bouton.setStyle("-fx-background-color: #ffffff; -fx-border-color: black; -fx-border-width: 0.5px;");
                    modele.getGrille().decouvrirCellule(celluleCourante);
                    bouton.setText(celluleCourante.toString());
                    decouvrirCaseCascade();
                }
                if (celluleCourante.isBombe()) {
                    finirPartie();
                }
            }
            
        });
    }
    
    /**
     * TODO comment method role
     *
     */
    private void finirPartie() {
       System.out.println("Partie perdue");
    }
    
    /**
     * TODO comment method role
     *
     */
    public void decouvrirCaseCascade() {
        for (int i = 0; i < boutonsCoordonnees.length; i++) {
            Button bouttonCourant = (Button) boutonsCoordonnees[i][0];
            Cellule celluleCourante = (Cellule) boutonsCoordonnees[i][1];
            
            if(celluleCourante.getValeur() < 0) {
                bouttonCourant.setStyle("-fx-background-color: #ffffff; -fx-border-color: black; -fx-border-width: 0.5px;");
                modele.getGrille().decouvrirCellule(celluleCourante);
                bouttonCourant.setText(celluleCourante.toString());
            }
        }
    }
    
    /**
     * TODO comment method role
     * @param celluleCourante
     */
    public void premierCoups(Cellule celluleCourante) {
        while(celluleCourante.getValeur() != 1) {
            this.modele.getGrille().placerMine();
            this.modele.getGrille().definirValeurCellule();
        }
        
        premiereCaseDecouverte = true;
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
            clickBoutons(bouton);
        }
        return grilleBouton;

    }
    
    

}

