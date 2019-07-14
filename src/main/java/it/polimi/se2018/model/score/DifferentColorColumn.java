package it.polimi.se2018.model.score;

import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.Grid;

/**
 * DifferentColorColumn implements PublicObjectiveScore
 * This class is used to compute the public score earned by column with no color repetitions
 * @author Luca Massini
 * @author Davide Lorenzi(JDocs)
 */
public class DifferentColorColumn implements PublicObjectiveScore {

    public static final int PUBLIC_VICTORY_POINTS = 5;
    public static final int CARD_NUMBER = 2;
    private static DifferentColorColumn ist = null;


    /**
     * Object Creator
     * This constructor build the object
     */
    private DifferentColorColumn() {
        new Object();
    }

    /**
     * Singleton Caller
     * This method initialize a new DifferentColorColumn if it has been never done before during the Match
     * @return a new DifferentColorColumn object that is a singleton object
     */
    public static DifferentColorColumn newDifferentColorColumn() {
        if (ist == null) ist = new DifferentColorColumn();
        return ist;
    }

    /**
     * Column Parser
     * This method extract the color of the Die in the grid and sets them on a Array of Strings
     * @param index is the index of the column that must be considered
     * @param grid is the grid to be considered
     * @return an Array of Strings that represent the colors of the Die of the column (of index = param index).
     * every color corresponds to one and only one die. It is possible to have repetitions
     */
    private String[] arrayOfColorsOfColumn(int index, Grid grid) {
        Die[] dice = grid.getColumn(index);
        String[] result = new String[4];
        int i;
        for (i = 0; i < 4; i++) {
            if(dice[i]!=null)
               result[i] = dice[i].getDieColor();
        }
        return result;
    }

    /**
     * DifferentColors Column Checker
     * This method verifies if there aren't 2 or more Die of the same colour on the same column.
     * @param column is an array of string that represent the colors of the Die of the column
     * @return true if param array is composed of different colors and false otherwise
     */
    private boolean isColumnMadeByDifferentColors(String[] column) {
        int index1;
        int index2;
        for (index1 = 0; index1 < 4; index1++) {
            if(column[index1] == null)
                return false;
            for (index2 = 0; index2 < 4; index2++) {
                if(column[index2] == null)
                    return false;
                if (column[index1].equals(column[index2]) && index1 != index2)
                    return false;
            }
        }
        return true;
    }

    /**
     * PublicScore Computer
     * This method return the Public score earned applying this PublicObjectiveCard
     * @param grid it is the Grid to be scored
     * @return the score of that grid that represent the number of columns that are composed of different colors
     * multiplied for the PUBLIC_VICTORY_POINTS of the PublicObjectiveCard
     */
    @Override
    public int publicScore(Grid grid) {
        int count=0;
        int index;
        String[] tmp;
        for(index = 0;index < 5; index++){
            tmp = arrayOfColorsOfColumn(index, grid);
            if(isColumnMadeByDifferentColors(tmp))
                count++;
        }
        return count* PUBLIC_VICTORY_POINTS;
    }

    /**
     * CardNumber Getter
     * This method return the required CardNumber
     * @return number of the PublicObjectiveCard
     */
    @Override
    public int getCardNumber() {
        return CARD_NUMBER;
    }

}