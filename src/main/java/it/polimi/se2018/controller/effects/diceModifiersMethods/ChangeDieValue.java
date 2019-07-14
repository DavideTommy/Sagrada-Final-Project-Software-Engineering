package it.polimi.se2018.controller.effects.diceModifiersMethods;

import it.polimi.se2018.model.Die;

/**
 * ChangeDie Modifier Class
 * This class instances a modifier for a die
 * @author Luca Massini
 * @author Davide Lorenzi (JDocs)
 */
public class ChangeDieValue implements DieModifier {

    private int newValue;

    /**
     * Value Changer
     * This method sets a new defined value to the chosen die
     * @param value new value of the die
     */
    public ChangeDieValue(int value){
        this.newValue = value;
    }

    /**
     * Die Modifier
     * This method applies the previous modification
     * @param die chosen die by the user
     */
    @Override
    public void modifyDie(Die die) {
        die.setDieNumber(newValue);
    }
}
