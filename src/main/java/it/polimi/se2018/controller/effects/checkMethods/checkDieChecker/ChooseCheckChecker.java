package it.polimi.se2018.controller.effects.checkMethods.checkDieChecker;

import it.polimi.se2018.model.Die;

/**
 * ChoseCheckModify
 * This class allows to instance a modifier checker
 * @author Luca Massini
 * @author Davide Lorenzi (JDocs)
 */
public class ChooseCheckChecker {

    private CheckDieChecker modifierChecker;

    /**
     * ChooseCheckChecker Constructor
     * This method creates the modifier checker
     * @param modifier is the modifier parameter instanced to chose the ckecker method
     */
    public ChooseCheckChecker(CheckDieChecker modifier){
        this.modifierChecker = modifier;
    }

    /**
     * CheckModifier Returner
     * This method instance and applies the check modifier on a die
     * @param die chosen die by the user
     * @return true if the check modifier is okay, false otherwise
     */
    public boolean checkModify(Die die){
        return modifierChecker.checkDieChecker(die);
    }

}
