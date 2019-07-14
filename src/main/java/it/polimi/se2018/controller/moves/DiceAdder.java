package it.polimi.se2018.controller.moves;

import it.polimi.se2018.controller.Controller;
import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.Grid;

/**
 * Dice Adder Class
 * This method allows to add a die into the player's grid
 * @author Federico Lichinchi
 * @author Davide Lorenzi (JDOCS)
 */
public class DiceAdder implements Move {

    private static final int MOVE_NUMBER = 1;

    /**
     * Dice Adder Constuctor
     * This method does the adding of the die
     */
    DiceAdder() { super(); }

    /**
     * Move Number Getter
     * This method returns the number of the move
     * @return the number of the move
     */
    @Override
    public int getMoveNumber() {
        return MOVE_NUMBER;
    }

    /**
     * Player Address Getter
     * This method gets the IP Address of the player
     * @param controller is the game controller
     * @return the required IP Address
     */
    @Override
    public String getAddressOfPlayerCurrentTurn(Controller controller) {
        return controller.getPlayerOfCurrentTurn().getPlayerAddress();
    }

    /**
     * Current Grid Getter
     * This method catch the grid of the current player
     * @param controller game logic controller
     * @return required grid
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
        Die die = controller.getReserveChosenDie();
        if(die != null)
            controller.addDie(playerAddress, grid, die);
        if(controller.isConnected(playerAddress) && controller.askToApplyToolCard(playerAddress))
            controller.buyToolCard(playerAddress, grid);
    }
}
