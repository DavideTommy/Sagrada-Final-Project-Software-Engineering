package it.polimi.se2018.model.score;
import it.polimi.se2018.model.BoxOfSchemeCards;
import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.Grid;
import org.junit.Assert;
import org.junit.Test;

/**
 * TestColoredDiagonals Class
 * Test the efficiency of TestColoredDiagonals points counter
 * @author Federico Lichinchi
 * @author Davide Lorenzi (JDocs)
*/
public class TestColoredDiagonals {
    private BoxOfSchemeCards box = BoxOfSchemeCards.newBox();

    /**
     * Test of PublicScore Method
     * This method checks if object 'ColoredDiagonals' return the right score after the call of 'publicScore' method(
     * 1 pt for every die that has an adjacent die with the same colour).
     * @throws CloneNotSupportedException From Cloneable interface
     */
    @Test
    public void publicScore() throws CloneNotSupportedException {
        Grid grid = new Grid(box.request("Virtus"));
        ColoredDiagonals coloredDiagonals = ColoredDiagonals.newColoredDiagonals();
        grid.addDie(new Die("Green", 5),0,0);
        grid.addDie(new Die("Blue", 3),1,1);
        grid.addDie(new Die("Red", 4),1,0);
        grid.addDie(new Die("Yellow", 2),0,1);
        grid.addDie(new Die("Purple", 1),1,2);

        grid.addDie(new Die("Red", 1),2,3);
        grid.addDie(new Die("Blue", 2),2,2);
        grid.addDie(new Die("Red", 2),0,2);
        grid.addDie(new Die("Green", 3),3,1);
        grid.addDie(new Die("Purple", 6),2,0);

        grid.addDie(new Die("Yellow", 4),2,1);
        grid.addDie(new Die("Yellow", 3),3,0);
        grid.addDie(new Die("Blue", 6),3,2);
        grid.addDie(new Die("Blue", 6),3,4);
        grid.addDie(new Die("Green", 5),2,4);
        int n = coloredDiagonals.publicScore(grid);
        Assert.assertEquals(4, n);
    }

    /**
     * SameColor Tester
     * This method checks if 'checkForSameColor(g,x,y)' return 1 if the die in the x row and the y column has an
     * adjacent die with the same color, otherwise 0.
     */
    @Test
    public void calcCheckForSameColor() {
        ColoredDiagonals coloredDiagonals = ColoredDiagonals.newColoredDiagonals();
        Grid grid = new Grid(box.request("Virtus"));
        grid.addDie(new Die("Green", 5),0,4);
        grid.addDie(new Die("Green", 3),1,3);
        grid.addDie(new Die("Red", 4),0,3);
        grid.addDie(new Die("Yellow", 2),1,4);
        grid.addDie(new Die("Yellow", 5),2,3);
        grid.addDie(new Die("Yellow", 1),1,2);
        grid.addDie(new Die("Blue", 2),1,2);
        Assert.assertTrue(coloredDiagonals.checkForSameColor(grid, 1, 3));
        Assert.assertFalse(coloredDiagonals.checkForSameColor(grid, 0, 3));
    }

    /**
     * Card Number Getter Tester
     * This test verifies the correct return of the PublicObjectiveCard number
     */

    @Test
    public void testGetCardNumber() {
        ColoredDiagonals coloredDiagonals    = ColoredDiagonals.newColoredDiagonals();
        Assert.assertEquals(9, coloredDiagonals.getCardNumber());
        Assert.assertNotEquals(2, coloredDiagonals.getCardNumber());
    }
}