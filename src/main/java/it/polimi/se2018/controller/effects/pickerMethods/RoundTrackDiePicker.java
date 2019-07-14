package it.polimi.se2018.controller.effects.pickerMethods;

import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.RoundTrack;
import it.polimi.se2018.model.mv.Model;

/**
 * RoundTrackPicker Class
 * This class allows to pick a die from the RoundTrack
 * @author Luca Massini
 * @author Davide Lorenzi (JDocs)
 */
public class RoundTrackDiePicker {

    /**
     * Picker
     * This method picks a die from the RoundTrack
     * @param model is the logic unit of the player
     * @param roundTrackIndex is the pointer to a cell of the Array of Die on the RoundTrack
     * @return the chosen die
     */
    public Die getPickedDie(int roundTrackIndex, Model model){
        RoundTrack roundTrack = model.getMatch().getRoundTrack();
        Die die = null;
        try {
            die = roundTrack.getRemainingDice().get(roundTrackIndex).clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        roundTrack.getRemainingDice().remove(roundTrackIndex);
        return die;
    }


}
