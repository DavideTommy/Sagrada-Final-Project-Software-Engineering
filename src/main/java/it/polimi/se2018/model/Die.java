package it.polimi.se2018.model;

import java.io.Serializable;

/**
 * Die Class
 * This class creates all Die we use in the match
 * @author Federico Lichinchi
 */
public class Die implements Cloneable, Serializable {

    private String dieColor;
    private int dieNumber;

    /**
     * Constructor method
     * Builder method of die class
     * @param dieColor Color of the die(yellow, red, green, blue or purple)
     * @param dieNumber Number of the die(1,2,3,4,5,6)
     */
    public Die(String dieColor, int dieNumber) {
        this.dieColor = dieColor;
        this.dieNumber = dieNumber;
    }

    /**
     * Number Die Getter
     * This method returns the number of the die
     * @return number (1,2,3,4,5,6)
     */
    public int getDieNumber() {
        return dieNumber;
    }

    /**
     * Color Die Getter
     * This method returns the color of the die
     * @return String (1,2,3,4,5,6)
     */
    public String getDieColor() {
        return dieColor;
    }

    /**
     * Die Color Setter
     * This method changes the color of a die
     * @param dieColor New color of the die
     */
    public void setDieColor(String dieColor) {
        this.dieColor = dieColor;
    }

    /**
     * Die number Setter
     * This method changes the number of a die
     * @param dieNumber New number of the die
     */
    public void setDieNumber(int dieNumber) {
        this.dieNumber = dieNumber;
    }

    /**
     * Die Maker
     * This method creates a new copy of the die that calls it, with same color and same number
     * @return The copy of the die
     * @throws CloneNotSupportedException From interface Cloneable
     */
    public Die clone() throws CloneNotSupportedException {
        return (Die) super.clone();
    }
}
