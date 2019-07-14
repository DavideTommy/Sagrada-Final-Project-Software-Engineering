package it.polimi.se2018.model.score;

import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.Grid;

/**
 * DifferentColorRow Class implements PublicObjectiveScore
 * This class is used to compute the public score earned by rows with no color repetitions
 * @author Luca Massini
 * @author Davide Lorenzi (JDocs)
 */
public class DifferentColorRow implements PublicObjectiveScore {

    public static final int CARD_NUMBER = 1;
    public static final int PUBLIC_VICTORY_POINTS = 6;
    private static DifferentColorRow ist = null;


    /**
     * Object Creator
     * This constructor build the object
     */
    private DifferentColorRow(){
       new Object();
    }


    /**
     * Singleton Caller
     * This method initialize a new DifferentColorRow if it has been never done before during the Match
     * @return a new DifferentColorRow object that is a singleton object
     */
    public static DifferentColorRow newDifferentColorRow(){
        if(ist == null) ist = new DifferentColorRow();
        return ist;
    }


    /**
     * DifferentColorsRow Checker
     * This method verifies if there aren't 2 or more Die of the same colour on the same row
     * @param array is an array of string that represent the colors of the Die of the row
     * @return true if param array is composed of different colors and false otherwise
     */
    private boolean isMadeByDifferentColors(String[] array){
        int index1;
        int index2;

        for(index1 = 0; index1 < 5; index1++){
            if(array[index1] == null)
                return false;
            for(index2 = 0; index2 < 5; index2++){
                if(array[index2] == null)
                    return false;
                if(array[index1].equals(array[index2])&& index1 != index2)
                    return false;
            }
        }
        return true;
    }

    /**
     * Row Parser
     * This method extract the color of the Die in the grid and sets them on a Array of Strings
     * @param index is the index of the row that must be considered
     * @param grid is the grid to be considered
     * @return an Array of Strings that represent the colors of the Die of the row (of index = param index).
     * every color corresponds to one and only one die. It is possible to have repetitions
     */
    private String[] arrayOfColorsOfLine(int index, Grid grid){
        Die[] dice = grid.getRow(index);
        String[] result = new String[5];
        int i;
        for(i=0;i<5;i++){
            if(dice[i] != null)
                result[i] = dice[i].getDieColor();
        }
        return result;
    }

    /**
     * PublicScore Computer
     * This method return the Public score earned applying this PublicObjectiveCard
     * @param grid it is the Grid to be scored
     * @return the score of that grid that represent the number of rows that are composed of different colors
     * multiplied for the PUBLIC_VICTORY_POINTS of the PublicObjectiveCard
     */
    @Override
    public int publicScore(Grid grid) {
        int index;
        int count=0;
        String[] tmp;
        for(index = 0; index < 4; index++){
            tmp = arrayOfColorsOfLine(index, grid);
            if(isMadeByDifferentColors(tmp))
                count++;
        }
        return count * PUBLIC_VICTORY_POINTS;
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
