package it.polimi.se2018.controller.effects.diceModifiersMethods;

import it.polimi.se2018.controller.effects.checkMethods.checkDieChecker.CheckDieEnhance;
import it.polimi.se2018.model.Die;

/**
 * DieEnhancer Class implements DieModifier
 * This class increments the value of the chosen die by one
 * @author Luca Massini
 * @author Davide Lorenzi (JDocs)
 */
public class DieEnhancer implements DieModifier {

    /**
     * Die Enhancer
     * This method increments by 1 the value of the chosen die
     * @param die the chosen die
     */
    private void enhanceDie(Die die){
        int value = die.getDieNumber()+1;
        if(new CheckDieEnhance().checkDieChecker(die))
            die.setDieNumber(value);
    }

    /**
     * Die Modifier
     * This method applies the die enhancing
     * @param die chosen die to enhance
     */
    @Override
    public void modifyDie(Die die) {
        enhanceDie(die);
    }
}
