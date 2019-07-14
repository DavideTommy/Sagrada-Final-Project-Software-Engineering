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
 * CheckNotAdjacency Tester
 * This class tests the not adjacency checks works properly
 * @author Federico Lichinchi
 * @author Davide Lorenzi (JDocs)
 */
public class TestCheckNotAdjacent {

    private CheckNotAdjacent checkNotAdjacent = new CheckNotAdjacent();
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
     * Constraint Checker Tester
     * This test verifies if not adjacency check works properly
     */
    @Test
    public void checkConstraint() {
        if(player.getGrid().isGridEmpty())
            player.getGrid().setFirstTimeToTrue();
        Die die = new Die("Green",4);
        player.getGrid().addDie(die,3,1);
        Die die2 = new Die("Blue", 3);
        Assert.assertFalse(checkNotAdjacent.checkConstraint(2,2,
                die2,player));
        Assert.assertTrue(checkNotAdjacent.checkConstraint(0,0,
                die2,player));
    }

}