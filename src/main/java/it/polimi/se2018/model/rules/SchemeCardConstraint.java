package it.polimi.se2018.model.rules;

import it.polimi.se2018.model.Die;

/**
 * SchemeCardConstraint Interface
 * This interface returns a boolean for a valid constraint
 * @author Luca Massini
 */
public interface SchemeCardConstraint {
    boolean isValidConstraint(Die dice, int line, int column);
}
