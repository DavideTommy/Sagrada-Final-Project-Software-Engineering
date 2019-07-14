package it.polimi.se2018.controller.moves;

import it.polimi.se2018.controller.Controller;
import it.polimi.se2018.model.Grid;

/**
 * ToolCard Applier Method
 * This method allows to apply all ToolCards
 * @author Federico Lichinchi
 * @author Davide Lorenzi (JDocs)
 */
public class ToolCardApplier implements Move {

    private static final int MOVE_NUMBER = 2;

    /**
     * ToolCardApplier Constructor
     * This method generates the class
     */
    ToolCardApplier() { super(); }

    /**
     * Move Number Getter
     * This method return the number of the move
     * @return the number of the required move
     */
    @Override
    public int getMoveNumber() {
        return MOVE_NUMBER;
    }

    /**
     * Player Address Getter
     * This method gets the IP Address of the current player
     * @param controller game controller
     * @return che required IP Address
     */
    @Override
    public String getAddressOfPlayerCurrentTurn(Controller controller) {
        return controller.getPlayerOfCurrentTurn().getPlayerAddress();
    }


    /**
     * Player's Grid Getter
     * This method catch the grid of the current player
     * @param controller game logic controller
     * @return the controller status
     */
    @Override
    public Grid getGridOfPlayerCurrentTurn(Controller controller) {
        return controller.getPlayerOfCurrentTurn().getGrid();
    }

    /**
     * Move Applier
     * This method allows to apply a move
     * @param controller is the game controller
     */
    @Override
    public void applyMove(Controller controller) {
        String playerAddress = getAddressOfPlayerCurrentTurn(controller);
        Grid grid = getGridOfPlayerCurrentTurn(controller);
        controller.buyToolCard(playerAddress, grid);
        if (controller.isConnected(playerAddress) && !controller.getInsertedDie() && controller.askToAddDie(playerAddress)) {
            controller.addDie(playerAddress, grid, controller.getReserveChosenDie());
        }
    }
}
