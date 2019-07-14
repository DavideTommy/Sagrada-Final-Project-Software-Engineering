package it.polimi.se2018.controller.effects.checkMethods;

import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.Grid;
import it.polimi.se2018.model.Player;
import it.polimi.se2018.model.SchemeCard;
import it.polimi.se2018.model.cards.CardParser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;

/**
 * CheckGridPicker class tester
 * @author Federico Lichinchi
 */
public class TestCheckGridPicker {

    private CheckGridPicker checkGridPicker;
    private Player player;

    /**
     * SetUp
     * This method prepare the test ambient
     * @throws FileNotFoundException If file is not found in resources
     * @throws CloneNotSupportedException from Cloneable interface
     */
    @Before
    public void setUp() throws FileNotFoundException, CloneNotSupportedException {
        checkGridPicker = new CheckGridPicker();
        player = new Player("aosdkfpas","adsadf","Green",
                new Grid(new SchemeCard("Virtus")), CardParser.newCardParser().getRandomPrivateObjectiveCard());
        player.getGrid().addDie(new Die("Red",3),0,0);
    }

    /**
     * Tear Down
     * This method prepare the test ambient for a new test
     */
    @After
    public void tearDown() {
        checkGridPicker = null;
        player = null;
    }

    /**
     * The coordinates of the position of the die to check in the grid has passed by parameters.
     * This method checks if method returns 'true' when it founds a die in the coordinates of the grid,
     * 'false' otherwise.
     */
    @Test
    public void isPossibleToPick() {
        Assert.assertTrue(checkGridPicker.isPossibleToPick(0,0,player));
        Assert.assertFalse(checkGridPicker.isPossibleToPick(1,1,player));
    }
}