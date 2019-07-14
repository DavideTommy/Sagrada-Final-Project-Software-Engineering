package it.polimi.se2018.controller.effects.checkMethods;

import it.polimi.se2018.controller.effects.pickerMethods.RoundTrackDiePicker;
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
 * CheckRoundTrackPicker class tester
 * @author Federico Lichinchi
 */
public class TestCheckRoundTrackPicker {

    private CheckRoundTrackPicker checkRoundTrackPicker;
    private Model model;
    private int index;

    /**
     * SetUp
     * This method prepare the test ambient
     * @throws FileNotFoundException If file is not found in resources
     * @throws CloneNotSupportedException from Cloneable interface
     */
    @Before
    public void setUp() throws CloneNotSupportedException, FileNotFoundException {
        checkRoundTrackPicker = new CheckRoundTrackPicker();
        model = new Model(2);
        model.newMatch();
        model.getMatch().getRoundTrack().getPlayers().add(new Player("aosdkfpas","adsadf","Green",
                new Grid(new SchemeCard("Virtus")), CardParser.newCardParser().getRandomPrivateObjectiveCard()));
        model.getMatch().getRoundTrack().getPlayers().add(new Player("aosdkfasdfdaspas","adsadf","Purple",
                new Grid(new SchemeCard("Via Lux")), CardParser.newCardParser().getRandomPrivateObjectiveCard()));
        model.getMatch().getRoundTrack().getRemainingDice().add(model.getMatch().getBag().extractDie());
        model.getMatch().getRoundTrack().getRemainingDice().add(model.getMatch().getBag().extractDie());
        index = 1;
    }

    /**
     * Tear Down
     * This method prepare the test ambient for a new test
     */
    @After
    public void tearDown() {
        checkRoundTrackPicker = null;
        model = null;
        index = -1;
    }

    /**
     * This method checks if the die extracted from the round track dice ArrayList in the 'index' position, doesn't
     * assume a null value.
     */
    @Test
    public void isPossibleToPick() {
        Assert.assertTrue(index >= 0 && index < model.getMatch().getRoundTrack().getRemainingDice().size());
        Assert.assertTrue(checkRoundTrackPicker.isPossibleToPick(index,model));
        Assert.assertTrue(checkRoundTrackPicker.isPossibleToPick(index-1,model));
    }
}