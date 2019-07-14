package it.polimi.se2018.model.score;

import it.polimi.se2018.model.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * TestDifferentShadeColumn Class
 * Test the efficiency of TestDifferentShadeColumn points counter
 * @author Luca Massini
 * @author Davide Lorenzi (JDocs)
 */
public class TestDifferentShadeColumn {
    private Grid testGrid1;
    private  Grid testGrid2;
    private DifferentShadeColumn test;
    private BoxOfSchemeCards box = BoxOfSchemeCards.newBox();

    /**
     * SetUp Method
     * This method prepare the new test ambient
     */
    @Before
    public void setUp() {
        testGrid1 = new Grid(box.request("Virtus"));
        test = DifferentShadeColumn.newDifferentShadeColumn();
        testGrid2 = new Grid(box.request("Virtus"));
    }

    /**
     * TearDown Method
     * This method sets up the class to a clear and new test ambient
     * @throws Exception if is not possible to clear the test ambient
     */
    @After
    public void tearDown() throws Exception{
        testGrid1 = null;
        test = null;
        testGrid2 = null;

    }

    /**
     * DifferentShadeColumn Tester
     * this test checks if the calculation of the score is correct filling a column of a grid with Die
     * with different numbers starting with the first to the last. For each filling of the column it assert
     * that the expected score is equal to the effective score calculating by the publicScore method
     */
    @Test
    public void testDifferentShadeColumn() {
        int i;
        int iterationScore;
        TestDifferentColorColumn tmp= new TestDifferentColorColumn();
        for (i = 0; i < 5; i++) {
            tmp.fillAColumn(i,testGrid1);
            iterationScore = test.publicScore(testGrid1);
            assertTrue(iterationScore == 4*(i+1));
        }
    }

    /**
     * DifferentShadeColumnSame Tester
     * Before the start of this test the grid that is the used is empty Die.
     * this test checks if the calculation of the score is correct filling
     * a column of a grid with Die with different numbers starting with
     * the first to the last. For each filling of the column it assert that the expected
     * score is equal to the effective score calculating by the publicScore method
     */
    @Test
    public void testDifferentShadeColumnSame(){
        TestDifferentColorColumn tmp= new TestDifferentColorColumn();
        int i,iterationScore;
        tmp.fillAllSameColors(testGrid2);
        assertTrue(test.publicScore(testGrid2)==0);
        for (i = 0; i < 5; i++) {
            tmp.fillAColumn(i,testGrid1);
            iterationScore = test.publicScore(testGrid1);
            assertTrue(iterationScore == 4*(i+1));
        }
    }

    /**
     * GetCardNumber Tester
     * This method tests that is returned the correct card number
     */
    @Test
    public void testGetCardNumber() {
        Assert.assertEquals(4, test.getCardNumber());
        Assert.assertNotEquals(2, test.getCardNumber());
    }

}