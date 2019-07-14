package it.polimi.se2018.controller.effects.checkMethods.checkDieChecker;

import it.polimi.se2018.model.Die;

/**
 * CheckDieNoColorConstraints Class implements CheckDieChecker
 * This class verifies if is possible to apply a card respecting the max value of a die
 * @author Luca Massini (all the code except JDocs)
 * @author Davide Lorenzi (JDocs)
 */
public class CheckDieEnhance implements CheckDieChecker {

    /**
     * Enhancer Verifier
     * This method verifies if is it possible to enhance the chosenDie.
     * @param die is the chosen die
     * @return true if is possible to apply the move after the application of the ToolCard, false otherwise
     */
    private boolean isPossibleToEnhance(Die die){
        return die != null && die.getDieNumber()<6;
    }

    /**
     * CheckDie Checker
     * This method verifies if is possible to enhance the value of a die
     * @param die die chosen by the user
     * @return true if is possible to apply a modification, false otherwise
     */
    @Override
    public boolean checkDieChecker(Die die) {
        return isPossibleToEnhance(die);
    }
}
