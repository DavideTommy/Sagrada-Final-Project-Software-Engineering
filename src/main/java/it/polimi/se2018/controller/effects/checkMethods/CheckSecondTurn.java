package it.polimi.se2018.controller.effects.checkMethods;

import it.polimi.se2018.model.mv.Model;

/**
 * CheckTurn Class
 * This class verifies if is the second turn of a player or not
 * @author Luca Massini
 * @author Davide Lorenzi (JDocs)
 */
public class CheckSecondTurn {

    /**
     * SecondTurn Verifier
     * This method verifies if it is the second turn of a player or not
     * @param model is the model class of the game
     * @return true if is possible to apply the move after the application of the ToolCard, false otherwise
     */
    public boolean isThisTheSecondTurn(Model model){
        return model.getMatch().getRoundTrack().getPlayerTurn() == 2;
    }

}
