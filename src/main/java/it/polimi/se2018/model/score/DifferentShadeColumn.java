package it.polimi.se2018.model.score;

import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.Grid;

/**
 * DifferentShadeColumn Class
 * This class is used to compute the public score earned by column with no Die value repetitions
 * @author Luca Massini
 * @author Davide Lorenzi (JDocs)
 */
public class DifferentShadeColumn implements PublicObjectiveScore {

    public static final int CARD_NUMBER = 4;
    public static final int PUBLIC_VICTORY_POINTS = 4;
    private static DifferentShadeColumn ist = null;

    /**
     * Object Creator
     * This constructor build a new DifferentShadeColumn object
     */
    private  DifferentShadeColumn(){
        new Object();
    }

    /**
     * Singleton Caller
     * This method initialize a new DifferentShadesColumn if it has been never done before during the Match
     * @return a new DifferentShadeColumn object that is a singleton object
     */
    public static DifferentShadeColumn newDifferentShadeColumn(){
        if(ist == null) ist = new DifferentShadeColumn();
        return ist;
    }

    /**
     * Complete Column Checker
     * This method verifies if a column is full
     * @param column is and array of int that represent the shades of the Die of a column passed like parameter
     * @return true if for every position in the column there is a color. No otherwise
     */
    private boolean isACompleteColumn(int[] column){
        int index;
        for(index = 0;index < 4; index++){
            if(column[index] == 0)
                return false;
        }
        return true;
    }
    /**
     * Column Parser
     * This method extract the number of the Die in the grid and sets them on a Array of Strings
     * @param index is the index of column in the grid that must be considered
     * @param grid is the grid to be considered
     * @return an Array of int that represent the shades of the Die in the column of the grid of index = index param.
     * every shade corresponds to one die and only one die. It's possible to have repetitions
     */
    private int[] ArrayOfShadesOfColumn(int index, Grid grid){
        Die[] dice = grid.getColumn(index);
        int[] result=new int[4];
        int i;
        for(i = 0; i < 4; i++){
            if(dice[i] != null)
                result[i] = dice[i].getDieNumber();
            if(dice[i] == null)
                result[i] = 0;
        }
        return result;
    }

    /**
     * DifferentNumbersColumn Checker
     * This method verifies if there aren't 2 or more Die of the same value on the same column
     * @param column is a column of a grid that must be considered
     * @return true if the param array (that is a column of a grid) is made by different Shades
     */
    private boolean isColumnMadeByDifferentShades(int[] column){
        int index1;
        int index2;
        if(!isACompleteColumn(column))
            return false;
        for(index1 = 0; index1 < 4; index1++){
            for(index2 = 0; index2 < 4; index2++){
                if(column[index1] == (column[index2]) &&index1 != index2)
                    return false;
            }
        }
        return true;
    }

    /**
     * PublicScore Computer
     * This method return the Public score earned applying this PublicObjectiveCard
     * @param grid it is the Grid to be scored
     * @return the score of that grid that represent the number of columns that are composed of different Die values
     * multiplied for the PUBLIC_VICTORY_POINTS of the PublicObjectiveCard
     */
    @Override
    public int publicScore(Grid grid){
        int count = 0;
        int index;
        int[] tmp;
        for(index = 0; index < 5; index++){
            tmp = ArrayOfShadesOfColumn(index, grid);
            if(isColumnMadeByDifferentShades(tmp))
                count++;
        }
        return count* PUBLIC_VICTORY_POINTS;
    }

    /**
     * CardNumber Getter
     * @return number of the PublicObjectiveCard
     */
    @Override
    public int getCardNumber() {
        return CARD_NUMBER;
    }
}
