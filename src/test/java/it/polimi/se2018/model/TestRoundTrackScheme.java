package it.polimi.se2018.model;

import it.polimi.se2018.model.cards.PrivateObjectiveCard;
import it.polimi.se2018.model.cards.PublicObjectiveCard;
import it.polimi.se2018.model.cards.ToolCard;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

/**
 * RoundTrackScheme Tester
 * Test the efficiency of the application of PlacementValue
 * @author Federico Lichinchi
 * @author Davide Lorenzi (JDocs)
 */
public class TestRoundTrackScheme {
    ArrayList<ToolCard> tc = new ArrayList<ToolCard>(3);
    ArrayList<PublicObjectiveCard> poc = new ArrayList<PublicObjectiveCard>(3);
    ArrayList<PrivateObjectiveCard> poc2 = new ArrayList<PrivateObjectiveCard>(3);
    ArrayList<Player> players = new ArrayList<Player>(4);
    RoundTrack r;

    {
        try {
            r = new RoundTrack(players, tc, poc, poc2);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }


    /**
     * NextTurn tests
     * @author Davide Lorenzi
     * verifies if round value is between '1' and '10';
     * verifies that the playerTurn has only '1' or '2' value
     * verifies if nextPlayer index value is between '-1' and the number of players
     */
    @Test
    public void testNextTurn() {
        Assert.assertTrue(r.getRound() <= 10 & r.getRound() > 0);
        Assert.assertTrue(r.getPlayerTurn() == 1 || r.getPlayerTurn() == 2);
        Assert.assertTrue(players.size() >= r.getNextPlayerIndex() & r.getNextPlayerIndex() >= -1);

        Assert.assertFalse(r.getRound() >= 10 & r.getRound() < 0);
        Assert.assertFalse(r.getPlayerTurn() != 1 & r.getPlayerTurn() != 2);
        Assert.assertFalse(players.size() <= r.getNextPlayerIndex() & r.getNextPlayerIndex() <= -1);

    }

    /**
     * NextRound tests
     *check if all the initial values of playerTurn, nextPlayerIndex are set
     */
    @Test
    public void testNextRound() {
        Assert.assertTrue(r.getRound() != 10 && r.getNextPlayerIndex() == 0 && r.getPlayerTurn() == 1);
        Assert.assertFalse(r.getRound() != 10 && (r.getNextPlayerIndex() != 0 || r.getPlayerTurn() != 1));
    }

}