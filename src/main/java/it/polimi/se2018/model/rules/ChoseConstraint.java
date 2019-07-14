package it.polimi.se2018.model.rules;

import it.polimi.se2018.model.Die;

/**
 * ChoseConstraint Class
 * This class is used to apply the placement rules
 * @author Luca Massini
 * @author Davide Lorenzi (JDocs)
 */
public class ChoseConstraint  {

    private Die die;
    private SchemeCardConstraint constraint;

    /**
     * Constraint Chooser Constructor
     * This method is used to choose the constraint
     * @param constraint is the interface SchemeCardConstraint
     * @param die is the die of which we must check if respects all the SchemeCard constraint
     */
    ChoseConstraint(SchemeCardConstraint constraint,Die die){
        this.constraint = constraint;
        this.die = die;
    }

    /**
     * This method executes a constraint basing on the dynamic type of the object that is passed to the Constructor
     * @param column column coordinate of the chosen die
     * @param row row coordinate of the chosen die
     * @return isValidConstraint(grid)
     */

    protected boolean executeConstraint(int row,int column){
        return constraint.isValidConstraint(die, row, column);
    }
}
