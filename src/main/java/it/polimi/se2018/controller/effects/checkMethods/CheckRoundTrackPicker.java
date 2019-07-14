package it.polimi.se2018.controller.effects.checkMethods;

import it.polimi.se2018.model.mv.Model;

/**
 * CheckRoundTrackPicker Class
 * This class verifies if is possible to apply a card picking a die from the RoundTrack
 * @author Luca Massini
 * @author Davide Lorenzi (JDocs)
 */
public class CheckRoundTrackPicker {

    /**
     * Picker Checker
     * This method verifies if the chosen die to pick exists or not
     * @param model is the logic unit of the player
     * @param roundTrackIndex is the position of the picking in the RoundTrack
     * @return true if is possible to apply the move after the application of the ToolCard, false otherwise
     * @throws IndexOutOfBoundsException In case of wrong roundtrackIndex
     */
    public boolean isPossibleToPick(int roundTrackIndex, Model model) throws IndexOutOfBoundsException {
        return model.getMatch().getRoundTrack().getRemainingDice().get(roundTrackIndex) != null;
    }

}
