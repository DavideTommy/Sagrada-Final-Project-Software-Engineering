package it.polimi.se2018.model.score;

import it.polimi.se2018.model.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * TestDifferentShadeRow Class
 * Test the efficiency of TestDifferentShadeRow points counter
 * @author Luca Massini
 */
public class TestDifferentShadeRow {
    private Grid testGrid;
    private Grid testGrid2;
    private DifferentShadesRow test;
    private BoxOfSchemeCards box=BoxOfSchemeCards.newBox();


    /**
     * SetUp Method
     * This method prepare the new test ambient
     */
    @Before
    public void setUp() {
        testGrid2 = new Grid(box.request("Virtus"));
        test = DifferentShadesRow.newDifferentShadesRow();
        testGrid = new Grid(box.request("Virtus"));
    }

    /**
     * TearDown Method
     * This method sets up the class to a clear and new test ambient
     * @throws Exception if is not possible to clear the test ambient
     */
    @After
    public void tearDown() throws Exception{
        testGrid = null;
        testGrid2 = null;
        test = null;
    }

    /**
     * DifferentShadeRow Tester
     * This test checks if the calculation of the score is correct filling a row of a grid with Die
     * with different numbers starting with the first to the last. For each filling of the row it assert
     * that the expected score is equal to the effective score calculating by the publicScore method
     */
    @Test
    public void testDifferentShadeRow(){
        int index;
        int score;
        TestDifferentColorRow tmp= new TestDifferentColorRow();
        for(index = 0;index < 4; index++){
            tmp.fillALine(index,testGrid);
            score = test.publicScore(testGrid);
            assertTrue(score == 5*(index+1));
        }
    }


    /**
     * DifferentShadesRow Tester
     * Before the start of this test the grid that is the used is empty Die.
     * this test checks if the calculation of the score is correct filling
     * a row of a grid with Die with different numbers starting with
     * the first to the last. For each filling of the row it assert that the expected
     * score is equal to the effective score calculating by the publicScore method
     */
    @Test
    public void testDifferentShadeRowSame(){
        TestDifferentColorColumn tmp = new TestDifferentColorColumn();
        tmp.fillAllSameColors(testGrid2);
        TestDifferentColorRow tmp1 = new TestDifferentColorRow();
        int index;
        int score;
        assertEquals(0, test.publicScore(testGrid2));
        for(index=0; index<4; index++){
            tmp1.fillALine(index,testGrid);
            score=test.publicScore(testGrid);
            assertEquals(score, 5 * (index + 1));
        }
    }

    /**
     * GetCardNumber Tester
     * This method tests that is returned the correct card number
     */
    @Test
    public void testGetCardNumber() {
        Assert.assertEquals(3, test.getCardNumber());
        Assert.assertNotEquals(5, test.getCardNumber());
    }


}
