package it.polimi.se2018.controller.effects.structuresModifiersMethods;

import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.Player;

/**
 * DieMover Class
 * This class moves the chosen die after the application of the ToolCard
 * @author Luca Massini
 * @author Davide Lorenzi (JDocs)
 */
public class DieMover {

    /**
     * Die Mover
     * This method move the chosen die
     * @param newRow row in which the player wanna set the die after the application of the ToolCard
     * @param newColumn column in which the player wanna set the die after the application of the ToolCard
     * @param chosenDie is the die chosen on which wil be applied the effects of the chosen ToolCard
     * @param player is the player that bought the ToolCard
     */
    public void moveDie(Die chosenDie, int newRow, int newColumn, Player player){
        player.getGrid().addDie(chosenDie, newRow, newColumn);
    }

}
