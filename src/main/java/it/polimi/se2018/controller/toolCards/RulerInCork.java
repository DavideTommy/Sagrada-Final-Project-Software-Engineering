package it.polimi.se2018.controller.toolCards;

import it.polimi.se2018.controller.Controller;
import it.polimi.se2018.controller.effects.checkMethods.checkConstraintMethods.CheckDieNotAdjacency;
import it.polimi.se2018.controller.effects.checkMethods.checkConstraintMethods.ChooseCheckConstraint;
import it.polimi.se2018.controller.effects.structuresModifiersMethods.DieMover;
import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.Player;

/**
 * RulerInCork Class extends CheckAndPick implements ModelSideToolCard
 * This class represents the ToolCard RulerInCork
 * @author Federico Lichinchi
 * @author Luca Massini
 * @author Davide Lorenzi (JDocs)
 */
public class RulerInCork  implements ModelSideToolCard {

    private Die dieFromReserve;
    private static final int CARDNUMBER = 9;
    private int[] coordinates;


    /**
     * Super Catcher
     * This method catch all characteristics from the super class
     */
    public RulerInCork(){
        super();
    }

    /**
     * PlacementConstraint Checker
     * This method verifies if constraints are respected or not
     * @param newRow row in which the player wanna set the die after the application of the ToolCard
     * @param newColumn column in which the player wanna set the die after the application of the ToolCard
     * @param chosenDie is the die chosen on which wil be applied the effects of the chosen ToolCard
     * @param player is the player that bought the ToolCard
     * @return true if is possible to apply the move after the application of the ToolCard, false otherwise
     */
    private boolean arePlacementConstraintsRespected(int newRow, int newColumn, Die chosenDie, Player player) {
        if(player.getGrid().isGridEmpty())
            player.getGrid().setFirstTimeToTrue();
        ChooseCheckConstraint constraint = new ChooseCheckConstraint(new CheckDieNotAdjacency());
        return constraint.checkConstraint(newRow,newColumn,chosenDie,player);
    }

    /**
     * CardNumber Getter
     * This method catch the correct card number
     * @return the ToolCard Number
     */
    @Override
    public int getCardNumber() {
        return CARDNUMBER;
    }

    /**
     * ToolCard Applier
     * This method verifies the application of the ToolCard
     * @param controller is the game controller
     * @return true if the ToolCard has been correctly applied, false otherwise
     */
    @Override
    public boolean applyToolCard(Controller controller) {
        getParameters(controller);
        if(dieFromReserve == null || coordinates[0] == -1)
            return false;
        else {
            DieMover mover = new DieMover();
            if (arePlacementConstraintsRespected(coordinates[0], coordinates[1], dieFromReserve, controller.getPlayerOfCurrentTurn())) {
                mover.moveDie(dieFromReserve, coordinates[0], coordinates[1], controller.getPlayerOfCurrentTurn());
                controller.removeDieFromReserve(dieFromReserve);
                controller.setInsertedDie(true);
                return true;
            } else
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
        dieFromReserve = controller.getReserveChosenDie();
        if(dieFromReserve != null) {
            coordinates = controller.getDiePositionChangeCoordinates();
        }
    }

}
