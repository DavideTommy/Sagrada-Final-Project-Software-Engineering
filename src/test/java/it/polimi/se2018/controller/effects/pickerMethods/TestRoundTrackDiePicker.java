package it.polimi.se2018.controller.effects.pickerMethods;

import it.polimi.se2018.model.*;
import it.polimi.se2018.model.cards.CardParser;
import it.polimi.se2018.model.mv.Model;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;

/**
 * RoundTrackDiePicker class tester
 * @author Federico Lichinchi
 */
public class TestRoundTrackDiePicker {

    private RoundTrackDiePicker roundTrackDiePicker;
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
        roundTrackDiePicker = new RoundTrackDiePicker();
        model = new Model(2);
        model.newMatch();
        model.getMatch().getRoundTrack().getPlayers().add(new Player("aosdkfpas","adsadf","Green",
                new Grid(new SchemeCard("Virtus")), CardParser.newCardParser().getRandomPrivateObjectiveCard()));
        model.getMatch().getRoundTrack().getPlayers().add(new Player("aosdkfasdfdaspas","adsadf","Purple",
                new Grid(new SchemeCard("Via Lux")), CardParser.newCardParser().getRandomPrivateObjectiveCard()));
        model.getMatch().getRoundTrack().getRemainingDice().add(model.getMatch().getBag().extractDie());
        model.getMatch().getRoundTrack().getRemainingDice().add(model.getMatch().getBag().extractDie());
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
        roundTrackDiePicker = null;
        model = null;
        index = -1;
    }

    /**
     * This method checks if the die picked from the RoundTrack is not a null object. After the call of 'getPickedDie'
     * in RoundTrackDiePicker class the number of that die occurrences have to be decremented.
     */
    @Test
    public void getPickedDie() {
        Assert.assertTrue(index >= 0 && index < model.getMatch().getRoundTrack().getRemainingDice().size());
        Die die = model.getMatch().getRoundTrack().getRemainingDice().get(index);
        int occurrences = 0;
        for(Die dice : model.getMatch().getRoundTrack().getRemainingDice())
            if(dice.getDieColor().equals(die.getDieColor()) && dice.getDieNumber() == die.getDieNumber())
                occurrences++;
        Assert.assertNotNull(roundTrackDiePicker.getPickedDie(index,model));
        int occurrences2 = 0;
        for(Die dice : model.getMatch().getRoundTrack().getRemainingDice())
            if(dice.getDieColor().equals(die.getDieColor()) && dice.getDieNumber() == die.getDieNumber())
                occurrences2++;
        Assert.assertTrue(occurrences2 < occurrences);
    }
}