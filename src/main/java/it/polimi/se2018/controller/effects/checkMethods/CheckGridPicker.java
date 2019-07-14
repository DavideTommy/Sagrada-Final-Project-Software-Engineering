package it.polimi.se2018.controller.effects.checkMethods;

import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.Player;

/**
 * CheckPick Class
 * This class verifies if is possible pick a die from the grid
 * @author Luca Massini
 * @author Davide Lorenzi (JDocs)
 */
public class CheckGridPicker {

    /**
     * Picker Checker
     * This method verifies if the chosen die to pick exists or not
     * @param row row coordinate of the chosen die
     * @param column column coordinate of the chosen die
     * @param player player that bought the ToolCard
     * @return true if is possible to apply the move after the application of the ToolCard, false otherwise
     */
    public boolean isPossibleToPick(int row, int column,Player player){
        Die tmp = player.getGrid().getDie(row,column);
        return tmp != null;

    }

}
