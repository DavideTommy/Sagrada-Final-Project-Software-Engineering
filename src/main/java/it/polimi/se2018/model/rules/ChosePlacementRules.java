package it.polimi.se2018.model.rules;

import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.Grid;

/**
 * ChosePlacementRules Class
 * This class is used to verify if a die placement is correct or not
 * @author Luca Massini (all the code)
 */
public class ChosePlacementRules  {

    private MoveRules moveRules;

    /**
     * PlacementRules Constructor
     * This method sets a new MoveRules
     * @param moveRules is the interface that is passed to be instanced in this object
     */
    ChosePlacementRules(MoveRules moveRules){
        this.moveRules = moveRules;
    }

    /**
     * PlacementChecker Runner
     * This method execute the PlacementChecker
     * @param line is the line coordinate of placement in the grid of which the adjacency placement rules
     *             must be checked
     * @param column is the column coordinate of the placement in the grid of which the adjacency
     *               placement rules must be checked
     * @param grid is the current grid
     * @param die is the die of which we have to check the correct placement
     * @return true if the adjacency placement rules have been respected
     */

    protected boolean executeCheckingPlacement(int line, int column, Grid grid, Die die){
        return moveRules.checkPlacements(line, column, grid, die);
    }

}
