package it.polimi.se2018.controller.effects.diceModifiersMethods;

import it.polimi.se2018.model.Die;

/**
 * Die Modifier Class
 * This class allows to apply an effect of a ToolCard
 * @author Luca Massini
 * @author Davide Lorenzi (JDocs)
 */
public interface DieModifier {

    /**
     * Die Modifier
     * This method applies the effect of a ToolCard on a die
     * @param die chosen die by the user
     */
    void modifyDie(Die die);
}
