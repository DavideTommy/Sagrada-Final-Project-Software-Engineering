package it.polimi.se2018.controller.effects.checkMethods.checkDieChecker;

import it.polimi.se2018.model.Die;

/**
 * CheckDieChecker Interface
 * This interface verifies if is possible to apply a modification or not
 * @author Luca Massini
 * @author Davide Lorenzi (JDocs)
 */
public interface CheckDieChecker {

    /**
     * CheckDieChecker Constructor
     * This method builds the check die checker
     * @param die die chosen by the user
     * @return true if is possible to apply a modification, false otherwise
     */
    boolean checkDieChecker(Die die);

}
