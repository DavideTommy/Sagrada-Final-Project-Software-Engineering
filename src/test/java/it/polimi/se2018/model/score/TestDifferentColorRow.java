package it.polimi.se2018.model.score;


import it.polimi.se2018.model.BoxOfSchemeCards;
import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.Grid;
import org.junit.After;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


/**
 * TestDifferentColorRow Class
 * Test the efficiency of TestDifferentColorRow points counter
 * @author Luca Massini
 * @author Davide Lorenzi (JDocs)
 */
public class TestDifferentColorRow {
    private Grid testGrid;
    private Grid testGrid2;
    private DifferentColorRow test;
    private Die redDie = new Die("Red",1);
    private Die greenDie = new Die("Green",2);
    private Die yellowDie = new Die("Yellow",3);
    private Die blueDie = new Die("Blue",4);
    private Die purpleDie = new Die("Purple",5);
    private BoxOfSchemeCards box = BoxOfSchemeCards.newBox();

    /**
     * SetUp Method
     * This method prepare the new test ambient
     */
    @Before
    public void setUp() {
        testGrid2 = new Grid(box.request("Virtus"));
        test = DifferentColorRow.newDifferentColorRow();
        testGrid = new Grid(box.request("Virtus"));

    }

    /**
     * TearDown Method
     * This method sets up the class to a clear and new test ambient
     */
    @After
    public void tearDown() {
        testGrid = null;
        testGrid2 = null;
    }

    /**
     * Row Filler Tester
     * This method fills a line using Die
     * @param lineIndex is the line of the grid (param grid) to be filled by Die with different colors
     * @param grid is the grid to be considered to fill the its line.
     */
    public void fillALine(int lineIndex,Grid grid){
            grid.addDie(greenDie, lineIndex, 0);
            grid.addDie(yellowDie, lineIndex, 1);
            grid.addDie(purpleDie, lineIndex, 2);
            grid.addDie(blueDie, lineIndex, 3);
            grid.addDie(redDie,lineIndex,4);
    }

    /**
     * DifferentColorRow Tester
     * this test checks if the calculation of the score is correct filling a row of a grid with Die
     * with different colors starting with the first to the last. For each filling of the row it assert
     * that the expected score is equal to the effective score calculating by the publicScore method
     */
    @Test
    public void testDifferentColorRow(){
        int index;
        int score;
        for(index = 0;index < 4;index++){
            fillALine(index,testGrid);
            score = test.publicScore(testGrid);
            assert(score == 6*(index+1));
        }
    }

    /**
     * DifferentColorRowSame Tester
     * Before the start of this test the grid that is the used is empty Die.
     * this test checks if the calculation of the score is correct filling
     * a row of a grid with Die with different colors starting with
     * the first to the last. For each filling of the row it assert that the expected
     * score is equal to the effective score calculating by the publicScore method
     */
    @Test
    public void testDifferentColorRowSame(){
        TestDifferentColorColumn tmp = new TestDifferentColorColumn();
        tmp.fillAllSameColors(testGrid2);
        int index;
        int score;
        assertEquals(0, test.publicScore(testGrid2));
        for(index = 0;index < 4;index++){
            fillALine(index,testGrid);
            score=test.publicScore(testGrid);
            assert(score == 6*(index+1));
        }
    }


    /**
     * GetCardNumber Tester
     * This method tests that is returned the correct card number
     */
    @Test
    public void testGetCardNumber() {
        Assert.assertEquals(1, test.getCardNumber());
        Assert.assertNotEquals(2, test.getCardNumber());
    }
}
