package it.polimi.se2018.controller.toolCards;

import it.polimi.se2018.controller.Controller;
import it.polimi.se2018.controller.effects.checkMethods.checkConstraintMethods.CheckDieNoColorConstraints;
import it.polimi.se2018.controller.effects.checkMethods.checkConstraintMethods.ChooseCheckConstraint;
import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.Grid;
import it.polimi.se2018.model.Player;
/**
 * WheeledPincer Class extends CheckAndPick implements ModelSideToolCard
 * This class is the logic of the Wheeled Pincer ToolCard
 * @author Federico Lichinchi
 * @author Davide Lorenzi (JDocs)
 */
public class WheeledPincer extends CheckAndPick implements ModelSideToolCard {

    private static final int CARDNUMBER = 8;
    private boolean isSecondTurn;
    private Player player;
    private Grid playerGrid;

    /**
     * WheeledPincer Constructor
     * This method creates the ToolCard
     */
    public WheeledPincer() { super(); }

    /**
     * PlacementConstraint Checker
     * This method verifies if constraints are respected or not
     * @param newRow row in which the player wanna set the die after the application of the ToolCard
     * @param newColumn column in which the player wanna set the die after the application of the ToolCard
     * @param chosenDie is the die chosen on which wil be applied the effects of the chosen ToolCard
     * @param player is the player that bought the ToolCard
     * @return true if is possible to apply the move after the application of the ToolCard, false otherwise
     */
    @Override
    public boolean arePlacementConstraintsRespected(int newRow, int newColumn, Die chosenDie, Player player) {
        if(player.getGrid().isGridEmpty())
            player.getGrid().setFirstTimeToTrue();
        ChooseCheckConstraint constraintChecker = new ChooseCheckConstraint(new CheckDieNoColorConstraints());
        return constraintChecker.checkConstraint(newRow, newColumn, chosenDie, player);
    }

    /**
     * CardNumber Getter
     * This method catch the correct card number
     * @return required card number
     */
    @Override
    public int getCardNumber() {
        return CARDNUMBER;
    }

    /**
     * ToolCard Applier
     * This method verifies the application of the ToolCard
     * @param controller is the game controller
     * @return true if ToolCard has been correctly applied, false otherwise
     */
    @Override
    public boolean applyToolCard(Controller controller) {
        getParameters(controller);
        if(!isSecondTurn) {
            Die die = controller.getReserveChosenDie();
            if(die != null) {
                controller.addPlayerThatWillSkipSecondTurn(player);
                controller.addDie(player.getPlayerAddress(), playerGrid, die);
                return true;
            }
            else
                return false;
        } else {
            controller.notifyPlayerSecondTurn(player.getPlayerAddress());
            return false;
        }
    }

    /**
     * Parameters Getter
     * This method catch all parameters needed for the application of the ToolCard
     * @param controller is the game controller
     */
    @Override
    public void getParameters(Controller controller) {
        isSecondTurn = (controller.getModel().getMatch().getRoundTrack().getPlayerTurn() == 2);
        player = controller.getPlayerOfCurrentTurn();
        playerGrid = controller.getPlayerOfCurrentTurn().getGrid();
    }
}
