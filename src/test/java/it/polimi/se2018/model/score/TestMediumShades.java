package it.polimi.se2018.model.score;
import it.polimi.se2018.model.BoxOfSchemeCards;
import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.Grid;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static it.polimi.se2018.model.score.MediumShades.PUBLIC_VICTORY_POINTS;

/**
 * MediumShades Set Tester
 * Test the efficiency of MediumShades points counter
 * @author Davide Lorenzi
 */

public class TestMediumShades {

    private Grid grid;
    private MediumShades mediumShades;
    private int publicScore;
    private BoxOfSchemeCards box=BoxOfSchemeCards.newBox();

    /**
     * SetUp Method
     * This method prepare the new test ambient
     */
    @Before
    public void setUp() {

        this.mediumShades = MediumShades.newMediumShades();
        this.grid = new Grid(box.request("Virtus"));
        publicScore = 0;
        grid.addDie(new Die("Green", 4),0,0);
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
        publicScore = mediumShades.publicScore(grid);
    }

    /**
     * TearDown Method
     * This method sets up the class to a clear and new test ambient
     */
    @After
    public void tearDown() {
        mediumShades.numberOfThree = 0;
        mediumShades.numberOfFour = 0;
    }

    /**
     * PublicScore Tester
     * This method checks if 'publicScore' method of 'MediumShades' class returns the right score number (1pt for
     * each 3 and 4 sets everywhere in the 'SchemeCard' of 'Grid').
     */
    @Test
    public void publicScore() {
        Assert.assertEquals(publicScore, 3 * PUBLIC_VICTORY_POINTS);
        Assert.assertNotEquals(publicScore, 4 * PUBLIC_VICTORY_POINTS);
    }
    /**
     * MinSet Tester
     * This method checks if 'minSet' method of 'DeepShades' class returns the minimum value between the number of Onne
     * and Two occurrences.
     */

    @Test
    public void minSet() {
        Assert.assertEquals(3, mediumShades.minSet(mediumShades.number1, mediumShades.number2));
        Assert.assertNotEquals(4, mediumShades.minSet(mediumShades.number1, mediumShades.number2));
    }

    /**
     * Card Number Getter Tester
     * This test verifies the correct return of the PublicObjectiveCard number
     */

    @Test
    public void testGetCardNumber() {
        Assert.assertEquals(6, mediumShades.getCardNumber());
        Assert.assertNotEquals(2, mediumShades.getCardNumber());
    }
}