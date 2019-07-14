package it.polimi.se2018.controller.effects.diceModifiersMethods;

import it.polimi.se2018.model.Die;

/**
 * DieTurner Class implements DieModifier
 * This class turns a die on the opposite side to change his value
 * @author Luca Massini
 * @author Davide Lorenzi (JDocs)
 */
public class TurnTheDieOpposite implements DieModifier {

    /**
     * Die Turner
     * This methods turn the chosen die on the other opposite face
     * @param die chosen die
     */
    private void turnTheDie(Die die){
        die.setDieNumber(7-die.getDieNumber());
    }

    /**
     * Die Modifier
     * This method applies the die turning
     * @param die chosen die to turn
     */
    @Override
    public void modifyDie(Die die) {
        turnTheDie(die);
    }
}
