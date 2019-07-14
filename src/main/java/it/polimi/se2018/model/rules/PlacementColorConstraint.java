package it.polimi.se2018.model.rules;

import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.Grid;

/**
 * PlacementColorConstraint Class implements MoveRules
 * This class manage all color restrictions
 * @author Luca Massini (all the code except JDocs)
 * @author Davide Lorenzi (JDocs)
 */

public class PlacementColorConstraint implements MoveRules {

    private static final int MAXLINE = 3;
    private static final int MAXCOLUMN = 4;
    private static final int MINLINE = 0;
    private static final int MINCOLUMN = 0;

    /**
     * BelowDieColour Checker
     * This method verifies if the chosen die and the one below have different colors
     * @param row is the row coordinate of the grid
     * @param column is the column coordinate of the grid
     * @param grid is the current grid
     * @param die is the current die
     * @return true if the color of the chosen die is different from the one below
     * False otherwise
     */
    private boolean isTheColorOfTheDieBelowDifferentFromDice(int row , int column , Die die , Grid grid) {
        if(row == MAXLINE)
            return true;
        if (grid.getDieBelow(row, column) == null) return true;
        return !(grid.getDieBelow(row, column).getDieColor().equals(die.getDieColor()));
    }

    /**
     * AboveDieColour Checker
     * This method verifies if the chosen die and the one above have different colors
     * @param row is the row coordinate of the grid
     * @param column is the column coordinate of the grid
     * @param grid is the current grid
     * @param die is the current die
     * @return true if the color of the chosen die is different from the one above.
     * False otherwise
     */
    private boolean isTheColorOfTheDieAboveDifferentFromDice(int row, int column, Die die, Grid grid) {
        if(row == MINLINE)
            return true;
        if (grid.getDieAbove(row, column) == null) return true;
        return !(grid.getDieAbove(row, column).getDieColor().equals(die.getDieColor()));
    }

    /**
     * RightDieColour Checker
     * This method verifies if the chosen die and the one on its right have different colors
     * @param row is the row coordinate of the grid
     * @param column is the column coordinate of the grid
     * @param grid is the current grid
     * @param die is the current die
     * @return true if the color of the chosen die is different from the one on the right.
     * False otherwise
     */
    private boolean isTheColorOfTheDieOnTheRightDifferentFromDice(int row , int column, Die die, Grid grid) {
        if(column == MAXCOLUMN) {
            return true;
        }
        if (grid.getRightDie(row, column) == null) return true;
        return !(grid.getRightDie(row, column).getDieColor().equals(die.getDieColor()));
    }

    /**
     * LeftDieColour Checker
     * This method verifies if the chosen die and the one on its left have different colors
     * @param row is the row coordinate of the grid
     * @param column is the column coordinate of the grid
     * @param grid is the current grid
     * @param die is the current die
     * @return true if the color of the chosen die is different from the one on the left.
     * False otherwise
     */
    private boolean isTheColorOfTheDieOnTheLeftDifferentFromDice(int row, int column, Die die, Grid grid){
        if(column == MINCOLUMN)
            return true;
        if (grid.getLeftDie(row, column) == null) return true;
        return !(grid.getLeftDie(row, column).getDieColor().equals(die.getDieColor()));
    }

    /**
     * ColorVerticalAdjacency Checker
     * This private boolean method checks if there isn't  at least one die above or below the die in position
     * (row,column) in the grid that has the same color of it. This method return true if the condition
     * written above is respected and false otherwise
     * @param row is the row coordinate of the grid
     * @param column is the column coordinate of the grid
     * @param grid is the current grid
     * @param die is the current die
     * @return true if there's no a die that has the same color of the the die
     * in position (row,column) and is below or above the die. False otherwise
     */
    private boolean checkNotColorVerticalAdjacency(int row, int column, Grid grid, Die die) {
           boolean notDieAboveAdjacency = isTheColorOfTheDieAboveDifferentFromDice(row, column, die, grid);
           boolean notDieBelowAdjacency = isTheColorOfTheDieBelowDifferentFromDice(row, column, die, grid);
           return notDieAboveAdjacency && notDieBelowAdjacency;
    }

    /**
     * ColorHorizontalAdjacency Checker
     * This private boolean method checks if there isn't  at least
     * one die to the right or to the left of  the the in position (row,column)
     * in the grid that has the same color of it. This method return true if the condition
     * written above is respected and false otherwise
     * @param row is the row coordinate of the grid
     * @param column is the column coordinate of the grid
     * @param grid is the current grid
     * @param die is the current die
     * @return true if there's no a die that has the same color of the the die
     * in position (row,column) and is to the right or to the left of the die.
     * False otherwise
     */
    private boolean checkNotColorHorizontalAdjacency(int row, int column, Grid grid, Die die) {
        boolean notDieOnTheRightAdjacency = isTheColorOfTheDieOnTheRightDifferentFromDice(row, column, die, grid);
        boolean notDieOnTheLeftAdjacency = isTheColorOfTheDieOnTheLeftDifferentFromDice(row, column, die, grid);
        return notDieOnTheLeftAdjacency && notDieOnTheRightAdjacency;
    }

    /**
     * Placement Checker
     * This private boolean method checks if there isn't at least one die to the right or to the left or
     * above or below the die in position that has the same color of it. It returns
     * true if the condition written above is respected or false otherwise
     * @param row is the line coordinate of the grid
     * @param column is the column coordinate of the grid
     * @param grid is the current grid
     * @param die is the current die
     * @return true if the grid is null or if notColorVerticalAdjacency(line, column, grid) is
     * true and also noColorHorizontalAdjacency(line, column, grid)is true.
     * It return false if the condition written above isn't respected
     */
    @Override
    public boolean checkPlacements(int row, int column, Grid grid, Die die) {
        boolean notColorVerticallyAdjacency;
        boolean notColorHorizontalAdjacency;
        if (grid != null) {
            notColorHorizontalAdjacency = checkNotColorHorizontalAdjacency(row, column, grid, die);
            notColorVerticallyAdjacency = checkNotColorVerticalAdjacency(row, column, grid, die);
            return notColorHorizontalAdjacency && notColorVerticallyAdjacency;
        }
        else
            return true;
    }
}
