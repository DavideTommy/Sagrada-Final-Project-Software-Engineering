package it.polimi.se2018.model.rules;

import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.Grid;

/**
 * MoveRules Interface
 * This interface manages the movement rules
 * @author Luca Massini
 */
public interface MoveRules {
    boolean checkPlacements(int row, int column, Grid grid, Die dice);
}
