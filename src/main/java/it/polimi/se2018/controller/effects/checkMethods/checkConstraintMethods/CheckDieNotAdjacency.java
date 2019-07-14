package it.polimi.se2018.controller.effects.checkMethods.checkConstraintMethods;

import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.Player;
import it.polimi.se2018.model.rules.Rules;

/**
 * NotAdjacencyChecker Class implements CheckConstraint
 * This class verifies the not adjacency of die
 * @author Luca Massini
 * @author Davide Lorenzi (JDocs)
 */
public class CheckDieNotAdjacency implements CheckConstraint {

    /**
     * IsNotAdjacent Checker
     * This method verifies if Die are not adjacent
     * @param newRow row in which the player wanna set the die after the application of the ToolCard
     * @param newColumn column in which the player wanna set the die after the application of the ToolCard
     * @param chosenDie is the die chosen on which wil be applied the effects of the chosen ToolCard
     * @param player is the player that bought the ToolCard
     * @return true if the not adjacency is respected, false otherwise
     */
    private boolean isNotAdjacent(int newRow, int newColumn, Die chosenDie, Player player){
        return Rules.newRules().checkAllExceptAdjacency(chosenDie,newRow,newColumn,player.getGrid());
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
