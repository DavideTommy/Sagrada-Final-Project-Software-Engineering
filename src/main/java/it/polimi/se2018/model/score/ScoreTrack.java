package it.polimi.se2018.model.score;

import it.polimi.se2018.model.Player;
import it.polimi.se2018.model.cards.PublicObjectiveCard;

import java.util.ArrayList;
import java.util.List;

/**
 * ScoreTrack Interface
 * This interface is implemented by CalcScore class is used to calculate the players score
 */

public interface ScoreTrack {

    /**
     * Score Computer
     * This method is used to calc the player's total score
     * @param player player that we've to count the score
     * @param cards ArrayList of PublicObjectiveCards
     * @return total score of the player
     */
    int calcScore(Player player, List<PublicObjectiveCard> cards);

    /**
     * PrivateScore Computer
     * This method is used to calc the player's score earned by his PrivateObjectiveCard
     * @param player player that we've to count the score
     * @return Private Score of the player
     */
    int privateObjectiveCounter(Player player);

    /**
     * FreeCells Counter
     * This method is used to calc the number of unoccupied cells
     * @param player player that we've to verify the number of cells
     * @return number of Free Cells
     */
    int freeCellsCounter(Player player);

    /**
     * PublicScore Computer
     * This method is used to calc the player's score earned by his PublicObjectiveCard
     * @param player player that we've to count the score
     * @param cards ArrayList of PublicObjectiveCards
     * @return Public Score of the player
     */
    int publicObjectiveCounter(Player player, List<PublicObjectiveCard> cards);

}
