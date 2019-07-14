package it.polimi.se2018.controller.effects.checkMethods.checkConstraintMethods;

import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.Grid;
import it.polimi.se2018.model.Player;
import it.polimi.se2018.model.rules.Rules;

/**
 * CheckDieNoColorConstraints Class implements CheckConstraint
 * This class verifies if is possible to apply a card respecting color die constraints
 * @author Luca Massini
 * @author Davide Lorenzi (JDocs)
 */
public class CheckDieNoColorConstraints implements CheckConstraint {

    /**
     * Move Verifier
     * This method calls the proper check rule to verify if is possible to apply a ToolCard or not. It
     * checks if all the rules except those of color would be respected in a placement in (newRow, newColumn)
     * in the player's grid.
     * @param newRow row in which the player wanna set the die after the application of the ToolCard
     * @param newColumn column in which the player wanna set the die after the application of the ToolCard
     * @param chosenDie is the die chosen on which wil be applied the effects of the chosen ToolCard
     * @param player is the player that bought the ToolCard
     * @return true if is possible to apply the move after the application of the ToolCard, false otherwise
     */
    private boolean isPossibleToMove(int newRow, int newColumn, Die chosenDie, Player player){
        Grid playerGird = player.getGrid();
        Rules rules = Rules.newRules();
        return rules.checkExceptionColorRules(chosenDie, newRow,newColumn,playerGird);
    }

    /**
     * Constraint Checker
     * This method verifies if constraints are respected or not
     * @param newRow row in which the player wanna set the die after the application of the ToolCard
     * @param newColumn column in which the player wanna set the die after the application of the ToolCard
     * @param chosenDie is the die chosen on which wil be applied the effects of the chosen ToolCard
     * @param player is the player that bought the ToolCard
     * @return true if is possible to apply the move after the application of the ToolCard, false otherwise
     */
    @Override
    public boolean checkConstraint(int newRow, int newColumn, Die chosenDie, Player player) {
        return isPossibleToMove(newRow,newColumn,chosenDie,player);
    }
}


