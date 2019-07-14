package it.polimi.se2018.model.score;

import it.polimi.se2018.model.Grid;

/**
 * Light Shades Points Counter Class implements PublicObjectiveScore
 * This class return the points earned by the number of 3 and 4 sets (couple everywhere in the player's Die grid)
 * @author Davide Lorenzi
 * @author Federico Lichinchi
 */

public class MediumShades implements PublicObjectiveScore {

    public static final int CARD_NUMBER = 6;
    public static final int PUBLIC_VICTORY_POINTS = 2;
    int numberOfThree = 0;
    int numberOfFour = 0;
    int number1;
    int number2;
    private static  MediumShades ist = null;


    /**
     * Class Constructor
     * This Method initialize a new Object
     */
    private MediumShades(){
        new Object();
    }

    /**
     * Singleton Constructor
     * This Method initialize a instance of the class
     * @return the actual instance of Medium Shades
     */
    public static MediumShades newMediumShades(){
        if(ist == null) ist = new MediumShades();
        return ist;
    }
    /**
     * Score Returner
     * Return score method to main CalcScore class
     * @param grid player's Die grid
     * @return mediumSets score
     */

    @Override
    public int publicScore(Grid grid) {
        int score = 0;
        calcMediumSets(grid);
        number1 = numberOfThree;
        number2 = numberOfFour;
        score = minSet(number1, number2) * PUBLIC_VICTORY_POINTS;
        numberOfFour = 0;
        numberOfThree = 0;
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
     * Medium Sets counter
     * This method calculate the number of Die for each value
     * @param localGrid player's Die grid
     */

    public void calcMediumSets(Grid localGrid) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                if (localGrid.getDie(i,j) != null) {
                    if(localGrid.getDie(i,j).getDieNumber() == 3) {
                        numberOfThree++;
                    }
                    else if (localGrid.getDie(i,j).getDieNumber() == 4) {
                        numberOfFour++;
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