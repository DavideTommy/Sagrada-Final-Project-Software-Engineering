package it.polimi.se2018.controller.toolCards;

import it.polimi.se2018.controller.Controller;
import it.polimi.se2018.controller.effects.checkMethods.checkConstraintMethods.CheckDieNoColorConstraints;
import it.polimi.se2018.controller.effects.checkMethods.checkConstraintMethods.ChooseCheckConstraint;
import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.Player;

/**
 * BrushForEglomise Class extends CheckAndPick implements ModelSideToolCard
 * This class represents the ToolCard BrushForEglomise
 * @author Luca Massini
 * @author Davide Lorenzi (JDocs)
 */
public class BrushForEglomise extends CheckAndPick implements ModelSideToolCard {

    private static final int CARDNUMBER = 2;

    /*
    NB: this class extends CheckAndPick class and so it also can obviously contains his method that are:
    isPossibleToPick, pickADie and finally moveTheDie
     */

    /**
     * Super Catcher
     * This method catch all characteristics from the super class
     */
    public BrushForEglomise(){
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
    @Override
    public boolean arePlacementConstraintsRespected(int newRow, int newColumn, Die chosenDie, Player player){
        if(player.getGrid().isGridEmpty())
            player.getGrid().setFirstTimeToTrue();
        ChooseCheckConstraint constraintChecker = new ChooseCheckConstraint(new CheckDieNoColorConstraints());
        return constraintChecker.checkConstraint(newRow, newColumn, chosenDie, player);
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
        return apply(controller);
    }


    /**
     * Parameters Getter
     * This method catch all parameters needed for the application of the ToolCard
     * @param controller is the game controller
     */
    @Override
    public void getParameters(Controller controller) {
        controllerInteraction(controller);
    }

}
