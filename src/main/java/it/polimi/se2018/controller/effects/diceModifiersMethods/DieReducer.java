package it.polimi.se2018.controller.effects.diceModifiersMethods;

import it.polimi.se2018.controller.effects.checkMethods.checkDieChecker.CheckDieDecrement;
import it.polimi.se2018.model.Die;

/**
 * DieReducer Class implements DieModifier
 * This class allows reduce the value of the chosen die by one
 * @author Luca Massini
 * @author Davide Lorenzi (JDocs)
 */
public class DieReducer implements DieModifier {

    /**
     * Die Reducer
     * This method reduces by 1 the value of the chosen die
     * @param die the chosen die
     */
    private void decrementDie(Die die){
        int value = die.getDieNumber()-1;
        if(new CheckDieDecrement().checkDieChecker(die))
            die.setDieNumber(value);
    }


    /**
     * Die Modifier
     * This method applies the die reducing
     * @param die chosen die to reduce
     */
    @Override
    public void modifyDie(Die die) {
        decrementDie(die);
    }
}
