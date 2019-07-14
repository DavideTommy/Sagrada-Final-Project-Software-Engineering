package it.polimi.se2018.model.score;
import it.polimi.se2018.model.BoxOfSchemeCards;
import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.Grid;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * TestDifferentColors Class
 * Test the efficiency of TestDifferentColors points counter
 * @author Federico Lichinchi
 * @author Davide Lorenzi (JDocs)
 */
public class TestDifferentColors {

    private DifferentColors differentColors;
    private Grid grid;
    private BoxOfSchemeCards box= BoxOfSchemeCards.newBox();

    /**
     * SetUp Method
     * This method prepare the new test ambient
     */
    @Before
    public void setUp() {
        this.differentColors = DifferentColors.newDifferentColors();
        this.grid = new Grid(box.request("Virtus"));
        grid.addDie(new Die("Green", 5),0,0);
        grid.addDie(new Die("Blue", 3),1,1);
        grid.addDie(new Die("Red", 4),1,0);
        grid.addDie(new Die("Yellow", 2),0,1);
        grid.addDie(new Die("Purple", 5),1,2);

        grid.addDie(new Die("Yellow", 1),2,3);
        grid.addDie(new Die("Blue", 2),2,2);
        grid.addDie(new Die("Red", 1),0,2);
        grid.addDie(new Die("Green", 1),3,1);
        grid.addDie(new Die("Purple", 1),2,0);


        grid.addDie(new Die("Yellow", 1),2,1);
        grid.addDie(new Die("Yellow", 1),3,0);
        grid.addDie(new Die("Blue", 1),3,2);

    }

    /**
     * MinSet Tester
     * This method checks if 'minSet' method of 'DifferentColors' returns the minimum number of int array that has
     * been passed as parameter.
     */
    @Test
    public void minSet() {
        int[] num = {4, 6, 3, 1, 10 };
        Assert.assertEquals(1, differentColors.minSet(num));
    }

    /**
     * PublicScore Tester
     * This method checks if 'publicScore' method of 'DifferentColors' class returns the right score number (1pt for
     * each colour set everywhere in the 'SchemeCard' of the 'Grid').
     */
    @Test
    public void publicScore() {
        Assert.assertEquals(differentColors.publicScore(grid), 2 * 4);

    }

    /**
     * GetCardNumber Tester
     * This method tests that is returned the correct card number
     */
    @Test
    public void testGetCardNumber() {
        Assert.assertEquals(10, differentColors.getCardNumber());
        Assert.assertNotEquals(2, differentColors.getCardNumber());
    }
}