package it.polimi.se2018.model.score;

import it.polimi.se2018.model.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import java.util.Random;

import static org.junit.Assert.*;

/**
 * TestDifferentColorColumn Class
 * Test the efficiency of TestDifferentColorColumn points counter
 * @author Luca Massini
 * @author Davide Lorenzi (JDocs)
 */
public class TestDifferentColorColumn {
    private Grid testGrid1;
    private Grid testGrid2;
    private Grid testGrid3;
    private Die redDie = new Die("Red",1);
    private Die greenDie = new Die("Green",2);
    private Die yellowDie  =new Die("Yellow",3);
    private Die blueDie = new Die("Blue",4);
    private Die purpleDie = new Die("Purple",5);
    private DifferentColorColumn test;
    private BoxOfSchemeCards box = BoxOfSchemeCards.newBox();

    /**
     * SetUp Method
     * This method prepare the new test ambient
     */
    @Before
    public void setUp(){
        testGrid1 = new Grid(box.request("Virtus"));
        test = DifferentColorColumn.newDifferentColorColumn();
        testGrid2 = new Grid(box.request("Virtus"));
        testGrid3 = new Grid(box.request("Virtus"));
    }

    /**
     * TearDown Method
     * This method sets up the class to a clear and new test ambient
     */
    @After
    public void tearDown() {
        testGrid2 = null;
        testGrid3 = null;
        testGrid1 = null;
        test = null;
    }


    /**
     * ColumnFiller Tester
     * This method fills a column with Die
     * @param columnIndex is the index in the grid of the column that must be filled with different Die (different colors)
     * @param grid is the grid to be considered
     */
    public void fillAColumn(int columnIndex,Grid grid){
        int random=new Random().nextInt(1);
        if(random==1) {
            grid.addDie(greenDie, 0, columnIndex);
            grid.addDie(yellowDie, 1, columnIndex);
            grid.addDie(purpleDie, 2, columnIndex);
            grid.addDie(blueDie, 3, columnIndex);
        }
        grid.addDie(redDie, 0, columnIndex);
        grid.addDie(yellowDie, 1, columnIndex);
        grid.addDie(purpleDie, 2, columnIndex);
        grid.addDie(blueDie, 3, columnIndex);
    }

    /**
     * DifferentColorColumn Tester
     * Before the start of this test the grid that is the used is empty Die
     * this test checks if the calculation of the score is correct filling a column of a grid with Die with different color
     * starting with the first to the last. For each filling of the column it assert that the expected
     * score is equal to the effective score calculating by the publicScore method
     */
    @Test
    public void testDifferentColorColumn() {
        int i;
        int iterationScore;
        for (i = 0; i < 5; i++) {
            fillAColumn(i,testGrid1);
            iterationScore = test.publicScore(testGrid1);
            assertEquals(iterationScore, 5 * (i + 1));
        }
    }

    /**
     * SameColor Filler Tester
     * This method fills a column using Die of the same color
     * @param grid is the grid to be filled with Die colored by Green
     */
    public void fillAllSameColors(Grid grid){
        int lineIndex;
        int columnIndex;
        Die die = new Die("Green",1);
        for(lineIndex = 0;lineIndex < 4;lineIndex++){
            for(columnIndex = 0;columnIndex < 5;columnIndex++){
                grid.addDie(die, lineIndex, columnIndex);
            }
        }
    }


    /**
     * DifferentRandomColor Tester
     * In this test is checked the calculation of the score in case that the grid ,after the start of this method
     * is filled by Die of the same color. A column of the grid is filled every time with Die that have different
     * colors.And every time that the grid is filled this method asserts if the expected score is equal to
     * the effective score calculated by the publicScore method
     */
    @Test
    public void testDifferentColorColumnRandom(){
        fillAllSameColors(testGrid2);
        int i;
        int iterationScore;
        for(i = 0;i < 5;i++){
            fillAColumn(i,testGrid2);
            iterationScore = test.publicScore(testGrid2);
            assertEquals(iterationScore, (5 * (i + 1)));
        }
    }


    /**
     * DifferentSameColor Tester
     * This test method checks if a the score calculated by the publicScore method is zero when
     * the grid is filled with Die that have the same color.
     */
    @Test
    public void testDifferentColorColumnSame(){
        fillAllSameColors(testGrid3);
        int score = test.publicScore(testGrid3);
        assertEquals(0, score);
    }

    /**
     * GetCardNumber Tester
     * This method tests that is returned the correct card number
     */
    @Test
    public void testGetCardNumber() {
        Assert.assertEquals(2, test.getCardNumber());
        Assert.assertNotEquals(3, test.getCardNumber());
    }
}
