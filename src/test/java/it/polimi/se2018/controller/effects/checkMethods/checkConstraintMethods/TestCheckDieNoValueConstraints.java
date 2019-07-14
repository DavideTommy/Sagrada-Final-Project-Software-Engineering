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
 * CheckDieNoValue Tester
 * This class tests if a die has no value
 * @author Federico Lichinchi
 * @author Davide Lorenzi (JDocs)
 */
public class TestCheckDieNoValueConstraints {

    private CheckDieNoValueConstraints checkDieNoValueConstraints = new CheckDieNoValueConstraints();
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
     * ConstraintChecker Test
     * This method checks if a die has no value
     */
    @Test
    public void checkConstraint() {
        if(player.getGrid().isGridEmpty())
            player.getGrid().setFirstTimeToTrue();
        Die die = new Die("Blue",4);
        player.getGrid().addDie(die,0,3);
        player.getGrid().removeDie(0,3);
        if(player.getGrid().isGridEmpty())
            player.getGrid().setFirstTimeToTrue();
        Assert.assertTrue(checkDieNoValueConstraints.checkConstraint(1,0,
                die,player));
        Assert.assertFalse(checkDieNoValueConstraints.checkConstraint(3,1,
                die,player));

    }

}