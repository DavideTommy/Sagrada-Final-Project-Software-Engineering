package it.polimi.se2018.controller.effects.structuresModifiersMethods;

import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.Grid;
import it.polimi.se2018.model.Player;
import it.polimi.se2018.model.SchemeCard;
import it.polimi.se2018.model.cards.CardParser;
import it.polimi.se2018.model.mv.Model;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;

/**
 * PutDieInRoundTrack class tester
 * @author Federico Lichinchi
 */
public class TestPutDieInRoundTrack {

    private PutDieInRoundTrack putDieInRoundTrack;
    private Model model;

    /**
     * SetUp
     * This method prepare the test ambient
     * @throws FileNotFoundException If file is not found in resources
     * @throws CloneNotSupportedException from Cloneable interface
     */
    @Before
    public void setUp() throws FileNotFoundException, CloneNotSupportedException {
        putDieInRoundTrack = new PutDieInRoundTrack();model = new Model(2);
        model.newMatch();
        model.getMatch().getRoundTrack().getPlayers().add(new Player("aosdkfpas","adsadf","Green",
                new Grid(new SchemeCard("Virtus")), CardParser.newCardParser().getRandomPrivateObjectiveCard()));
        model.getMatch().getRoundTrack().getPlayers().add(new Player("aosdkfasdfdaspas","adsadf","Purple",
                new Grid(new SchemeCard("Via Lux")), CardParser.newCardParser().getRandomPrivateObjectiveCard()));
        model.getMatch().getRoundTrack().getRemainingDice().add(model.getMatch().getBag().extractDie());
    }

    /**
     * Tear Down
     * This method prepare the test ambient for a new test
     */
    @After
    public void tearDown() {
        putDieInRoundTrack = null;
        model = null;
    }

    /**
     * This method checks if a die from the bag is really added in RoundTrack dice ArrayList.
     */
    @Test
    public void putInRoundTrack() {
        int number = model.getMatch().getRoundTrack().getRemainingDice().size();
        putDieInRoundTrack.putInRoundTrack(model.getMatch().getBag().extractDie(),model);
        Assert.assertTrue(model.getMatch().getRoundTrack().getRemainingDice().size() > number);
    }
}