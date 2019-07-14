package it.polimi.se2018.controller.effects.checkMethods.checkConstraintMethods;

import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.Player;

/**
 * ConstraintCheck Chooser Class
 * This class allows tho chose the correct ConstraintCheck method for the ToolCard to apply
 * @author Luca Massini
 * @author Davide Lorenzi (JDocs)
 */
public class ChooseCheckConstraint {
    private CheckConstraint constraint;

    /**
     * ChoseCheckConstraint Constructor
     * This method creates a new constraint checker
     * @param constraint chosen constraint to check
     */
    public ChooseCheckConstraint(CheckConstraint constraint){
        this.constraint = constraint;
    }

    /**
     * Constraint Checker
     * This method verifies if constraints are respected or not
     * @param newRow row in which the player wanna set the die after the application of the ToolCard
     * @param newColumn column in which the player wanna set the die after the application of the ToolCard
     * @param die is the die chosen on which wil be applied the effects of the chosen ToolCard
     * @param player is the player that bought the ToolCard
     * @return true if is possible to apply the move after the application of the ToolCard, false otherwise
     */
    public boolean checkConstraint(int newRow, int newColumn, Die die, Player player){
        return constraint.checkConstraint(newRow, newColumn, die, player);
    }

    /**
     * Constraint Getter
     * This method returns the a required constraint
     * @return required constraint
     */
    public CheckConstraint getConstraint() {
        return constraint;
    }

}
