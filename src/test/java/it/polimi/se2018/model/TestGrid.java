package it.polimi.se2018.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Grid Tester
 * This class test the correct functioning of the rgid
 * @author Luca Massini
 */
public class TestGrid {
    private Grid grid;
    BoxOfSchemeCards box = BoxOfSchemeCards.newBox();

    /**
     * SetUp Method
     * This method initializes the test class
     */
    @Before
    public void setUp(){
        grid = new Grid(box.request("Virtus"));
    }

    /**
     * TearDown Method
     * This method prepare a new clear test ambient
     */
    @After
    public void tearDown(){
        grid = null;
    }

    /**
     * AddDice Tester
     * This test checks if the addDie works properly checking the equality of the Die that is added on position
     * x,y by the method addDie and the Die that is returned by the method getDie in the same position.
     */
    @Test
    public void addDiceGetDiceTest() {
        int i,j;
        String[] colors = {"Red", "Blue", "Yellow", "Purple", "Green"};
        int[] numbers ={1,2,3,4,5,6};
        boolean testPassed=true;
        for (i = 0; i < 5; i++) {
            for (j = 0; j < 6; j++) {
                Die d = new Die(colors[i], numbers[j]);
                grid.addDie(d, 2, 3);
                if(grid.getDie(2, 3) == null)
                    testPassed=false;
            }
        }
        assert(testPassed);
    }



    /**
     * GetDie Tester
     * This test that the getDice methods works properly
     */
    @Test
    public void CorrectGetDice() {
        Grid gridValue = new Grid(box.request("Virtus"));
        boolean testPassed = true;
        Die die = new Die("Green",5);
        gridValue.addDie(die,2,2);
        gridValue.addDie(die,1,2);
        gridValue.addDie(die,1,3);
        gridValue.addDie(die,2,3);
        gridValue.addDie(die,3,3);
        gridValue.addDie(die,3,2);
        gridValue.addDie(die,3,1);
        gridValue.addDie(die,2,1);
        gridValue.addDie(die,1,1);
        if(gridValue.getDieAbove(2,2)==null || gridValue.getDieBelow(2,2)==null || gridValue.getLeftDie(2,2)==null || gridValue.getRightDie(2,2)==null || gridValue.dieOnTheLeftAboveDiagonally(2,2)==null || gridValue.getDieOnTheLeftBelowDiagonally(2,2)==null || gridValue.getDieOnTheRightAboveDiagonally(2,2)==null || gridValue.getDieOnTheRightBelowDiagonally(2,2)==null)
            testPassed=false;
        assertTrue(testPassed);
    }

    /**
     * CompleteLineGetter Tester
     * This method checks that the getRow method is correct and works properly
     */
    @Test
    public void testGetCompleteLine(){
        Grid grid = new Grid(box.request("Virtus"));
        Die green = new Die("Green",5);
        Die[] tmp;
        Die[] array={green,green,green,green,green};
        grid.addDie(green,0,0);
        grid.addDie(green,0,1);
        grid.addDie(green,0,2);
        grid.addDie(green,0,3);
        grid.addDie(green,0,4);
        tmp = grid.getRow(0);
        assertTrue(Arrays.deepEquals(tmp,array));
    }

    /**
     * NotCompleteLine Tester
     * This methods checks that the getRow method works properly when in a line there isn't a die
     */
    @Test
    public void testGetNotCompleteLine(){
        Grid grid = new Grid(box.request("Virtus"));
        Die green = new Die("Green",5);
        Die[] tmp;
        Die[] array = {null, green, green, green, green};
        grid.addDie(green,0,1);
        grid.addDie(green,0,2);
        grid.addDie(green,0,3);
        grid.addDie(green,0,4);
        tmp=grid.getRow(0);
        assertTrue(Arrays.deepEquals(tmp,array));
    }

    /**
     * NotCompleteColumn Tester
     * This method checks that the getColumn method work properly
     */
    @Test
    public void testGetCompleteColumn(){
        Grid grid = new Grid(box.request("Virtus"));
        Die green = new Die("Green",5);
        Die[] tmp;
        Die[] array = {green,green,green,green};
        grid.addDie(green,0,0);
        grid.addDie(green,1,0);
        grid.addDie(green,2,0);
        grid.addDie(green,3,0);
        tmp = grid.getColumn(0);
        assertTrue(Arrays.deepEquals(tmp,array));
    }

    /**
     * NotCompleteColumn Tester
     * This method checks that the getColumn method works properly in case that there isn't a die in that column
     */
    @Test
    public void testGetNotCompleteColumn(){
        Grid grid = new Grid(box.request("Virtus"));
        Die green = new Die("Green",5);
        Die[] tmp;
        Die[] array={null,green,green,green};
        grid.addDie(green,1,0);
        grid.addDie(green,2,0);
        grid.addDie(green,3,0);
        tmp=grid.getColumn(0);
        assertTrue(Arrays.deepEquals(tmp,array));
    }

    /**
     * Rules Tester
     * This test checks that the method that checks the rules excluding that of the tool card works properly
     */
    @Test
    public void testRules(){
        Grid testGrid = new Grid(box.request("Virtus"));
        assertFalse(grid.checkRules(2,2,new Die("Green",4)));
        assertFalse(grid.checkRules(0,0,new Die("Green",3)));
        assertTrue(grid.checkRules(1,0,new Die("Green",4)));

        Die d = new Die("Green",4);
        if(testGrid.checkRules(0,0,d))
            testGrid.addDie(d,0,0);
        assertEquals(testGrid.getDie(0, 0), d);

        Die d2 = new Die("Green",5);
        if(testGrid.checkRules(0,4,d2))
            testGrid.addDie(d2,0,4);
        assertNull(testGrid.getDie(0, 4));

        Die d1 = new Die("Yellow",4);
        if(testGrid.checkRules(0,4,d1))
            testGrid.addDie(d1,0,4);
        assertNull(testGrid.getDie(0, 4));

        Die d3 = new Die("Yellow",3);
        if(testGrid.checkRules(1,0,d3))
            testGrid.addDie(d3,1,0);
        assertEquals(testGrid.getDie(1, 0), d3);
    }


}
