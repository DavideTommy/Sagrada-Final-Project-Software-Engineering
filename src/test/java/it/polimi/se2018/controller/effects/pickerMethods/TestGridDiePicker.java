package it.polimi.se2018.controller.effects.pickerMethods;

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
 * GridDiePicker class tester
 * @author Federico Lichinchi
 */
public class TestGridDiePicker {

    private GridDiePicker gridDiePicker;
    private Player player;

    /**
     * SetUp
     * This method prepare the test ambient
     * @throws FileNotFoundException If file is not found in resources
     * @throws CloneNotSupportedException from Cloneable interface
     */
    @Before
    public void setUp() throws FileNotFoundException, CloneNotSupportedException {
        gridDiePicker = new GridDiePicker();
        player = new Player("aosdkfpas","adsadf","Green",
                new Grid(new SchemeCard("Virtus")), CardParser.newCardParser().getRandomPrivateObjectiveCard());
        player.getGrid().addDie(new Die("Yellow", 3),1,1);
    }

    /**
     * Tear Down
     * This method prepare the test ambient for a new test
     */
    @After
    public void tearDown() {
    }

    /**
     * This method checks if a die is really picked from the grid of the player and if it isn't in a cell of the grid
     * anymore.
     */
    @Test
    public void getPickedDie() {
        Assert.assertNotNull(gridDiePicker.getPickedDie(1,1,player));
        Assert.assertNull(player.getGrid().getDie(1,1));
    }
}