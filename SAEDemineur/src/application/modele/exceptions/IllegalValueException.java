/*
 * IllegalValueException.java                                   24 ‎mai ‎2023
 * IUT de Rodez, BUT Informatique 1 TP33, pas de copyright ni de "copyleft"
 */


package application.modele.exceptions;

/**
 * TODO comment class responsibility (SRP)
 * @author thomas.lemaire
 */
public class IllegalValueException extends RuntimeException {

    /**
     * 
     */
    public IllegalValueException() {
        super();
    }

    /**
     * 
     * @param message
     */
    public IllegalValueException(String message) {
        super(message);
    }
}
