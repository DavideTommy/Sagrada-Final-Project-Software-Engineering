package it.polimi.se2018.model.score;

import it.polimi.se2018.model.Grid;

/**
 * Interface Public Objective Score Interface
 * Used to call the needed function in relation of which PublicObjectiveCard were set at the beginning of the match.
 * @author Luca Massini
 * @author Davide Lorenzi (JDocs)
 */

public interface PublicObjectiveScore {

    /**
     * Public Score Calculator
     * This Interface manages all class used for the computation of the score
     * @param grid is the player's grid
     * @return the required grid
     * @throws CloneNotSupportedException if is not possible to clone a grid
     */
    int publicScore(Grid grid) throws CloneNotSupportedException;

    /**
     * Card Number Getter
     * This method returns the required card number
     * @return the required card number
     */
    int getCardNumber();
}

