package it.polimi.se2018.model.score;

import it.polimi.se2018.model.Grid;

/**
 * Different Shades Set Counter implements PublicObjectiveScore
 * This class is used to compute the public score earned by set of Die of different colors
 * @author Federico Lichinchi
 * @author Davide Lorenzi
 */

public class DifferentColors implements PublicObjectiveScore {

    public static final int VICTORY_POINTS = 4;
    public static final int CARD_NUMBER = 10;
    private static DifferentColors ist = null;
    private int differentColorsSet = 0;
    private int[] colorsOccurrence = { 0, 0, 0, 0, 0 };
    private String[] colors = { "Red", "Blue", "Yellow", "Green", "Purple" };

    /**
     * Class Constructor
     * This Method initialize a new Object
     */
    private DifferentColors(){
        new Object();
    }

    /**
     * Singleton Constructor
     * This Method initialize a instance of the class
     * @return the actual instance of Different Colors
     */
    public static DifferentColors newDifferentColors(){
        if(ist == null) ist = new DifferentColors();
        return ist;
    }

    /**
     * Score Returner
     * Return score method to main CalcScore class
     * @param grid player's Die grid
     * @return Different Shades Sets score
     */
    @Override
    public int publicScore(Grid grid) {
        int score;
        calcDifferentColors(grid);
        score = differentColorsSet * VICTORY_POINTS;
        differentColorsSet = 0;
        for (int i = 0; i < 5; i++) {
            colorsOccurrence[i] = 0;
        }
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

    /**
     * Occurrences Getter
     * This method calculate the number of occurrences for each Die color
     * @param localGrid player's Die grid
     */
    public void calcDifferentColors(Grid localGrid) {
        int len;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                if (localGrid.getDie(i, j) != null) {
                    len = colors.length;
                    for(int k = 0; k < len; k++) {
                        if(localGrid.getDie(i , j).getDieColor().equals(colors[k])) {
                            colorsOccurrence[k]++;
                            len = 0;
                        }
                    }
                }
            }
        }
        differentColorsSet = minSet(colorsOccurrence);
    }

    /**
     * MinSet Counter
     * This method finds the minimum number in the array
     * @param ar array of color occurrences
     * @return min number of all color occurrences
     */

    public int minSet(int[] ar) {
        int min = 1000;
        for(int i = 0; i < ar.length; i++) {
            if(ar[i] < min) {
                min = ar[i];
            }
        }
        return min;
    }
}
