package it.polimi.se2018.controller.effects.checkMethods.checkConstraintMethods;

import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.Grid;
import it.polimi.se2018.model.Player;
import it.polimi.se2018.model.rules.Rules;

/**
 * CheckDieNoValueColorConstraints Class implements CheckConstraint
 * This class verifies if is possible to apply a card respecting value die constraints
 * @author Luca Massini
 * @author Davide Lorenzi (JDocs)
 */
public class CheckDieNoValueConstraints implements CheckConstraint {

    /**
     * Move Verifier
     * This method calls the proper check rule to verify if is possible to apply a ToolCard or not. It
     * checks if all the placement rules except those of value would be respected in a placement in (newRow,newColumn)
     * in player's grid.
     * @param newRow row in which the player wanna set the die after the application of the ToolCard
     * @param newColumn column in which the player wanna set the die after the application of the ToolCard
     * @param chosenDie is the die chosen on which wil be applied the effects of the chosen ToolCard
     * @param player is the player that bought the ToolCard
     * @return true if is possible to apply the move after the application of the ToolCard, false otherwise
     */
    private boolean isPossibleToMove(int newRow, int newColumn, Die chosenDie, Player player){
        Rules rules= Rules.newRules();
        Grid playerGrid = player.getGrid();
        return rules.checkExceptionValueRules(chosenDie, newRow, newColumn, playerGrid);
    }


    /**
     * Constraint Checker
     * This method verifies if all constraint are respected
     * @param newRow row coordinate of the die the player is gonna set
     * @param newColumn column coordinate of the die the player is gonna set
     * @param chosenDie die chosen by the player
     * @param player player that bought
     * @return true if is constraint are verified and is possible to move, false otherwise
     */
    @Override
    public boolean checkConstraint(int newRow, int newColumn, Die chosenDie, Player player) {
        return isPossibleToMove(newRow, newColumn, chosenDie, player);
    }
}
