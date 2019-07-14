package it.polimi.se2018.controller.toolCards;

import it.polimi.se2018.controller.Controller;

/**
 * ModelSideToolCard Interface
 * This interface initializes the management of the ToolCard model Side
 * @author Federico Lichinchi
 * @author Davide Lorenzi (JDocs)
 */
public interface ModelSideToolCard {

    /**
     * CardNumber Getter
     * This method catch the correct card number
     * @return required card number
     */
    int getCardNumber();

    /**
     * ToolCard Applier
     * This method verifies the application of the ToolCard
     * @param controller is the game controller
     * @return true if ToolCard has been correctly applied, false otherwise
     * @throws CloneNotSupportedException if is not possible to clone a ToolCard
     */
    boolean applyToolCard(Controller controller) throws CloneNotSupportedException;

    /**
     * Parameters Getter
     * This method catch all parameters needed for the application of the ToolCard
     * @param controller is the game controller
     */
    void getParameters(Controller controller);
}
