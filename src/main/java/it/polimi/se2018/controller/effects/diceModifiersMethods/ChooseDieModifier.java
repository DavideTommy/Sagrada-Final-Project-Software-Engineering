package it.polimi.se2018.controller.effects.diceModifiersMethods;

import it.polimi.se2018.model.Die;

/**
 * ChooseDieModifier
 * This class instances a modifier for a die
 * @author Luca Massini
 * @author Davide Lorenzi (JDocs)
 */
public class ChooseDieModifier {

    private DieModifier modifier;

    /**
     * This method instances the die modifier
     * @param modifier is the parameter of the instanced modifier
     */
    public ChooseDieModifier(DieModifier modifier){
        this.modifier = modifier;
    }

    /**
     * Die Modifier
     * This method applies the die modification
     * @param die chosen die to modify
     */
    public void modifyDie(Die die){
        modifier.modifyDie(die);
    }

}
