/*
 * ErreurGenerationDemineur.java                  30 mai 2023
 * IUT Rodez, info1 2022-2023, pas de copyright ni "copyleft"
 */
package application.modele.exceptions;

/** TODO comment class responsibility (SRP)
 * @author thomas.lemaire
 *
 */
public class ErreurGenerationGrille extends RuntimeException {

    /**
     * 
     */
    public ErreurGenerationGrille() {
        super();
    }

    /**
     * 
     * @param message
     */
    public ErreurGenerationGrille(String message) {
        super(message);
    }
}