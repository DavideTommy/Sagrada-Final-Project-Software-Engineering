package it.polimi.se2018.controller.effects.checkMethods.checkConstraintMethods;

import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.Player;

/**
 * ConstraintChecker Interface
 * This interface is used to call all constraint checker
 * @author Luca Massini
 * @author Davide Lorenzi (JDocs)
 */
public interface CheckConstraint {

    /**
     * @param newRow row in which the player wanna set the die after the application of the ToolCard
     * @param newColumn column in which the player wanna set the die after the application of the ToolCard
     * @param chosenDie is the die chosen on which wil be applied the effects of the chosen ToolCard
     * @param player is the player that bought the ToolCard
     * @return true if the constraint are respected, false otherwise
     */
    boolean checkConstraint(int newRow, int newColumn, Die chosenDie, Player player);
}
