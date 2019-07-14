package it.polimi.se2018.model.score;

import it.polimi.se2018.model.Player;
import it.polimi.se2018.model.cards.PublicObjectiveCard;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * Score Calculator Class ScoreTrack
 * This class is used to compute the total score of a player
 * @author Davide Lorenzi
 * @author Federico Lichinchi
 * @author Luca Massini (publicObjectiveCounter method)
 */

public class CalcScore implements ScoreTrack {

    /**
     * Score Function
     * This method sums all parameters (Number of favour counters, free cells, points from Public and Private objective
     * cards) to obtain the fina score of the player
     * @param cards is the list of the three public objective cards that are assigned in the
     *              game to all players
     * @param player player and his attributes
     * @return the score of the player
     */
    @Override
    public int calcScore(Player player, List<PublicObjectiveCard> cards) {
        player.addScore(player.getNumFavCounter() + publicObjectiveCounter(player,cards) + privateObjectiveCounter(player) - freeCellsCounter(player));
        return player.getScore();
    }

    /**
     * PrivateObjective Points Counter
     * This method sums the value of all Die of the color indicated on the player's PrivateObjectiveCard
     * @param player is a player of the game
     * @return PrivateObjective score
     */

    public int privateObjectiveCounter(Player player) {
        int diceTotalValue = 0;
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 5; j++) {
                if (player.getGrid().getDie(i, j) != null && Objects.equals(player.getPrivateObjectiveCard().getColor(),
                        player.getGrid().getDie(i, j).getDieColor())) {
                    diceTotalValue = diceTotalValue + player.getGrid().getDie(i, j).getDieNumber();
                }
            }
        return diceTotalValue;
    }

    /**
     * FreeCells Counter
     * This method count how many cells are empty
     * @param player is a player object of the game
     * @return PrivateObjective score
     */
    @Override
    public int freeCellsCounter(Player player) {
        int freeCells = 0;
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 5; j++) {
                if (player.getGrid().getDie(i, j) == null) {
                    freeCells = freeCells + 1;
                }
            }

        return freeCells;
    }

    /**
     * PrivateObjective Points Counter
     * This method sums the value of the points earned by the 3 active PublicObjectiveCard
     * @param player is the player whose score on the public cards must be calculated
     * @param cards is the list of the 3 public objective cards that are assigned in the
     *              game to all players
     * @return PublicObjective score that is the score  obtained from the application of
     *              public cards
     */
    @Override
    public int publicObjectiveCounter(Player player, List<PublicObjectiveCard> cards) {
        ScoreFactory factory = ScoreFactory.newScoreFactory();
        Iterator<PublicObjectiveCard> iterator = cards.iterator();
        int result=0;
        while(iterator.hasNext()){
            try {
                result += factory.getCalculationScoreObject(iterator.next().getCardNumber()).publicScore(player.getGrid());
            } catch (CloneNotSupportedException e) {
                Logger.getLogger("Grid clone failed.");
            }
        }
        return result;
        }
}
