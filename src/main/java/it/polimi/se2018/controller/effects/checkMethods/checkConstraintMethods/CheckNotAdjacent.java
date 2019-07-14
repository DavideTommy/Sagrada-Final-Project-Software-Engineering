package it.polimi.se2018.controller.effects.checkMethods.checkConstraintMethods;

import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.Grid;
import it.polimi.se2018.model.Player;
import it.polimi.se2018.model.rules.Rules;


/**
 * AdjacencyChecker Class implements CheckConstraint
 * This class verifies the adjacency or not of a die inserted after the application of a ToolCard
 * @author Luca Massini
 * @author Davide Lorenzi (JDocs)
 */
public class CheckNotAdjacent implements CheckConstraint {

    /**
     * Adjacency Checker
     * This method verifies if the new die is adjacent to another one
     * @param newRow row in which the player wanna set the die after the application of the ToolCard
     * @param newColumn column in which the player wanna set the die after the application of the ToolCard
     * @param chosenDie is the die chosen on which wil be applied the effects of the chosen ToolCard
     * @param player is the player that bought the ToolCard
     * @return true if the new die is not adjacent to another one, false otherwise
     */
    private boolean isNotAdjacent(int newRow, int newColumn, Die chosenDie, Player player){
        Rules rules = Rules.newRules();
        Grid playerGrid = player.getGrid();
        return rules.checkNotAdjacency(chosenDie,newRow,newColumn,playerGrid);
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
        return isNotAdjacent(newRow,newColumn,chosenDie,player);
    }
}
