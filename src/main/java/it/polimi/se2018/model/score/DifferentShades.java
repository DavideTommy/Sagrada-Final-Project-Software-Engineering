package it.polimi.se2018.model.score;

import it.polimi.se2018.model.Grid;

/**
 * Different Shades Set Counter implements PublicObjectiveScore
 * This class is used to compute the public score earned by set of Die with different values
 * @author Federico Lichinchi
 * @author Davide Lorenzi
 */

public class DifferentShades implements PublicObjectiveScore {

    public static final int CARD_NUMBER = 8;
    public static final int PUBLIC_VICTORY_POINTS = 5;
    private static DifferentShades ist = null;
    private int differentShadesSet = 0;
    private int[] numbersOccurrence = {0, 0, 0, 0, 0, 0};

    /**
     * Class Constructor
     * This Method initialize a new Object
     */
    private DifferentShades(){
        new Object();
    }

    /**
     * Singleton Constructor
     * This Method initialize a instance of the class
     * @return the actual instance of Different Shades
     */
    static DifferentShades newDifferentShades(){
        if(ist == null) ist = new DifferentShades();
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
        calcDifferentShades(grid);
        score = differentShadesSet * PUBLIC_VICTORY_POINTS;
        differentShadesSet = 0;
        for (int i = 0; i < 5; i++) {
            numbersOccurrence[i] = 0;
        }
        return score;
    }

    /**
     * CardNumber Getter
     * This method returns the required CardNumber
     * @return number of the PublicObjectiveCard
     */
    @Override
    public int getCardNumber() {
        return CARD_NUMBER;
    }

    /**
     * Occurrences Getter
     * This method calculate the number of occurrences for each Die value
     * @param localGrid player's Die grid
     */
    private void calcDifferentShades(Grid localGrid) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                if (localGrid.getDie(i, j) != null && localGrid.getDie(i, j).getDieNumber() > 0) {
                    numbersOccurrence[(localGrid.getDie(i, j).getDieNumber()) - 1]++;
                }
            }
        }
        differentShadesSet = minSet(numbersOccurrence);
    }

    /**
     * MinSet Calculator
     * This method returns the minimum number of the array
     * @param ar array of Die values occurrences
     * @return minimum number of all value occurrences
     */
    int minSet(int[] ar) {
        int min = 1000;
        for (int anAr : ar) {
            if (anAr < min) {
                min = anAr;
            }
        }
        return min;
    }
}