package it.polimi.se2018.model;

import it.polimi.se2018.model.rules.Rules;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Class Grid
 * This class is used to create every player's grid
 * @author Luca Massini
 * @author Davide Lorenzi (JDocs)
 */
public class Grid implements Cloneable, Serializable {

    private Die[][] matrixOfDice;
    private SchemeCard schemeCard;
    private boolean firstTime;
    private Rules rules;
    private static final int MAXROW = 4;
    private static final int MAXCOL = 5;

    public void setFirstTimeToTrue(){
        firstTime= true;
    }

    /**
     *
     * @return true if this grid object is empty of dice and false otherwise
     */
    public boolean isGridEmpty(){
        for(int i=0; i<MAXROW; i++){
            for(int j=0; j<MAXCOL; j++){
                if(matrixOfDice[i][j]!=null)
                    return false;
            }
        }
        return true;
    }


    /**
     * Grid Constructor
     * This constructor of the class Grid initializes the attributes matrixOfDice
     * and taking in input a SchemeCard object it assign it to SchemeCard attribute
     * to this constructor
     * @param schemeCard SchemeCard chosen by the player
     */
    public Grid(SchemeCard schemeCard) {
        matrixOfDice = new Die[4][5];
        this.schemeCard = schemeCard;
        firstTime = true;
        rules = Rules.newRules();
    }


    /**
     * Die Remover
     * This method removes a die from the matrix of dice
     * @param row is the row coordinate of the die
     * @param column is the column coordinate of the die
     */
    public void removeDie(int row, int column){
        matrixOfDice[row][column]=null;
    }

    /**
     * Die Getter
     * This is a getter method for the Die in position(line,column) in the grid
     * @param line is the line coordinate of the die in the grid that is returned
     * @param column is the column coordinate of the die in the grid that is returned
     * @return the die in position (line,column)
     */
    public Die getDie(int line, int column){
        if(line < 0 || line > 3 || column < 0 || column > 4)
                        return null;
        else if(matrixOfDice[line][column] == null){
            return null;
        }
         else
             return matrixOfDice[line][column];
    }

    /**
     * SchemeCard Getter
     * This method is used to get a SchemeCard
     * @return the required SchemeCard
     */
    public SchemeCard getSchemeCard() {
        return schemeCard;
    }

    /**
     * Row Getter
     * This method is used to get the Die on a row
     * @param rowIndex the index of the row from which we are extracting Die
     * @return the array of Die
     */
    public Die[] getRow(int rowIndex){
        int i;
        Die[] array = new Die[5];
        for(i = 0 ; i < 5 ; i++){
            if(matrixOfDice[rowIndex][i] != null)
               array[i] = matrixOfDice[rowIndex][i];
        }
        return array;
    }

    /**
     * Column Getter
     * This method is used to get the Die on a column
     * @param columnIndex the index of the column from which we are extracting Die
     * @return the array of Die
     */
    public Die[] getColumn(int columnIndex){
        int i;
        Die[] array=new Die[4];
        for(i = 0;i <4; i++){
                array[i] = matrixOfDice[i][columnIndex];
        }
        return array;
    }

    /**
     * RightDie Getter
     * This method returns the die to the right to nearest the die in position (row, column)
     * @param row is the row coordinate of the reference die
     * @param column is the column coordinate of the reference die
     * @return the die on the right of the Die in position(row, column)
     */
    public Die getRightDie(int row, int column){
        return getDie(row, column+1);
    }

    /**
     * LeftDie Getter
     * This method returns the die to the left to nearest the die in position (row, column)
     * @param row is the row coordinate of the reference die
     * @param column is the column coordinate of the reference die
     * @return the die on the left of the Die in position(row, column)
     */
    public Die getLeftDie(int row, int column){
        return getDie(row, column-1);
    }

    /**
     * BelowDie Getter
     * This method returns the first die below the die in the position (row, column)
     * @param row is the row coordinate of the reference die
     * @param column is the column coordinate of the reference die
     * @return the die below the Die in position(row, column)
     */
    public Die getDieBelow(int row, int column){
        return getDie(row+1,column);
    }

    /**
     * AboveDie Getter
     * This method returns the first die above the die in position (row, column)
     * @param row is the row coordinate of the reference die
     * @param column is the column coordinate of the reference die
     * @return the die above the Die in position(row, column)
     */
    public Die getDieAbove(int row, int column){
        return getDie(row-1, column);
    }

    /**
     * AboveRightDie Getter
     * This method returns the first die nearest that is above and to the right of the die in position (row, column).
     * @param row is the row coordinate of the reference die
     * @param column is the column coordinate of the reference die
     * @return the die above and right the Die in position(row, column)
     */

    public Die getDieOnTheRightAboveDiagonally(int row, int column){
        return getDie(row-1,column+1);
    }

    /**
     * BelowRightDie Getter
     * This method returns the first die nearest that is below and to the right of the die in
     * position (row, column).
     * @param row is the row coordinate of the reference die
     * @param column is the column coordinate of the reference die
     * @return the die below and right the Die in position(row, column)
     */
    public Die getDieOnTheRightBelowDiagonally(int row, int column){
        return getDie(row+1,column+1);
    }

    /**
     * LeftAboveDie Getter
     * This method returns the first die nearest that is above and to the left of the die in position (row, column).
     * @param row is the row coordinate of the reference die
     * @param column is the column coordinate of the reference die
     * @return the die above and left the Die in position(row, column)
     */
    public Die dieOnTheLeftAboveDiagonally(int row, int column){
        return getDie(row-1, column-1);
    }

    /**
     * LeftBelowDie Getter
     * This method returns the first die nearest that is below and to the left of the die in position (row, column).
     * @param row is the row coordinate of the reference die
     * @param column is the column coordinate of the reference die
     * @return the die below and left the Die in position(row, column)
     */
    public Die getDieOnTheLeftBelowDiagonally(int row, int column){
        return getDie(row+1,column-1);
    }


    /**
     * DifficultyValue Getter
     * This method returns the difficultyValue attribute of the object
     * @return the attribute "difficultyValue" of the object
     */
    public int getDifficultyValue(){
        return schemeCard.getDifficultValue();
    }


    /**
     * Grid Clone
     * This method returns a clone of the object
     * @return grid clone
     * @throws CloneNotSupportedException if is not possible to lone the grid
     */
    public Grid clone() throws CloneNotSupportedException {
        return (Grid) super.clone();
    }


    /**
     * Die Adder
     * this method put the die that is passed as parameter in the matrixOfDice in the position(row, column)
     * @param die is the die that must be passed to this method to be put in the grid in position(row, column)
     * @param row is the row coordinate of the grid of the placement of the die
     * @param column is the row coordinate of the grid of the placement of the die
     */
    public void addDie(Die die, int row, int column){
            matrixOfDice[row][column] = die;
            firstTime = false;
    }

    /**
     * FirstPlacement Checker
     * This Method is used to verify the correctness of the first placement
     * @param row is the row coordinate of the reference die
     * @param column is the column coordinate of the reference die
     * @return the boolean value of firstTime and the value of valid rows and column of the first placement
     */
    public boolean isValidFirstTimePlacement(int row, int column) {
        return firstTime && ((row == 3 || row == 0)||(column == 0 || column == 4));
    }


    /**
     * Rules Checker
     * This method returns true if all the placement rules have been Respected
     * @param row is the row coordinate of the coordinate of the placement in the grid
     * @param column is the column coordinate of the placement of a die in the grid
     * @param die the die about which we have to do all checks
     * @return true if all the rules except of the tool cards have been respected
     */
    public boolean checkRules(int row, int column,Die die){
        return rules.checkAllRules(die, row, column, this);
    }

    /**
     * CardName Getter
     * This is a getter method for the attribute cardName that returns tha attribute cardName
     * of the object
     * @return the name of the SchemeCard that is contented in this class
     */
    public String getCardName(){
        return schemeCard.getSchemeCardName();
    }

    /**
     * Die Setter
     * This method is used to set a die into the MatrixOfDice
     * @param die is the die that must be passed to this method to be put in the grid in position(row, column)
     * @param row is the row coordinate of the grid of the placement of the die "Die"
     * @param column is the row coordinate of the grid of the placement of the die "Die"
     * @throws CloneNotSupportedException if is not possible to clone the die
     */
    public void setDie(int row, int column, Die die) throws CloneNotSupportedException {
               matrixOfDice[row][column] = die.clone();
    }

    /**
     * Row Printer
     * This method is used to print a row of the grid as a String
     * @param rowIndex index of the row we are printing
     * @return the message
     */
    private String rowToString(int rowIndex){
        String[][] colors = schemeCard.getMatrixOfColors();
        int[][] numbers = schemeCard.getMatrixOfNumbers();
        StringBuilder builder = new StringBuilder();
        char tmpChar;
        int tmpInt;
        int i;
        for(i=0;i<5;i++) {
            if (matrixOfDice[rowIndex][i] != null) {
                tmpChar = matrixOfDice[rowIndex][i].getDieColor().charAt(0);
                tmpInt = matrixOfDice[rowIndex][i].getDieNumber();
                builder.append("|").append(tmpChar).append(tmpInt);
            } else {
                if (colors[rowIndex][i] == null && numbers[rowIndex][i] == 0)
                    builder.append("|  ");
                else {
                    if (colors[rowIndex][i] == null)
                        builder.append("|").append(numbers[rowIndex][i]).append(" ");
                    else {
                        if (numbers[rowIndex][i] == 0)
                            builder.append("|").append(colors[rowIndex][i].charAt(0)).append(" ");
                    }
                }
            }
        }
        builder.append("|");
        return builder.toString();
    }


    /**
     * Underscore Printer
     * This method is used to print underscores between a row and another
     * @param rowIndex index of the row we're printing
     * @return the result with underscores
     */
    private String underScores(int rowIndex){
        int length = rowToString(rowIndex).length();
        StringBuilder builder= new StringBuilder();
        for(int i = 0; i < length; i++){
            builder.append("_");
        }
        return builder.toString();
    }

    /**
     * Grid Printer
     * This method is used to print the grid
     * @return an ArrayList in which the elements in even position (i) represent the String representation
     * of the i-th row of the grid starting from the top. In the odd positions there are the separators
     * between one row and the other
     */
    public List<String> gridToArrayListOfStrings(){
        ArrayList<String> result = new ArrayList<>();
        int rowIndex;
        for(rowIndex = 0; rowIndex < 4; rowIndex++){
            result.add(rowToString(rowIndex));
            result.add(underScores(rowIndex));
        }
        return result;
    }

    /**
     * Rules Getter
     * This method the rules class.
     * @return grid rules
     */
    public Rules getRules(){
        return rules;
    }


}