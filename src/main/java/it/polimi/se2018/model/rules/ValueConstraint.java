package it.polimi.se2018.model.rules;

import it.polimi.se2018.model.Die;

/**
 * ValueConstraint Class implements SchemeCardConstraint
 * This class checks all value constraints
 * @author Luca Massini
 */

public class ValueConstraint implements SchemeCardConstraint {

    private int[][] matrixOfNumbers;

    /**
     * ValueConstraint Constructor
     * This method initialize a new MatrixOfNumbers
     * @param matrixOfNumbers is the filled MatrixOfNumbers
     */
    public ValueConstraint(int[][] matrixOfNumbers){
        this.matrixOfNumbers = matrixOfNumbers;
    }


    /**
     * Constraint Validator
     * @param dice is the die that must be putted in the grid
     * @return true if there's no die in position that has the same number of the
     * matrixOfNumbers in the same position
     */
    @Override
    public boolean isValidConstraint(Die dice, int line, int column) {
        return matrixOfNumbers[line][column] == 0 || matrixOfNumbers[line][column] == dice.getDieNumber();
    }
}

