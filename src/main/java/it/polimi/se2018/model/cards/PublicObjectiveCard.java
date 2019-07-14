package it.polimi.se2018.model.cards;

/**
 * Class PublicObjectiveCard implements Card
 * This class is used to create the PublicObjectiveCards
 * @author Davide Lorenzi
 * JSON PublicObjectiveCard file written by Davide Lorenzi
 */


public class PublicObjectiveCard implements Card, Cloneable{

     private String cardDescription;
     private String cardName;
     private int publicVictoryPoints;
     private int cardNumber;

    /**PublicObjectiveCard class constructor method
     * @param cardName Name of the card
     * @param cardDescription Description of the Card
     * @param publicVictoryPoints Number of Victory Points of the Card
     * @param cardNumber card number, used for random extractions
     */

    public PublicObjectiveCard(String cardName, String cardDescription, int cardNumber, int publicVictoryPoints) {
        this.cardName = cardName;
        this.cardDescription = cardDescription;
        this.publicVictoryPoints = publicVictoryPoints;
        this.cardNumber = cardNumber;
    }

    /**
     * Card Shower
     * This method prints all the features of the selected card
     */
    @Override
    public String showCard() {
        return "Card Name: " + cardName + "\nCard Description: " + cardDescription + "\nPublic Victory Points: " + publicVictoryPoints + "\n";
    }

    /**
     * Description Getter
     * This method is used to acquire the description of a PublicObjectiveCard
     * @return the cardDescription of the chosen card
     */
    @Override
    public String getCardDescription() {
        return cardDescription;
    }

    /**
     * CardName Getter
     * This method is used to acquire the name of a PublicObjectiveCard
     * @return the cardName of the chosen card
     */
    @Override
    public String getCardName() {
        return cardName;
    }

    /**
     * FavourMarkers Getter
     * This method is used to acquire the number of Favour Markers of a PublicObjectiveCard
     * @return the cardDescription of the chosen card
     */
    @Override
    public int getNumMarkers() {
        return 404;
    }

    /**
     * CardNumber Getter
     * This method is used to acquire the number of a PublicObjectiveCard
     * @return the number of the chosen card
     */
    @Override
    public int getCardNumber() {
        return cardNumber;
    }

    /**
     * Type Getter
     * This method is used to acquire the type of a Card
     * @return the type of the chosen PublicObjectiveCard
     */
    @Override
    public String getCardType() {
        return "PublicObjectiveCard";
    }

    /**
     * DieColor Getter
     * This method is used to acquire the color of the activation die of a PublicObjectiveCard
     * @return the colour of die die necessary to activate the correct computing score function
     */
    @Override
    public String getColor() {
        return "noColor";
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
     * @param number number of FavourMarkers to add
     */
    @Override
    public void addFavMarkers(int number) {
        // this method is used only by a ToolCard object
    }

    /**
     * PublicVictoryPoints Getter
     * This method is used to acquire the number of victory points of a PublicObjectiveCard
     * @return the number of Victory Points of the chosen PublicObjectiveCard
     */
    @Override
    public int getPublicVictoryPoint() {
        return publicVictoryPoints;
    }

    /**
     * Clone Card Method
     * This method creates a clone of a PublicObjectiveCard
     * @return a new PublicObjectiveCard build by the random chosen one read from the JSON file
     * @throws CloneNotSupportedException From interface Cloneable
     */
    public PublicObjectiveCard clone() throws CloneNotSupportedException {
        return (PublicObjectiveCard) super.clone();
    }
}