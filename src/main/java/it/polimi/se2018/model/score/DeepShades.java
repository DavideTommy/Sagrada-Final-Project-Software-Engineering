package it.polimi.se2018.model.score;

import it.polimi.se2018.model.Grid;

/**
 * Light Shades Points Counter Class implements PublicObjectiveScore
 * This class return the points earned by the number of 5 and 6 sets (couple everywhere in the player's Die grid)
 * @author Davide Lorenzi
 * @author Federico Lichinchi
 */

public class DeepShades implements PublicObjectiveScore {

    public static final int PUBLIC_VICTORY_POINTS = 2;
    public static final int CARD_NUMBER = 7;
    private int numberOfFive = 0;
    private int numberOfSix = 0;
    int number1;
    int number2;
    private static DeepShades ist = null;

    /**
     * Class Constructor
     * This Method initialize a new Object
     */
    private DeepShades() {
        new Object();
    }

    /**
     * Singleton Constructor
     * This Method initialize a instance of the class
     * @return the actual instance of Deep Shades
     */
    public static DeepShades newDeepShades() {
        if (ist == null) ist = new DeepShades();
        return ist;
    }

    /**
     * Score Returner
     * Return score method to main CalcScore class     *
     * @param grid player's Die grid
     * @return deepSets score
     */

    @Override
    public int publicScore(Grid grid) {
        int score;
        calcDeepSets(grid);
        number1 = numberOfFive;
        number2 = numberOfSix;
        score = minSet(number1, number2) * PUBLIC_VICTORY_POINTS;
        numberOfFive = 0;
        numberOfSix = 0;
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
     * DeepSets Counter
     * This method calculate the number of Die for each value
     * @param localGrid player's Die grid
     */
    void calcDeepSets(Grid localGrid) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                if (localGrid.getDie(i, j) != null) {
                    if (localGrid.getDie(i, j).getDieNumber() == 5) {
                        numberOfFive++;
                    } else if (localGrid.getDie(i, j).getDieNumber() == 6) {
                        numberOfSix++;
                    }
                }
            }
        }
    }

    /**
     * MinSet Counter
     * This method finds the minimum number of sets between the two numbers
     * @param number1 first number of the comparison
     * @param number2 second number of the comparison
     */

    int minSet(int number1, int number2) {
        int deepSet;
        if (number2 >= number1) {
            deepSet = number1;
        } else {
            deepSet = number2;
        }
        return deepSet;
    }
}
