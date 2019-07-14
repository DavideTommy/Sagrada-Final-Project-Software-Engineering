package it.polimi.se2018.model;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * TestSchemeCard Tester
 * Test the efficiency of the application of TestSchemeCard
 * @author Luca Massini
 * @author Davide Lorenzi (JDocs)
 */
public class TestSchemeCard {

    private BoxOfSchemeCards box;

    /**
     * SetUp Method
     * This method prepare the test ambient
     */
    @Before
    public void setUp(){
        box = BoxOfSchemeCards.newBox();
    }


    /**
     * SchemeCardName Tester
     * This test verifies the correct request of the cardName
     */
    @Test
    public void schemeCardName(){
        SchemeCard card = box.request("Virtus");
        assertEquals("Virtus", card.getSchemeCardName());
    }

    /**
     * DifficultyValue Tester
     * This test verifies that the expected DifficultValue is the one of the SchemeCard
     */
    @Test
    public void schemeCardDifficultyValue(){
        SchemeCard card = box.request("Virtus");
        assertEquals(5,card.getDifficultValue());
    }

    /**
     * NumberZeroSetter
     * This method set to 0 all Die
     * @param matrixOfColors matrix of colors
     */
    private void setAllNumbersToZero(String[][] matrixOfColors){
        int line;
        int column;
        for(line = 0;line < 4;line++){
            for(column = 0;column < 5;column++){
                matrixOfColors[line][column] = null;
            }
        }
    }

    /**
     * MatrixOfColor Tester
     * This method tests if an expected MatrixOfColors is equal than the required one
     */
    @Test
    public void  matrixOfColor(){
        boolean testPassed = true;
        int i,j;
        String[][] matrixOfColors = new String[4][5];
        setAllNumbersToZero(matrixOfColors);
        matrixOfColors[0][4] = "Green";
        matrixOfColors[1][3] = "Green";
        matrixOfColors[2][2] = "Green";
        matrixOfColors[3][1] = "Green";
        SchemeCard card = box.request("Virtus");
        String [][] matrix = card.getMatrixOfColors();
        for(i = 0; i < 4; i++){
            for(j = 0; j < 5; j++){
                if (matrixOfColors[i][j] != null && matrix[i][j] != null) {
                    if (!(matrixOfColors[i][j].equals(matrix[i][j])))
                        testPassed = false;
                }
            }
        }
        assertTrue(testPassed);
    }

    /**
     * MatrixOfValue Tester
     * This method tests if an expected MatrixOfValue is equal than the required one
     */
    @Test
    public void testMatrixOfNumbers(){
        SchemeCard card = box.request("Virtus");
        int[][] matrix = card.getMatrixOfNumbers();
        int[][] matrix2 = new int[4][5];
        matrix2[0][0] = 4;
        matrix2[0][2] = 2;
        matrix2[0][3] = 5;
        matrix2[1][2] = 6;
        matrix2[1][4] = 2;
        matrix2[2][1] = 3;
        matrix2[2][3] = 4;
        matrix2[3][0] = 5;
        matrix2[3][2] = 1;
        assertTrue(Arrays.deepEquals(matrix,matrix2));
    }
}
