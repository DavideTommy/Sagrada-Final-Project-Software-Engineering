package it.polimi.se2018.model.score;

import it.polimi.se2018.model.Grid;

/**
 * Light Shades Points Counter Class implements PublicObjectiveScore
 * This class return the points earned by the number of 1 and 2 sets (couple everywhere in the player's Die grid)
 * @author Davide Lorenzi
 * @author Federico Lichinchi
 */

public class LightShades implements PublicObjectiveScore {

    public static final int CARD_NUMBER = 5;
    public static final int PUBLIC_VICTORY_POINTS = 2;
    int numberOfTwo = 0;
    int numberOfOne = 0;
    int number1;
    int number2;
    private static  LightShades ist = null;

    /**
     * Class Constructor
     * This Method initialize a new Object
     */
    private LightShades(){
        new Object();
    }

    /**
     * Singleton Constructor
     * This Method initialize a instance of the class
     * @return the actual instance of Light Shades
     */
    public static LightShades newLightShades(){
        if(ist == null) ist = new LightShades();
        return ist;
    }

    /**
     * Score Returner
     * Return score method to main CalcScore class
     * @param grid player's Die grid
     * @return lightSets score
     */

    @Override
    public int publicScore(Grid grid) {
        int score = 0;
        calcLightSets(grid);
        number1 = numberOfOne;
        number2 = numberOfTwo;
        score = minSet(number1, number2) * PUBLIC_VICTORY_POINTS;
        numberOfOne = 0;
        numberOfTwo = 0;
        return score;
    }

    /**
     * CardNumber Getter
     * This method returns tha required CardNumber
     * @return number of the PublicObjectiveCard
     */
    @Override
    public int getCardNumber() {
        return CARD_NUMBER;
    }

    /**
     * LightSets Counter
     * This method calculate the number Die for each value
     * @param localGrid player's Die grid
     */
    public void calcLightSets(Grid localGrid) {
        for (int i = 0; i < 4;i++) {
            for (int j = 0; j < 5; j++) {
                if (localGrid.getDie(i,j) != null) {
                    if(localGrid.getDie(i,j).getDieNumber() == 1) {
                        numberOfOne++;
                    }
                    else if (localGrid.getDie(i,j).getDieNumber() == 2) {
                        numberOfTwo++;
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
        int mediumSet;
        if(number2 >= number1) {
            mediumSet = number1;
        }
        else {
            mediumSet = number2;
        }
        return mediumSet;
    }
}