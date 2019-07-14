package it.polimi.se2018.model;
import it.polimi.se2018.model.cards.PrivateObjectiveCard;

import java.io.Serializable;

/**
 * Player Class
 * This class defines the player's structure
 * @author Federico Lichinchi
 * @author Davide Lorenzi (JDocs)
 */
public class Player implements Serializable, Cloneable {

    private String playerName;
    private String playerAddress;
    private String color;
    protected int score;
    private int numFavCounter;
    private int markerPos;
    private Grid grid;
    private PrivateObjectiveCard privateObjectiveCard;



    /**
     * Player Constructor
     * Initially, the player have to choose one from four SchemeCards passed by Match class. Every player must have
     * a name, an assigned IP address, a color (for the use of markers), an assigned SchemeCard and an assigned
     * PrivateObjectiveCard
     * numFavCounter = difficultValue of the SchemeCard
     * @param playerName Name of the player
     * @param playerAddress IP address of the player device
     * @param color Color of the player
     * @param grid  is the SchemeCard assigned to the player
     * @param privateObjectiveCard PrivateObjectiveCard of the player
     */
    public Player(String playerName, String playerAddress, String color, Grid grid, PrivateObjectiveCard privateObjectiveCard) {
        this.playerAddress = playerAddress;
        this.playerName = playerName;
        this.grid = grid;
        this.privateObjectiveCard = privateObjectiveCard;
        this.color = color;
        if(grid != null) {
            this.numFavCounter = grid.getDifficultyValue();
        }
        else {
            this.numFavCounter = 0;
        }
        this.markerPos = 0;
    }


    /**
     * Name Getter
     * This method returns the name of the player
     * @return String
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * Address Getter
     * This method returns the IP address of player device
     * @return String
     */
    public String getPlayerAddress() {
        return playerAddress;
    }

    /**
     * Color Getter
     * This method returns the color assigned to the player
     * @return String (blue, purple, green or red)
     */
    public String getColor() {
        return color;
    }

    /**
     * Grid Getter
     * This method returns the SchemeCard assigned to the player
     * @return Grid
     */
    public Grid getGrid() {
        return grid;
    }

    /**
     * PrivateObjectiveCard Getter
     * This method returns the PrivateObjectiveCard of the player
     * @return PrivateObjectiveCard
     */
    public PrivateObjectiveCard getPrivateObjectiveCard() {
        return privateObjectiveCard;
    }

    /**
     * MarkerPos Getter
     * This method returns the position of the marker of the player in the ScoreTrack
     * @return position (number)
     */
    public int getMarkerPos() {
        return markerPos;
    }

    /**
     * Score Getter
     * This method returns the score of the player
     * @return number
     */
    public int getScore() {
        return score;
    }

    /**
     * FavourCounter Getter
     * This method returns the number of favorite counters the player have
     * @return number
     */
    public int getNumFavCounter() {
        return numFavCounter;
    }

    /**
     * Score Adder
     * This method updates the score of the player
     * @param number The number that increments the score
     */
    public void addScore(int number) {
        score += number;
    }

    /**
     * PositionMarker Shifter
     * This method updates the marker position of the player
     * @param number The number of positions of which shift the marker position
     */
    public void shiftMarkerPos(int number) {
        markerPos += number;
    }

    /**
     * FavourCounter Decrement
     * This method updates the number of the favorite counters of the player
     * @param number Number that increments the number of favor counters
     */
    public void decrementNumFavCounter(int number) {

        if(numFavCounter >= number)
            numFavCounter -= number;
        else
            numFavCounter = 0;
    }

    /**
     * Name Setter
     * This method changes the name of the player
     * @param playerName String of the new name of the player
     */
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    /**
     * Color Setter
     * This method changes the color of the player
     * @param color String of the new color of the player
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Grid Setter
     * This method changes the grid of the player.
     * @param grid The new grid of the player
     */
    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    /**
     * Score Setter
     * This method changes the score of the player.
     * @param score The new score of the player
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * PrivateObjectiveCard Setter
     * This method changes the PrivateObjectiveCard of the player
     * @param privateObjectiveCard the new PrivateObjectiveCard of the player
     */
    public void setPrivateObjectiveCard(PrivateObjectiveCard privateObjectiveCard) {
        try {
            this.privateObjectiveCard = privateObjectiveCard.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }


    /**
     * Player Clone
     * This method returns a copy of the Player the calls it
     * @return The Player with the same attributes
     * @throws CloneNotSupportedException From interface Cloneable
     */
    public Player clone() throws CloneNotSupportedException {
        return (Player) super.clone();
    }

}
