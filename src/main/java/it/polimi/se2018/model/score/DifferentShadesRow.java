package it.polimi.se2018.model.score;

import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.Grid;

/**
 * DifferentShadeRow Class
 * This class is used to compute the public score earned by row with no Die value repetitions
 * @author Luca Massini
 * @author Davide Lorenzi (JDocs)
 */
public class DifferentShadesRow implements PublicObjectiveScore {
    public static final int PUBLIC_VICTORY_POINTS = 5;
    public static final int CARD_NUMBER = 3;
    private static DifferentShadesRow ist = null;


    /**
     * Object Creator
     * This constructor build a new object DifferentShadesRow
     */
    private DifferentShadesRow(){
        new Object();
    }

    /**
     * Singleton Caller
     * This method initialize a new DifferentShadesRow if it has been never done before during the Match
     * @return a new DifferentShadesRow that is a singleton object
     */
    public static DifferentShadesRow newDifferentShadesRow(){
        if(ist == null) ist = new DifferentShadesRow();
        return ist;
    }

    /**
     * DifferentNumbersRow Checker
     * This method verifies if there aren't 2 or more Die of the same value on the same row
     * @param array is a row of a grid that must be considered
     * @return true if the param array (that is a row of a grid) is made by different Shades
     */
    private boolean isMadeByDifferentNumbers(int[] array){
        int i1;
        int i2;
        for(i1 = 0; i1 < 5 ; i1++){
            if (array[i1]==0)
                return false;
            for(i2 = 0; i2 < 5;i2++){
                if(array[i2] == 0)
                    return false;
                if(array[i1] == array[i2] && i2 != i1)
                    return false;
            }
        }
        return true;
    }

    /**
     * Row Parser
     * This method extract the number of the Die in the grid and sets them on a Array of Strings
     * @param index is the index of the row in the grid that must be considered
     * @param grid is the grid that must be considered
     * @return an array of shades (array of int) that represent all the shades content in the Die content in the row. For every die
     * in the row there is a shade(int) in the returning array. It's possible to have repetitions
     */
    private int[] arrayOfNumbersOfLine(int index, Grid grid){
        Die[] dice = grid.getRow(index);
        int[] result = new int[5];
        int i;
        for(i = 0;i < 5; i++){
            if(dice[i] != null)
                result[i] = dice[i].getDieNumber();
            else
                result[i] = 0;
        }
        return result;
    }

    /**
     * PublicScore Computer
     * This method return the Public score earned applying this PublicObjectiveCard
     * @param grid it is the Grid to be scored
     * @return the score of that grid that represent the number of rows that are composed of different Die values
     * multiplied for the PUBLIC_VICTORY_POINTS of the PublicObjectiveCard
     */
    @Override
    public int publicScore(Grid grid) {
        int index;
        int count = 0;
        int[] tmp;
        int score;
        for(index = 0; index < 4; index++){
            tmp = arrayOfNumbersOfLine(index,grid);
            if(isMadeByDifferentNumbers(tmp))
                count++;
        }
        score = count* PUBLIC_VICTORY_POINTS;
        return score;
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
