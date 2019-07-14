package it.polimi.se2018.controller.effects.pickerMethods;

import it.polimi.se2018.controller.effects.structuresModifiersMethods.DieRemover;
import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.Player;

/**
 * DiePick Class
 * This class picks a die from the grid
 * @author Luca Massini
 * @author Davide Lorenzi (JDocs)
 */
public class GridDiePicker {

    /**
     * Picker
     * This method picks the chosen die from the player's grid
     * @param row row coordinate of the chosen die
     * @param column column coordinate of the chosen die
     * @param player player that bought the ToolCard
     * @return the chosen die
     */
    public Die getPickedDie(int row, int column, Player player){
        DieRemover remover = new DieRemover();
        Die chosenDie= null;
        try {
            chosenDie = player.getGrid().getDie(row, column).clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        remover.removeDie(row, column, player);
        return chosenDie;
    }
}
