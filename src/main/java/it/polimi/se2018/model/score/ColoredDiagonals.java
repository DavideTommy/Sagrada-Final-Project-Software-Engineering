package it.polimi.se2018.model.score;

import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.Grid;

/**
 * ColoredDiagonals Class implements PublicObjectiveScore
 * This class is used to compute the public score earned by adjacent Die of the same color on the same diagonal
 * @author Federico Lichinchi
 * @author Davide Lorenzi
 */
public class ColoredDiagonals implements PublicObjectiveScore {

    public static final int CARD_NUMBER = 9;
    private int totalScore;
    private static ColoredDiagonals ist = null;

    /**
     * Class Constructor
     * This Method initialize a new Object
     */
    private ColoredDiagonals(){
        new Object();
    }

    /**
     * Singleton Constructor
     * This Method initialize a instance of the class
     * @return the actual instance of Colored Diagonals
     */
    public static ColoredDiagonals newColoredDiagonals(){
        if(ist == null) ist = new ColoredDiagonals();
        return ist;
    }

    /**
     * Public Score Method
     * This method returns a part of the total score earned by setting Die of the same
     * color adjacent on the same diagonal
     * @param g Grid in which apply the control
     * @return the partial Public Score of the card
     * @throws CloneNotSupportedException From interface Cloneable
     */
    @Override
    public int publicScore(Grid g) throws CloneNotSupportedException {
        totalScore = 0;
        Grid grid = g.clone();
        calcColoredDiagonals(grid,0,0);
        calcColoredDiagonals(grid,0,1);
        return totalScore;
    }

    /**
     * CardNumber Getter
     * This method return the required CardNumber
     * @return number of the PublicObjectiveCard
     */
    @Override
    public int getCardNumber() {
        return CARD_NUMBER;
    }

    /**
     * Summing Points Function
     * This method sums 1 for every time that is found a closer die of the same kind on the diagonal.
     * @param g player's Die grid
     * @param x coordinate x of a cell
     * @param y coordinate y of a cell
     * @throws CloneNotSupportedException From interface Cloneable
     */
    public void calcColoredDiagonals(Grid g, int x, int y) throws CloneNotSupportedException {
        if((x < 4 && x > -1) && (y < 5 && y > -1)) {
            if (checkForSameColor(g, x, y)) {
                totalScore++;
                g.setDie(x, y, new Die(g.getDie(x,y).getDieColor(), 0));
            }
            else {
                g.setDie(x, y, new Die("Black", 0));
            }
            if(x > 0) {
                if(y < 4) {
                    Die d1 = g.getDie(x - 1, y + 1);
                    if(d1 != null && d1.getDieNumber() > 0) {
                        calcColoredDiagonals(g, x - 1, y + 1);
                    }
                }
                if(y > 0) {
                    Die d2 = g.getDie(x - 1, y - 1);
                    if(d2 != null && d2.getDieNumber() > 0) {
                        calcColoredDiagonals(g, x - 1, y - 1);
                    }
                }
            }
            if(x < 3) {
                if(y < 4) {
                    Die d3 = g.getDie(x + 1, y + 1);
                    if(d3 != null && d3.getDieNumber() > 0) {
                        calcColoredDiagonals(g, x + 1, y + 1);
                    }
                }
                if(y > 0) {
                    Die d4 = g.getDie(x + 1, y - 1);
                    if(d4 != null && d4.getDieNumber() > 0) {
                        calcColoredDiagonals(g, x + 1, y - 1);
                    }
                }
            }
        }
    }

    /**
     * Color Checker
     * This method returns true if 2 or more adjacent Die in a diagonal are of the same colour
     * @param g player's Die grid
     * @param x coordinate x of a cell
     * @param y coordinate y of a cell
     * @return true if previous conditions are respected
     */

    public boolean checkForSameColor(Grid g, int x, int y) {
        Die d = g.getDie(x, y);
        if(d == null) {
            return false;
        }
        else {
            Die d1 = g.getDie(x - 1, y - 1);
            if(d1 != null && d1.getDieColor().equals(d.getDieColor())) {
                    return true;
            }
            Die d2 = g.getDie(x + 1, y + 1);
            if(d2 != null && d2.getDieColor().equals(d.getDieColor())) {
                    return true;
            }
            Die d3 = g.getDie(x - 1, y + 1);
            if(d3 != null && d3.getDieColor().equals(d.getDieColor())) {
                    return true;
            }
            Die d4 = g.getDie(x + 1, y - 1);
            return d4 != null && d4.getDieColor().equals(d.getDieColor());
        }
    }
}
