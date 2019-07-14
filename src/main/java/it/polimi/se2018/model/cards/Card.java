package it.polimi.se2018.model.cards;

/**
 * Interface Card
 * This interfaces manages the creation of all types of card that are used in the game
 * @author Davide Lorenzi
 */

public interface Card extends Cloneable {

    /**
     * Card Shower
     * This method prints all the features of the selected Card
     * @return general description of the card
     */
    String showCard();

    /**
     * Description Getter
     * This method is used to acquire the description of a Card
     * @return the cardDescription of the Card
     */
    String getCardDescription();

    /**
     * CardName Getter
     * This method is used to acquire the name of a Card
     * @return the cardName
     */
    String getCardName();

    /**
     * FavourMarkers Getter
     * This method is used to acquire the number of Favour Markers of a ToolCard
     * @return the number of FavourMarkers of the ToolCard
     */
    int getNumMarkers();

    /**
     * CardNumber Getter
     * This method is used to acquire the number of a Card
     * @return the number of the Card
     */
    int getCardNumber();

    /**
     * Type Getter
     * This method is used to acquire the type of a Card
     * @return the type of the chosen Card
     */
    String getCardType();

    /**
     * DieColor Getter
     * This method is used to acquire the color of the die of PrivateObjectiveCard or ToolCard
     * @return the colour of the die to enable the correct score counter or to enable the chosen
     * ToolCard in SinglePlayer mode
     */
    String getColor();

    /**
     * Price Getter
     * This method is used to acquire the price of a ToolCard
     * @return the price of the chosen ToolCard
     */
    int getPrice();

    /**
     * Favour Marker Adder
     * This Method virtually sets 1 or 2 Favour Markers on a ToolCard
     * @param favMarkersNumber Number of favourite markers to add
     */
    void addFavMarkers(int favMarkersNumber);

    /**
     * PublicVictoryPoints Getter
     * This method is used to acquire the number of victory points of a PublicObjectiveCard
     * @return the number of Victory Points of the chosen PublicObjectiveCard
     */
    int getPublicVictoryPoint();

    /**
     * Clone Card Method
     * This method creates a clone of a Card
     * @return a new ToolCard build by the random chosen one read from the JSON file
     * @throws CloneNotSupportedException if is impossible to create the clone
     */
    Card clone() throws CloneNotSupportedException;

}
