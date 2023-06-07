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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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

    @FXML
    private Circle logoMines;

    @FXML
    private Label temps;

    @FXML
    private Button indice;

    @FXML
    private GridPane meilleursTemps;

    private static final int BUTTON_SIZE = 30;

    private int longueur;
    private int hauteur;
    private Integer mines;

    private Object[][] boutonsCoordonnees;

    private Modele modele = new Modele();

    private Object[][] niveauxJeu;

    private boolean premiereCaseDecouverte = false;

    @FXML
    private void initialize() {       
        niveauxJeu = modele.getNiveaux();

        for (int i = 0; i < niveauxJeu.length; i++) {
            choixNiveau.getItems().add((String) niveauxJeu[i][0]);
        }

        choixNiveau.setValue((String) niveauxJeu[0][0]);

        grille.setAlignment(Pos.CENTER);

        selectionNiveau();
    }

    /**
     * TODO comment method role
     * @param longueur
     * @param hauteur
     */
    private void creerGrilleVue(int longueur, int hauteur) {       
        premiereCaseDecouverte = false;
        grille.getChildren().clear();

        grille.setMaxSize(longueur * BUTTON_SIZE, hauteur * BUTTON_SIZE);

        boutonsCoordonnees = new Object[longueur * hauteur][3];

        // Index permettant de cibler le boutton
        int i = 0;

        for (int row = 0; row < hauteur; row++) {
            for (int col = 0; col < longueur; col++) {
                Button button = new Button();
                button.setMinSize(BUTTON_SIZE, BUTTON_SIZE);
                button.setMaxSize(BUTTON_SIZE, BUTTON_SIZE);
                GridPane.setHgrow(button, Priority.ALWAYS);
                GridPane.setVgrow(button, Priority.ALWAYS);

                boutonsCoordonnees[i][0] = button;
                boutonsCoordonnees[i][1] 
                        = modele.getGrille().getMatriceGrille()[row][col];
                boutonsCoordonnees[i][2] = i;

                grille.add(button, col, row);
                button.setBorder(Border.EMPTY);
                button.setStyle
                ("-fx-border-color: black; -fx-border-width: 0.5px;");
                grille.setBorder(Border.EMPTY);
                grille.setStyle
                ("-fx-border-color: black; -fx-border-width: 0.5px;");

                i++;
            }
        }

        getBoutons();
    }

    /**
     * TODO comment method role
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
        creerGrilleVue(longueur, hauteur);
    }



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
                    setBoutonCouleur(bouton, celluleCourante.getValeur());

                    bouton.setText(celluleCourante.toString());
                    decouvrirCase();

                    if (celluleCourante.isBombe()) {
                        finPartie(false);
                    } else if (modele.getGrille().verifierFinPartie()){
                        finPartie(true);
                    }
                }
            }

        });
    }
    
    /**
     * TODO comment method role
     * @param valeur
     */
    private void setBoutonCouleur(Button bouton, Integer valeur) {
        final String STYLE_BOUTON = "-fx-background-color: #ffffff;"
                                  + "-fx-border-color: black;" 
                                  + "-fx-border-width: 0.5px;"
                                  + "-fx-font-weight: bold;";
        
        if (valeur == Integer.valueOf(-2)) {
            bouton.setStyle(STYLE_BOUTON + "-fx-text-fill: #98D15E;");
        } else if (valeur == Integer.valueOf(-3)) {
            bouton.setStyle(STYLE_BOUTON + "-fx-text-fill: #5ED1A8;");
        } else if (valeur == Integer.valueOf(-4)) {
            bouton.setStyle(STYLE_BOUTON + "-fx-text-fill: #5EA8D1;");
        } else if (valeur == Integer.valueOf(-5)) {
            bouton.setStyle(STYLE_BOUTON + "-fx-text-fill: #6E5ED1;");
        } else if (valeur == Integer.valueOf(-6)) {
            bouton.setStyle(STYLE_BOUTON + "-fx-text-fill: #BA5ED1;");
        } else if (valeur == Integer.valueOf(-6)) {
            bouton.setStyle(STYLE_BOUTON + "-fx-text-fill: #D15EB1;");
        } else if (valeur == Integer.valueOf(-6)) {
            bouton.setStyle(STYLE_BOUTON + "-fx-text-fill: #D15E5E;");
        } else if (valeur == Integer.valueOf(-6)) {
            bouton.setStyle(STYLE_BOUTON + "-fx-text-fill: #000000;");
        } else {
            bouton.setStyle(STYLE_BOUTON);
        }
    }

    /**
     * TODO comment method role
     *
     */
    private void finPartie(boolean partieGagnee) {        
        Alert messageFinPartie = new Alert(AlertType.INFORMATION);
        messageFinPartie.setTitle("");
        messageFinPartie.setHeaderText(null);

        if(!partieGagnee) {
            partiePerdue(messageFinPartie);
            modele.getGrille().decouvrirCelluleFinPartie();
            decouvrirCase();
        } else {
            partieGagnee(messageFinPartie);
        }

        messageFinPartie.showAndWait();
        selectionNiveau();
    }

    /**
     * TODO comment method role
     * @param alertBox
     */
    private void partiePerdue(Alert alertBox) {
        alertBox.setContentText("Vous avez perdu.\nVous avez touché une mine."
                +"\nVoulez-vous recommencer ?");
    }

    /**
     * TODO comment method role
     * @param alertBox
     */
    private void partieGagnee(Alert alertBox) {
        alertBox.setContentText("Bravo vous avez Gagné."
                +"\nVoulez-vous recommencer ?");
    }

    /**
     * TODO comment method role
     *
     */
    public void decouvrirCase() {
        for (int i = 0; i < boutonsCoordonnees.length; i++) {
            Button bouttonCourant = (Button) boutonsCoordonnees[i][0];
            Cellule celluleCourante = (Cellule) boutonsCoordonnees[i][1];

            if(celluleCourante.getValeur() < 0) {
                bouttonCourant.setStyle("-fx-background-color: #ffffff; -fx-border-color: black; -fx-border-width: 0.5px;");
                setBoutonCouleur(bouttonCourant, celluleCourante.getValeur());
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

