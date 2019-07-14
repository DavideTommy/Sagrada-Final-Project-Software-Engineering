package it.polimi.se2018.model.score;
import it.polimi.se2018.model.BoxOfSchemeCards;
import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.Grid;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static it.polimi.se2018.model.score.DifferentShades.PUBLIC_VICTORY_POINTS;

/**
 * Test class of DifferentShades
 * Test the efficiency of DifferentShades points counter
 * @author Federico Lichinchi
 */
public class TestDifferentShades {

    private Grid grid;
    private DifferentShades differentShades;
    private BoxOfSchemeCards box=BoxOfSchemeCards.newBox();


    /**
     * SetUp Method
     * This method prepare the new test ambient
     */
    @Before
    public void setUp() {
        this.differentShades = DifferentShades.newDifferentShades();
        this.grid = new Grid(box.request("Virtus"));
        grid.addDie(new Die("Green", 5),0,0);
        grid.addDie(new Die("Blue", 3),1,1);
        grid.addDie(new Die("Red", 4),1,0);
        grid.addDie(new Die("Yellow", 2),0,1);
        grid.addDie(new Die("Purple", 1),1,2);

        grid.addDie(new Die("Yellow", 1),2,3);
        grid.addDie(new Die("Blue", 2),2,2);
        grid.addDie(new Die("Red", 2),0,2);
        grid.addDie(new Die("Green", 3),3,1);
        grid.addDie(new Die("Purple", 4),2,0);


        grid.addDie(new Die("Yellow", 4),2,1);
        grid.addDie(new Die("Yellow", 3),3,0);
        grid.addDie(new Die("Blue", 6),3,2);
        grid.addDie(new Die("Blue", 6),3,4);
        grid.addDie(new Die("Blue", 5),2,4);

    }

    /**
     * TearDown Method
     * This method sets up the class to a clear and new test ambient
     */
    @After
    public void tearDown() {
    }

    /**
     * MinSet Tester
     * This method checks if 'minSet' method of 'DifferentShades' class returns the minimum value in int array that has
     * been passed as parameter.
     */
    @Test
    public void minSet() {
        int[] num = { 4, 6, 3, 1, 10 };
        Assert.assertEquals(1, differentShades.minSet(num));
    }

    /**
     * Public Score Tester
     * This method checks if 'publicScore' method of 'DifferentShades' class returns the right score number (1pt for
     * each number sets everywhere in the 'SchemeCard' of 'Grid').
     */
    @Test
    public void publicScore() {
        Assert.assertEquals(differentShades.publicScore(grid), 2 * PUBLIC_VICTORY_POINTS);
    }

    /**
     * Card Number Getter Tester
     * This test verifies the correct return of the PublicObjectiveCard number
     */

    @Test
    public void testGetCardNumber() {
        Assert.assertEquals(8, differentShades.getCardNumber());
        Assert.assertNotEquals(2, differentShades.getCardNumber());
    }

}