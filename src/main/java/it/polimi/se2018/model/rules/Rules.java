package it.polimi.se2018.model.rules;

import it.polimi.se2018.model.*;

import java.io.Serializable;

/**
 * Rules Class
 * @author Luca Massini
 * @author Davide Lorenzi (JDocs)
 */
public class Rules implements Serializable {
    private static Rules ist = null;

    /**
     * Rules Builder
     * This method is empty because of the singleton characteristic of the class
     */
    private Rules(){

    }

    /**
     * Singleton Caller
     * This method creates a new rules instance, if it hasn't already been done
     * @return a new Rules object that is a singleton object
     */
    public static Rules newRules(){
        if(ist == null)ist = new Rules();
        return ist;
    }

    /**
     * ColorRules Checker
     * This method checks the correct application of color rules
     * @param die it is the die whose setting color rules (in position = row, column) must be checked
     * @param row is the row of the grid in which i want to place the die
     * @param column is the column of the grid in which i want to place the die
     * @param grid it's the grid of which I have to check the color rules. This method checks if is possible to place
     *             a die (param die) in (row, column) in the grid (param grid).
     * @return true if all color rules have been respected and no otherwise.
     */
    private boolean checkColorRules(Die die, int row, int column, Grid grid){
        ChoseConstraint constraint = new ChoseConstraint(new ColorConstraint(grid.getSchemeCard().getMatrixOfColors()), die);
        return constraint.executeConstraint(row,column);
    }

    /**
     * PlacementRules Checker
     * This method checks the correct application of placement rules
     * @param die it is the die whose setting placement rules (in position = row, column) must be checked
     * @param row is the row of the grid in which i want to place the die
     * @param column is the column of the grid in which i want to place the die
     * @param grid it's the grid of which I have to check the placement rules. This method checks if is possible to place
     *             a die (param die) in (row, column) in the grid (param grid).
     * @return true if all placement rules have been respected and no otherwise.
     */
    private boolean checkOnlyPlacementRules(int row, int column, Die die, Grid grid){
        ChosePlacementRules rules = new ChosePlacementRules(new PlacementOnlyConstraint());
        return rules.executeCheckingPlacement(row,column,grid,die );
    }

    /**
     * NumberPlacementRules Checker
     * This method checks the correct application of value placement rules
     * @param die it is the die whose setting value placement rules (in position = row, column) must be checked
     * @param row is the row of the grid in which i want to place the die
     * @param column is the column of the grid in which i want to place the die
     * @param grid it's the grid of which I have to check the value placement rules. This method checks if is possible to place
     *             a die (param die) in (row, column) in the grid (param grid).
     * @return true if all value placement rules have been respected and no otherwise.
     */
    private boolean checkNumberPlacementRules(int row, int column, Die die, Grid grid){
        ChosePlacementRules rules = new ChosePlacementRules(new PlacementNumberConstraint());
        return rules.executeCheckingPlacement(row,column,grid,die);
    }

    /**
     * ColorPlacementRules Checker
     * This method checks the correct application of color placement rules
     * @param die it is the die whose setting color placement rules (in position = row, column) must be checked
     * @param row is the row of the grid in which i want to place the die
     * @param column is the column of the grid in which i want to place the die
     * @param grid it's the grid of which I have to check the color placement rules. This method checks if is possible to place
     *             a die (param die) in (row, column) in the grid (param grid).
     * @return true if all color placement rules have been respected and no otherwise.
     */
    private boolean checkColorPlacementRules(int row, int column, Die die, Grid grid){
        ChosePlacementRules rules = new ChosePlacementRules(new PlacementColorConstraint());
        return rules.executeCheckingPlacement(row, column, grid, die);
    }

    /**
     * ValueRules Checker
     * This method checks the correct application of value rules
     * @param die it is the die whose setting value rules (in position = row, column) must be checked
     * @param row is the row of the grid in which i want to place the die
     * @param column is the column of the grid in which i want to place the die
     * @param grid it's the grid of which I have to check the value rules. This method checks if is possible to place
     *             a die (param die) in (row, column) in the grid (param grid).
     * @return true if all value rules have been respected and no otherwise.
     */
    private boolean checkValueRules(Die die, int row, int column, Grid grid){
        ChoseConstraint constraint = new ChoseConstraint( new ValueConstraint(grid.getSchemeCard().getMatrixOfNumbers()), die);
        return constraint.executeConstraint(row, column);
    }

    /**
     * Rules Checker
     * This method checks the correct application of rules
     * @param die it is the die whose setting rules (in position=row,column) must be checked
     * @param row is the row of the grid in which i want to place the die (die)
     * @param column is the column of the grid in which i want to place the die (param die)
     * @param grid it's the grid of which I have to check the rules. This method checks if is possible to place
     *             a die (param die) in (row,column) in the grid (param grid).
     * @return true if all the rules have been respected and no otherwise.
     */
    public boolean checkAllRules(Die die, int row, int column, Grid grid){
        boolean notFirstTimeRules = (checkNumberPlacementRules(row,column, die, grid)&& checkValueRules(die, row, column, grid) && checkColorPlacementRules(row, column, die, grid) && checkOnlyPlacementRules(row, column, die, grid)&& checkColorRules(die, row, column, grid));
        boolean firstTimeRules = grid.isValidFirstTimePlacement(row, column) && checkValueRules(die, row, column, grid) && checkColorRules(die, row, column, grid);
        return (notFirstTimeRules || firstTimeRules)&& (grid.getDie(row, column) == null);
    }


    /**
     * ExceptionColors Checker
     * This method is used to verify all rules except the color rules
     * @param die it is the die whose rules must be checked
     * @param row is the row in the grid of the adding of the die in the grid
     * @param column is the column in the grid of the adding of the die in the grid
     * @param grid the grid whose rules must be checked
     * @return true if all the rules except the color placement rules are respected in case that
     * the die is to be placed in the position (param:row, param:column)
     */
    public boolean checkExceptionColorRules(Die die, int row, int column, Grid grid){
        boolean notFirstTimeRules = (checkNumberPlacementRules(row, column, die, grid)&& checkValueRules(die, row, column, grid) && checkColorPlacementRules(row, column, die, grid) && checkOnlyPlacementRules(row, column, die,  grid));
        boolean firstTimeRules = grid.isValidFirstTimePlacement(row, column) && checkValueRules(die, row, column, grid);
        return (notFirstTimeRules || firstTimeRules)&&(grid.getDie(row, column) == null);
    }

    /**
     * All Placement Rules Checker
     * This method checks all placement rules are respected
     * @param die it is the die whose placement rules must be checked
     * @param row is the row of the possible placement in the grid
     * @param column is the column of the possible placement in the grid
     * @param grid is the grid whose placement rules must be checked
     * @return a boolean value that is true when all the placement rules have been respected and false otherwise
     */
    public boolean checkAllPlacementRules(Die die, int row, int column, Grid grid){
        boolean notFirstTimeRules = (checkNumberPlacementRules(row,column, die, grid) && checkColorPlacementRules(row, column, die, grid) && checkOnlyPlacementRules(row, column, die, grid));
        boolean firstTimeRules = grid.isValidFirstTimePlacement(row, column);
        return notFirstTimeRules && firstTimeRules;

    }

    /**
     * All Placement Except Adjacency Rules Checker
     * This method checks all placement rules are respected except adjacency ones
     * @param die it is the die whose placement rules must be checked
     * @param row is the row of the possible placement in the grid
     * @param column is the column of the possible placement in the grid
     * @param grid is the grid whose placement rules must be checked
     * @return a boolean value that is true when all the placement rules have been respected and false otherwise
     */
    public boolean checkAllExceptAdjacency(Die die, int row, int column, Grid grid){
        boolean notFirstTimeRules = (checkNumberPlacementRules(row,column, die, grid)&& checkValueRules(die, row, column, grid) && checkColorPlacementRules(row, column, die, grid) && checkColorRules(die, row, column, grid));
        boolean firstTimeRules = grid.isValidFirstTimePlacement(row, column) && checkValueRules(die, row, column, grid) && checkColorRules(die, row, column, grid);
        return (notFirstTimeRules || firstTimeRules)&&(grid.getDie(row, column) == null);
    }

    /**
     * Not Adjacency Rules Checker
     * This method checks adjacency rules are not respected
     * @param die it is the die whose placement rules must be checked
     * @param row is the row of the possible placement in the grid
     * @param column is the column of the possible placement in the grid
     * @param grid is the grid whose placement rules must be checked
     * @return a boolean value that is true when all the placement rules have been respected and false otherwise
     */
    public boolean checkNotAdjacency(Die die, int row, int column, Grid grid){
        return !checkOnlyPlacementRules(row,column,die,grid);
    }


    /**
     * ExceptionValue Checker
     * This method is used to verify any value exception
     * @param dice it is the die whose rules must be checked
     * @param line is the line in the grid of the adding of the Die in the grid
     * @param column is the column in the grid of the adding of the Die in the grid
     * @param grid the grid whose rules must be checked
     * @return true if all the rules except the value placement rules are respected in case that
     * the die is to be placed in the position (param:line, param:column)
     */
    public boolean checkExceptionValueRules(Die dice, int line , int column, Grid grid){
        boolean notFirstTimeRules =(checkNumberPlacementRules(line, column,dice,grid) && checkColorPlacementRules(line,column,dice,grid) && checkOnlyPlacementRules(line, column, dice, grid)&& checkColorRules(dice, line, column, grid));
        boolean firstTimeRules= grid.isValidFirstTimePlacement(line,column)  && checkColorRules(dice,line,column,grid);
        return (notFirstTimeRules || firstTimeRules)&&(grid.getDie(line,column) == null);
    }
}
