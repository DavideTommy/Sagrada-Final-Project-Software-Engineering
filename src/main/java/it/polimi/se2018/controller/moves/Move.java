package it.polimi.se2018.controller.moves;

import it.polimi.se2018.controller.Controller;
import it.polimi.se2018.model.Grid;

/**
 * Move Interface
 * This Interface is used to manage all player's moves
 * @author Federico Lichinchi
 * @author Davide Lorenzi (JDocs)
 */
public interface Move {
    /**
     * Move Number Getter
     * This method return the number of the move
     * @return the number of the required move
     */
    int getMoveNumber();

    /**
     * Player Address Getter
     * This method gets the IP Address of the current player
     * @param controller game controller
     * @return che required IP Address
     */
    String getAddressOfPlayerCurrentTurn(Controller controller);

    /**
     * Player's Grid Getter
     * This method catch the grid of the current player
     * @param controller game logic controller
     * @return the controller status
     */
    Grid getGridOfPlayerCurrentTurn(Controller controller);

    /**
     * Move Applier
     * This method allows to apply a move
     * @param controller is the game controller
     */
    void applyMove(Controller controller);
}
