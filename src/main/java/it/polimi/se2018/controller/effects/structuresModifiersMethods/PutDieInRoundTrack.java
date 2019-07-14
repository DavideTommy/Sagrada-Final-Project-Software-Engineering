package it.polimi.se2018.controller.effects.structuresModifiersMethods;

import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.mv.Model;

/**
 * Die Putter Class
 * This class allows to put a die into the RoundTrack
 * @author Luca Massini
 * @author Davide Lorenzi (JDocs)
 */
public class PutDieInRoundTrack {

    /**
     * Die Putter Constructor
     * This method allows to put a die on the RoundTrack after the application of a ToolCard
     * @param dice chosen die
     * @param model is the logic core of the game
     */
    public void putInRoundTrack(Die dice, Model model) {
        model.getMatch().getRoundTrack().getRemainingDice().add(dice);
    }
}
