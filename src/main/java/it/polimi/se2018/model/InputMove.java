package it.polimi.se2018.model;

/**
 * InputMove Interface
 * This interface manage the input move management system
 * @author Federico Lichinchi
 * @author Davide Lorenzi (JDocs)
 */
public interface InputMove {
    /**
     * Message Getter
     * This method gets a message
     * @return the message
     */
    String getMessage();

    /**
     * Elements Getter
     * This method returns a required object
     * @return the required object
     */
    Object getElement();
}
