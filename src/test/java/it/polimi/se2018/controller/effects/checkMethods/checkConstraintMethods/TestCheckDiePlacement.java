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
 * This class tests if a die has been correctly placed
 * @author Federico Lichinchi
 * @author Davide Lorenzi (JDocs)
 */
public class TestCheckDiePlacement {
    private CheckDiePlacement checkDiePlacement = new CheckDiePlacement();
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
     * This method checks if a die has been correctly placed
     */
    @Test
    public void checkConstraint() {
        if(player.getGrid().isGridEmpty())
            player.getGrid().setFirstTimeToTrue();
        Die die = new Die("Green",4);
        player.getGrid().addDie(die,0,4);
        Die die2 = new Die("Blue",2);
        Assert.assertFalse(checkDiePlacement.checkConstraint(1,3,
                die2,player));
        Assert.assertTrue(checkDiePlacement.checkConstraint(1,4,
                die2,player));
        Assert.assertFalse(checkDiePlacement.checkConstraint(1,0,
                die2,player));

    }
}