package it.polimi.se2018.controller.effects.structuresModifiersMethods;

import it.polimi.se2018.model.Player;

/**
 * DieRemover Class
 * This removes the chosen die
 * @author Luca Massini
 * @author Davide Lorenzi (JDocs)
 */
public class DieRemover {


    /**
     * Die Remover
     * This method removes the chosen die
     * @param row row coordinate of the chosen die
     * @param column column coordinate of the chosen die
     * @param player player that bought the ToolCard
     */
    public void removeDie(int row, int column , Player player){
        player.getGrid().removeDie(row,column);
    }
}
