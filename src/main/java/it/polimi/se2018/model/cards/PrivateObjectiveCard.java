package it.polimi.se2018.model.cards;

import java.io.Serializable;

/**
 * Class PrivateObjectiveCard implements Card
 * This class is used to create the PrivateObjectiveCards
 * @author Davide Lorenzi
 * JSON PrivateObjectiveCard file written by Davide Lorenzi
 */

public class PrivateObjectiveCard implements Card, Cloneable, Serializable {

    private String cardName;
    private String cardDescription;
    private String diceColor;
    private int cardNumber;

    /**
     * PrivateObjectiveCard Class Constructor
     * @param cardName card cardName
     * @param cardDescription card cardDescription
     * @param diceColor PrivateObjective diceColor
     * @param cardNumber card number, used for random extractions
     */

    public PrivateObjectiveCard(String cardName, String cardDescription, int cardNumber, String diceColor) {
        this.cardName = cardName;
        this.cardDescription = cardDescription;
        this.diceColor = diceColor;
        this.cardNumber = cardNumber;
    }

    /**
     * Card Shower
     * This method prints all the features of the PrivateObjectiveCard
     */
    @Override
    public String showCard() {
        return "Name: "+ cardName + "\nDescription: " + cardDescription + "\nColour: "+ diceColor +"\n";
    }

    /**
     * Description Getter
     * This method is used to acquire the description of a PrivateObjectiveCard
     * @return the cardDescription of the PrivateObjectiveCard
     */
    @Override
    public String getCardDescription() {
        return cardDescription;
    }

    /**
     * CardName Getter
     * This method is used to acquire the name of a PrivateObjectiveCard
     * @return the cardName of the PrivateObjectiveCard
     */
    @Override
    public String getCardName() {
        return cardName;
    }

    /**
     * FavourMarkers Getter
     * This method is used to acquire the number of Favour Markers of a ToolCard
     * @return the number of FavourMarkers of the ToolCard
     */
    @Override
    public int getNumMarkers() {
        return 404;
    }

    /**
     * CardNumber Getter
     * This method is used to acquire the number of a PrivateObjectiveCard
     * @return the number of the PrivateObjectiveCard
     */
    @Override
    public int getCardNumber() {
        return cardNumber;
    }

    /**
     * Type Getter
     * This method is used to acquire the type of a Card
     * @return the type of the chosen PrivateObjectiveCard
     */
    @Override
    public String getCardType() {
        return "PrivateObjectiveCard";
    }

    /**
     * DieColor Getter
     * This method is used to acquire the color of the die of  PrivateObjectiveCard
     * @return the colour of the die to enable the correct score counter
     */
    @Override
    public String getColor() {
        return diceColor;
    }

    /**
     * Price Getter
     * This method is used to acquire the price of a ToolCard
     * @return the price of the chosen ToolCard
     */
    @Override
    public int getPrice() {
        return 404;
    }

    /**
     * Favour Marker Adder
     * This method virtually sets 1 or 2 Favour Markers on a ToolCard
     * @param number number of Favour Markers we need to add
     */
    @Override
    public void addFavMarkers(int number) {
        // This method is used only by a ToolCard class
    }

    /**
     * PublicVictoryPoints Getter
     * This method is used to acquire the number of victory points of a PublicObjectiveCard
     * @return the number of Victory Points of the chosen PublicObjectiveCard
     */
    @Override
    public int getPublicVictoryPoint() {
        return 404;
    }

    /**
     * Clone Card Method
     * This method creates a clone of a PrivateObjectiveCard
     * @return a new PrivateObjectiveCard build by the random chosen one read from the JSON file
     * @throws CloneNotSupportedException if is not possible to do the clone
     */
    public PrivateObjectiveCard clone() throws CloneNotSupportedException {
        return (PrivateObjectiveCard) super.clone();
    }
}
