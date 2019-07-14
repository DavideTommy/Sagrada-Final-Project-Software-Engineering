package it.polimi.se2018.model.cards;

import java.io.Serializable;

/**
 * Class ToolCard implements Card
 * This class is used to create the ToolCards
 * @author Davide Lorenzi
 * JSON ToolCard file written by Davide Lorenzi
 */

public class ToolCard implements Serializable, Card, Cloneable {

    private String name;
    private String description;
    private int price;
    private int cardNumber;
    private int numMarkers;
    private String color;

    /**
     * Constructor method of the ToolCard class
     * This method is used to build a ToolCard Object
     * Constructor method of the ToolCard class
     * This method initialize the ToolCard object
     * @param cardName    cardName of the ToolCard
     * @param numMarkers  number of Favour Markers put on a ToolCard
     * @param price       Favour Markers needed to buy a ToolCard
     * @param description Contains the cardDescription of the effective action of the card
     * @param cardNumber  Is the number of the card, for a faster identification and use
     * @param dieColor    Is the color of the die used to activate the card
     */
    public ToolCard(String cardName, String description, int cardNumber, int numMarkers, int price, String dieColor) {
        this.name = cardName;
        this.price = price;
        this.description = description;
        this.cardNumber = cardNumber;
        this.numMarkers = numMarkers;
        this.color = dieColor;
    }

    /**
     * Card Shower
     * This method prints all the features of the selected card
     */
    @Override
    public String showCard() {
        return "Name: " + name + "\nCard Description: " + description + "\nToolCardNumber: " + cardNumber + "\n" +
                "Price: " + price + "\n";
    }

    /**
     * Description Getter
     * This method is used to acquire the description of a ToolCard
     * @return the cardDescription of the chosen card
     */
    @Override
    public String getCardDescription() {
        return description;
    }

    /**
     * CardName Getter
     * This method is used to acquire the name of a ToolCard
     * @return the cardName of the chosen card
     */
    @Override
    public String getCardName() {
        return name;
    }

    /**
     * FavourMarkers Getter
     * This method is used to acquire the number of Favour Markers of a ToolCard
     * @return the cardDescription of the chosen card
     */
    @Override
    public int getNumMarkers() {
        return numMarkers;
    }

    /**
     * CardNumber Getter
     * This method is used to acquire the number of a ToolCard
     * @return the number of the chosen card
     */
    @Override
    public int getCardNumber() {
        return cardNumber;
    }

    /**
     * Type Getter
     * This method is used to acquire the type of a Card
     * @return the type of the chosen ToolCard
     */
    @Override
    public String getCardType() {
        return "ToolCard";
    }

    /**
     * DieColor Getter
     * This method is used to acquire the color of the activation die of a ToolCard
     * @return the colour of the die necessary to activate of the chosen ToolCard
     */
    @Override
    public String getColor() {
        return color;
    }

    /**
     * Price Getter
     * This method is used to acquire the price of a ToolCard
     * @return the price of the chosen ToolCard
     */
    @Override
    public int getPrice() {
        if(numMarkers >= 0)
            price = 2;
        else
            price = 1;
        return price;
    }

    /**
     * Favour Marker Adder
     * This method virtually sets 1 or 2 Favour Markers on a ToolCard
     * @param number number of FavourMarkers to add
     */
    @Override
    public void addFavMarkers(int number) {
        numMarkers += number;
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
     * This method creates a clone of a ToolCard
     * @return a new ToolCard build by the random chosen one read from the JSON file
     * @throws CloneNotSupportedException If is not possible to do the clone
     */
    public ToolCard clone() throws CloneNotSupportedException {
        return (ToolCard) super.clone();
    }
}