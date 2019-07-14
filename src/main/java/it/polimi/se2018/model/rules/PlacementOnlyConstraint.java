package it.polimi.se2018.model.rules;

import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.Grid;

/**
 * PlacementOnlyConstraint class implements MoveRules
 * This class manage placement constraints
 * @author Luca Massini
 */
public class PlacementOnlyConstraint implements MoveRules {

    private static final int MAXLINE = 3;
    private static final int MAXCOLUMN = 4;
    private static final int MINLINE = 0;
    private static final int MINCOLUMN = 0;

    /**
     * PlacementOnly Constructor
     * This private method set boolean flag "myFirstRound" to false
     * myFirstRound is set to true when player hasn't pass his/her first die placement on the grid yet,
     * instead is set to false when the first placement of the player has already been done
     */

    public PlacementOnlyConstraint() {
        // Constructor
    }

    /**
     * Vertical Adjacency Checker
     * This method checks if there is at least one Die above and below the position (line,column) in the grid
     * returning true if yes and false otherwise
     * @param line is the coordinate of the line during a placement of a die int eh grid in the game
     * @param column is the coordinate of the column during a placement of a die in the grid in the game
     * @param grid is the current grid in the game
     * @return  true if there is at least a die that is below or above the die in position* (line,column)
     */
    private boolean checkVerticalAdjacent(int line, int column, Grid grid) {
        if (line == MINLINE)
            return grid.getDieBelow(line, column) != null;
        if (line == MAXLINE)
            return grid.getDieAbove(line, column) != null;
        else
            return (grid.getDieBelow(line, column) != null || grid.getDieAbove(line, column) != null);
    }


    /**
     * Horizontal Adjacency Checker
     * This method checks if there is at least one  Die to the right and another to the left of the position
     * (line,column) in the grid returning true if yes and false otherwise
     * @param line coordinate of the line of the grid
     * @param column coordinate of the column of the grid
     * @param grid current grid
     * @return true if there's at least on die on the right or on the left of the Die
     * in position(line,column)
     */
    private boolean checkHorizontalAdjacent(int line, int column, Grid grid) {
        if (column == MINCOLUMN)
            return grid.getRightDie(line, column) != null;
        if (column == MAXCOLUMN)
            return grid.getLeftDie(line, column) != null;
        else
            return ((grid.getRightDie(line, column) != null) || (grid.getLeftDie(line, column) != null));
    }

    /**
     * Major Diagonal Adjacency Checker
     * This private boolean method provides to check if there is at least one die diagonal adjacent on the major
     * diagonal to the die in the position(line,column) in the grid returning true if yes and false otherwise
     * @param line is the line coordinate of the grid
     * @param column is the column coordinate of the grid
     * @param grid is the current grid
     * @return true  true if there is at least one die that is  major diagonally adjacent of the die in position
     * (line column). It returns false otherwise
     */
    private boolean checkAdjacencyAlongMajorDiagonal(int line, int column, Grid grid) {
        if ((column == MINCOLUMN && line == MAXLINE) || (column == MAXCOLUMN && line == MINLINE))
            return false;
        if (column == MINCOLUMN || line == MINLINE)
            return (grid.getDieOnTheRightBelowDiagonally(line, column) != null);
        if (column == MAXCOLUMN || line == MAXLINE)
            return (grid.dieOnTheLeftAboveDiagonally(line, column) != null);
        else
            return ((grid.getDieOnTheRightBelowDiagonally(line, column) != null) || (grid.dieOnTheLeftAboveDiagonally(line, column) != null));
    }

    /**
     * Minor Diagonal Adjacency Checker
     * this private boolean method provides to check if there is at least one die diagonal adjacent on the non major
     * diagonal to the die in the position(line,column) in the grid returning true if yes and false otherwise
     * @param line is the line coordinate of the grid during a placement of a die in the game
     * @param column is the column coordinate of the grid during a placement of a die in the game
     * @param grid is the current grid
     * @return true if there is at least one die that is non major diagonally adjacent of the die in position
     * (line column). It returns false otherwise
     */
    private boolean checkAdjacencyAlongNotMajorDiagonal(int line, int column, Grid grid) {
        if ((line == MAXLINE && column == MAXCOLUMN) || (line == MINLINE && column == MINLINE))
            return false;
        if (column == MINCOLUMN || line == MAXLINE)
            return grid.getDieOnTheRightAboveDiagonally(line, column) != null;
        if (column == MAXCOLUMN || line == MINLINE)
            return grid.getDieOnTheLeftBelowDiagonally(line, column) != null;
        else
            return ((grid.getDieOnTheRightAboveDiagonally(line, column) != null) || (grid.getDieOnTheLeftBelowDiagonally(line, column) != null));
    }


    /**
     * Placement Checker
     * This is the only public method in this class and it provides to check
     * if there is at least one die diagonally (major and non major diagonal)
     * or there is at least one die vertically or horizontally respect on
     * the die in the position (line,column) in the grid. This methods returns
     * true if the conditions written above are respected and false otherwise.
     * You have also to consider that in case the grid is null it returns always
     * true
     * @param row coordinate of the line of the grid during the placement of a die in the game
     * @param column coordinate of the column of the grid during the placement of a die in the game
     * @param grid is the current
     * @param die die set on the grid
     * @return true if all the adjacency Placement Rules of the game are respected. False otherwise
     */

    @Override
    public boolean checkPlacements(int row, int column, Grid grid, Die die) {
        if (grid == null)
            return true;
        else {
            boolean orthogonallyAdjacency;
            boolean diagonallyAdjacency;
            orthogonallyAdjacency = checkHorizontalAdjacent(row, column, grid) || checkVerticalAdjacent(row, column, grid);
            diagonallyAdjacency = checkAdjacencyAlongMajorDiagonal(row, column, grid) || checkAdjacencyAlongNotMajorDiagonal(row, column, grid);
            return   orthogonallyAdjacency || diagonallyAdjacency;
        }
    }
}
