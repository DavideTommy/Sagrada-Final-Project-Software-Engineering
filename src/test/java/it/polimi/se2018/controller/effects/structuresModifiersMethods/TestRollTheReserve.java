package it.polimi.se2018.controller.effects.structuresModifiersMethods;

import it.polimi.se2018.model.*;
import it.polimi.se2018.model.cards.CardParser;
import it.polimi.se2018.model.mv.Model;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;


/**
 * RollTheReserve class tester
 * @author Federico Lichinchi
 */
public class TestRollTheReserve {

    private RollTheReserve rollTheReserve;
    private Model model;

    /**
     * SetUp
     * This method prepare the test ambient
     * @throws FileNotFoundException If file is not found in resources
     * @throws CloneNotSupportedException from Cloneable interface
     */
    @Before
    public void setUp() throws CloneNotSupportedException, FileNotFoundException {
        rollTheReserve = new RollTheReserve();
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
    public void tearDown() {
        rollTheReserve = null;
        model = null;
    }

    /**
     * This method checks, after 'rollTheReserve' method call of RollTheReserve class, if all the new dice in reserve
     * are different from the old ones.
     */
    @Test
    public void rollTheReserve() {
        ArrayList<Die> dice = (ArrayList<Die>) model.getMatch().getReserve().getDie();
        rollTheReserve.rollTheReserve(model);
        ArrayList<Die> dice2 = (ArrayList<Die>) model.getMatch().getReserve().getDie();
        int i = 0;
        for(Die die: dice) {
            Assert.assertNotEquals(die, dice2.get(i));
            i++;
        }
    }
}