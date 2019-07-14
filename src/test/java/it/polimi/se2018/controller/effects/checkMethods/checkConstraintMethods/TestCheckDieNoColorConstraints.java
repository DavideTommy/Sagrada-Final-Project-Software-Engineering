package it.polimi.se2018.controller.effects.checkMethods.checkConstraintMethods;

import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.Grid;
import it.polimi.se2018.model.Player;
import it.polimi.se2018.model.SchemeCard;
import it.polimi.se2018.model.cards.CardParser;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;

/**
 * CheckDiePlacement Tester
 * This class tests if a die has no color
 * @author Federico Lichinchi
 * @author Davide Lorenzi (JDocs)
 */
public class TestCheckDieNoColorConstraints {

    private CheckDieNoColorConstraints checkDieNoColorConstraints = new CheckDieNoColorConstraints();
    private Player player;

    {
        try {
            player = new Player("asadfasdf", "asdfasdf", "Green",
                        new Grid(new SchemeCard("Virtus")), CardParser.newCardParser().getRandomPrivateObjectiveCard());
        } catch (CloneNotSupportedException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * NoColorConstraints Test
     * This method checks if the die has no color
     */
    @Test
    public void checkConstraint() {
        if(player.getGrid().isGridEmpty())
            player.getGrid().setFirstTimeToTrue();
        Die die = new Die("Green",4);
        player.getGrid().addDie(die,0,4);
        player.getGrid().removeDie(0,4);
        if(player.getGrid().isGridEmpty())
            player.getGrid().setFirstTimeToTrue();
        Assert.assertFalse(checkDieNoColorConstraints.checkConstraint(2,2,
                die,player));
        Assert.assertTrue(checkDieNoColorConstraints.checkConstraint(0,0,
                die,player));
    }
}