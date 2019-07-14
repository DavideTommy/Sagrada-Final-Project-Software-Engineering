package it.polimi.se2018.model;

import it.polimi.se2018.model.cards.CardParser;
import it.polimi.se2018.model.cards.PrivateObjectiveCard;
import it.polimi.se2018.model.cards.PublicObjectiveCard;
import it.polimi.se2018.model.cards.ToolCard;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * TestPlacementValueConstraint Tester
 * Test the efficiency of the application of PlacementValue
 * @author Federico Lichinchi
 * @author Davide Lorenzi (JDocs)
 */
public class TestRoundTrack {

    RoundTrack roundTrack;
    ArrayList<ToolCard> toolCards;
    ArrayList<PublicObjectiveCard> publicObjectiveCards;
    ArrayList<PrivateObjectiveCard> privateObjectiveCards;
    ArrayList<Player> players;

    /**
     * SetUp Method
     * This method prepare the test ambient
     * @throws CloneNotSupportedException if is not possible to clone a random private objective card
     */
    @Before
    public void SetUp() throws CloneNotSupportedException {
        privateObjectiveCards = new ArrayList<>(3);
        publicObjectiveCards = new ArrayList<>(3);
        toolCards = new ArrayList<>(3);
        try {
            privateObjectiveCards.add(CardParser.newCardParser().getRandomPrivateObjectiveCard());
            privateObjectiveCards.add(CardParser.newCardParser().getRandomPrivateObjectiveCard());
            privateObjectiveCards.add(CardParser.newCardParser().getRandomPrivateObjectiveCard());

            toolCards.add(CardParser.newCardParser().getRandomToolCard());
            toolCards.add(CardParser.newCardParser().getRandomToolCard());
            toolCards.add(CardParser.newCardParser().getRandomToolCard());

            publicObjectiveCards.add(CardParser.newCardParser().getRandomPublicObjectiveCard());
            publicObjectiveCards.add(CardParser.newCardParser().getRandomPublicObjectiveCard());
            publicObjectiveCards.add(CardParser.newCardParser().getRandomPublicObjectiveCard());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        players = new ArrayList<>(3);
        players.add(new Player("amsdls","asdasd","Green",new Grid(new SchemeCard("Virtus")),null));
        players.add(new Player("qwrefw","afasdf","Blue",new Grid(new SchemeCard("Virtus")),null));
        players.add(new Player("qqqqq","qqqqq","Yellow",new Grid(new SchemeCard("Virtus")),null));

        roundTrack = new RoundTrack(players, toolCards, publicObjectiveCards, privateObjectiveCards);

        roundTrack.addDie(new Die("Blue", 3));
        roundTrack.addDie(new Die("Blue", 2));
        roundTrack.addDie(new Die("Green", 5));
    }

    /**
     * RoundTrackComponents Tester
     * This method tests all components into the RoundTrack
     */
    @Test
    public void testComponentRoundTrack() {

        for (int i = 0; i < roundTrack.getPublicObjectiveCards().size(); i++) {
            Assert.assertNotNull(roundTrack.getPublicObjectiveCards().get(i));
        }
        for (int i = 0; i < roundTrack.getPlayers().size(); i++) {
            Assert.assertNotNull(roundTrack.getPlayers().get(i));
        }
        for (int i = 0; i < roundTrack.getToolCards().size(); i++) {
            Assert.assertNotNull(roundTrack.getToolCards().get(i));
        }
        for (int i = 0; i < roundTrack.getRemainingDice().size(); i++) {
            Assert.assertNotNull(roundTrack.getRemainingDice().get(i));
            Assert.assertTrue(roundTrack.getRemainingDice().get(i).getDieColor().equals("Red") ||
                    roundTrack.getRemainingDice().get(i).getDieColor().equals("Blue") ||
                    roundTrack.getRemainingDice().get(i).getDieColor().equals("Green") ||
                    roundTrack.getRemainingDice().get(i).getDieColor().equals("Yellow") ||
                    roundTrack.getRemainingDice().get(i).getDieColor().equals("Purple"));
            Assert.assertTrue(roundTrack.getRemainingDice().get(i).getDieNumber() > 0 &&
                    roundTrack.getRemainingDice().get(i).getDieNumber() < 7);
        }


    }

    /**
     * ArrayShifter Test
     * This method tests the correct shifting of the array of players
     * @throws CloneNotSupportedException if is not possible to clone a player into a new position
     */
    @Test
    public void shiftPlayerArray() throws CloneNotSupportedException {
        roundTrack.getPlayers().clear();
        try {
            roundTrack.shiftPlayerArray();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }


        players.add(new Player("amsdls","asdasd","Green",new Grid(new SchemeCard("Virtus")),null));
        players.add(new Player("qwrefw","afasdf","Blue",new Grid(new SchemeCard("Virtus")),null));
        players.add(new Player("qqqqq","qqqqq","Yellow",new Grid(new SchemeCard("Virtus")),null));

        roundTrack.shiftPlayerArray();

        for (int i = 0; i < roundTrack.getPlayers().size(); i++) {
            Assert.assertNotNull(roundTrack.getPlayers().get(i));
        }

        Assert.assertTrue(roundTrack.getRound() <= 10 & roundTrack.getRound() > 0);
        Assert.assertTrue(roundTrack.getPlayerTurn() == 1 || roundTrack.getPlayerTurn() == 2);
        Assert.assertTrue(players.size() >= roundTrack.getNextPlayerIndex() & roundTrack.getNextPlayerIndex() >= -1);

        Assert.assertFalse(roundTrack.getRound() >= 10 & roundTrack.getRound() < 0);
        Assert.assertFalse(roundTrack.getPlayerTurn() != 1 & roundTrack.getPlayerTurn() != 2);
        Assert.assertFalse(players.size() <= roundTrack.getNextPlayerIndex() & roundTrack.getNextPlayerIndex() <= -1);

        Assert.assertTrue(roundTrack.getRound() != 10 && roundTrack.getNextPlayerIndex() == 0 && roundTrack.getPlayerTurn() == 1);
        Assert.assertFalse(roundTrack.getRound() != 10 && (roundTrack.getNextPlayerIndex() != 0 || roundTrack.getPlayerTurn() != 1));

    }

    /**
     * Next Turn Tester
     * This method verifies that the counter of the player's turn works properly
     */
    @Test
    public void nextTurn() {
        Player player = roundTrack.getPlayerOfCurrentTurn();
        roundTrack.nextTurn();
        roundTrack.nextTurn();
        roundTrack.nextTurn();
        roundTrack.nextTurn();
        roundTrack.nextTurn();
        roundTrack.nextTurn();
        Assert.assertNotEquals(player, roundTrack.getPlayerOfCurrentTurn());
    }

    /**
     * Next Round Tester
     * This method verifies that the next round is properly initialized
     */
    @Test
    public void nextRound() {
        int round = roundTrack.getRound();
        Player player = roundTrack.getPlayers().get(0);
        boolean bound = roundTrack.getBound();
        try {
            roundTrack.nextRound();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(roundTrack.getRound(),round+1);
        Assert.assertEquals(roundTrack.getBound(),!bound);
        Assert.assertEquals(roundTrack.getNextPlayerIndex(),0);
        Assert.assertEquals(roundTrack.getPlayerTurn(), 1);
        Assert.assertNotEquals(player, roundTrack.getPlayers().get(0));
    }

    /**
     * Die Remover Tester
     * Tis method checks the correct removal of the die
     */
    @Test
    public void removeDie() {
        Assert.assertNull(roundTrack.removeDie(new Die("Yellow",4)));
        Assert.assertNotNull(roundTrack.removeDie(new Die("Blue", 3)));
    }

    /**
     * Die Remover Tester
     * Tis method checks the correct addition of the die
     */
    @Test
    public void addDie() {
        int a = roundTrack.getRemainingDice().size();
        roundTrack.addDie(new Die("Green",6));
        Assert.assertEquals(roundTrack.getRemainingDice().size(), a+1);
    }

    /**
     * RoundTrack Die Presence Checker
     * This method checks the proper functioning of the check of the RoundTrack
     */
    @Test
    public void isRoundEmptyOfDice(){
        Assert.assertFalse(roundTrack.isRoundEmptyOfDice());
        roundTrack.getRemainingDice().clear();
        Assert.assertTrue(roundTrack.isRoundEmptyOfDice());
    }

    /**
     * Bound Setter Tester
     * This method verifies if is correctly set the RoundTrack bound
     */
    @Test
    public void setBound() {
        Assert.assertTrue(roundTrack.getBound());
        roundTrack.setBound();
        Assert.assertFalse(roundTrack.getBound());
    }

    /**
     * Current Player Getter Tester
     * This method verifies if is returned the correct current player
     */
    @Test
    public void getPlayerOfCurrentTurn() {
        Assert.assertNotNull(roundTrack.getPlayerOfCurrentTurn());
    }
}