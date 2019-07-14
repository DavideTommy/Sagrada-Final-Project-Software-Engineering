package it.polimi.se2018.controller.effects.diceModifiersMethods;

import it.polimi.se2018.model.Die;

import java.util.Random;

/**
 * DieRoller Class implements DieModifier
 * This method allows to throw a chosen die to exchange his value
 * @author Luca Massini
 * @author Davide Lorenzi (JDocs)
 */
public class RollDie implements DieModifier {


    /**
     * Die Roller Constructor
     * This method chooses a new random value for the chosen die
     * @param die chosen die
     */
    private void rollDie(Die die){
        Random random = new Random();
        int newDieValue = random.nextInt(5) + 1;
        die.setDieNumber(newDieValue);
    }

    /**
     * Die Modifier
     * This method applies the die rolling
     * @param die chosen die to roll
     */
    @Override
    public void modifyDie(Die die) {
        rollDie(die);
    }
}
