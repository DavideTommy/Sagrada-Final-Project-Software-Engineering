package it.polimi.se2018.controller.effects.checkMethods.checkDieChecker;

import it.polimi.se2018.model.Die;

/**
 * CheckDieDecrement Class
 * This class verifies if is possible to apply a card respecting the minimum value of the die
 * @author Luca Massini
 * @author Davide Lorenzi (JDocs)
 */
public class CheckDieDecrement implements CheckDieChecker {

    /**
     * Decrement Checker
     * This method verifies if is possible to reduce the value of a die
     * @param die chosen die
     * @return true if is possible to apply the move after the application of the ToolCard, false otherwise
     */
    private boolean isPossibleToDecrement(Die die){
        return die != null && die.getDieNumber()>1;
    }

    /**
     * CheckDie Checker
     * This method verifies if is possible to apply the decrement of die value
     * @param die chosen die to reduce
     * @return true if is possible to decrement the die, false otherwise
     */
    @Override
    public boolean checkDieChecker(Die die) {
        return isPossibleToDecrement(die);
    }
}
