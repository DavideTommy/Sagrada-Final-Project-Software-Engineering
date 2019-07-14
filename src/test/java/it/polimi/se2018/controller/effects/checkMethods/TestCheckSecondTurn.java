package it.polimi.se2018.controller.effects.checkMethods;

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

/**
 * CheckSecondTurn class tester
 * @author Federico Lichinchi
 */
public class TestCheckSecondTurn {
    private CheckSecondTurn checkSecondTurn;
    private Model model;

    /**
     * SetUp
     * This method prepare the test ambient
     * @throws FileNotFoundException If file is not found in resources
     * @throws CloneNotSupportedException from Cloneable interface
     */
    @Before
    public void setUp() throws CloneNotSupportedException, FileNotFoundException {
        checkSecondTurn = new CheckSecondTurn();
        model = new Model(2);
        model.newMatch();
        model.getMatch().getRoundTrack().getPlayers().add(new Player("aosdkfpas","adsadf","Green",
                new Grid(new SchemeCard("Virtus")), CardParser.newCardParser().getRandomPrivateObjectiveCard()));
        model.getMatch().getRoundTrack().getPlayers().add(new Player("aosdkfasdfdaspas","adsadf","Purple",
                new Grid(new SchemeCard("Via Lux")), CardParser.newCardParser().getRandomPrivateObjectiveCard()));
    }

    /**
     * Tear Down
     * This method prepare the test ambient for a new test
     */
    @After
    public void tearDown(){
        checkSecondTurn = null;
        model = null;
    }

    /**
     * This method checks if 'isThisSecondTurn()' function of CheckSecondTurn class return a correct value (true of
     * we are at the second turn, false otherwise).
     */
    @Test
    public void isThisSecondTurn() {
        Assert.assertFalse(checkSecondTurn.isThisTheSecondTurn(model));
        model.getMatch().getRoundTrack().nextTurn();
        model.getMatch().getRoundTrack().nextTurn();
        Assert.assertTrue(checkSecondTurn.isThisTheSecondTurn(model));
    }

}