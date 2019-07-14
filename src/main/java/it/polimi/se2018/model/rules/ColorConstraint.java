package it.polimi.se2018.model.rules;

import it.polimi.se2018.model.Die;

/**
 * ColorConstraint Class implements SchemeCardConstraint
 * This class is used to verify the correct color placement of a die
 *
 * @author Luca Massini (all the code)
 */
public class ColorConstraint implements SchemeCardConstraint {

    private String[][] matrixOfColors;

    /**
     * ColorConstraint Constructor
     * This method sets a new MatrixOfColors
     * @param matrixOfColors is the matrixOfColors representing the Color constraint
     *                       of the SchemeCard
     */
    public ColorConstraint(String[][] matrixOfColors){
        this.matrixOfColors = matrixOfColors;
    }


    /**
     * ValidConstraint Checker
     * This method provides to check that for every position in the matrixOfColors the die in its grid
     * position (row, column) has a color different from the color in position (row,column) of the matrixOfColors.
     * It returns true when the condition written above is respected and false otherwise
     * @param die is the die we have to set
     * @param row is the row coordinate indicated by the player for the placement of the die
     * @param column is the column coordinate indicated by the player for the placement of the die
     * @return true if there for all the possible positions there are no die that have the
     * same color of the matrixOfColor for the same position
     */
    @Override
    public boolean isValidConstraint(Die die, int row, int column){
        if(matrixOfColors[row][column] == null)
            return true;
        else
            return (matrixOfColors[row][column].equals(die.getDieColor()));
    }


}
