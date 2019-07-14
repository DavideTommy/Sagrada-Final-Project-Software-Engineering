package it.polimi.se2018.model.cards;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Random;

/**
 * Card Parser
 * This class parses from JSON files all Cards (of any type) into a proper ArrayList.
 * So using require methods is possible to catch the required one from array and use it.
 * @author Davide Lorenzi
 * @author Federico Lichinchi
 */
public class CardParser  {
    private static CardParser ist = null;

    private ArrayList<ToolCard> toolCard;
    private ArrayList<PublicObjectiveCard> publicObjectiveCards;
    private ArrayList<PrivateObjectiveCard> privateObjectiveCards;

    /**
     * CardParser Constructor
     * The core of the parser: extract from any JSON all the Cards and set them on an ArrayList
     * @throws FileNotFoundException if doesn't find JSON source files
     */
    private CardParser() throws FileNotFoundException {
        new Object();
        Gson gson = new Gson();

        Type publicObjective = new TypeToken<ArrayList<PublicObjectiveCard>>() {}.getType();
        Type privateObjective = new TypeToken<ArrayList<PrivateObjectiveCard>>() {}.getType();
        Type tools = new TypeToken<ArrayList<ToolCard>>() {}.getType();

        publicObjectiveCards = gson.fromJson(new FileReader("src/main/java/resources/JSONs/PublicObjectiveCard.json"), publicObjective);
        privateObjectiveCards = gson.fromJson(new FileReader("src/main/java/resources/JSONs/PrivateObjectiveCard.json"), privateObjective);
        toolCard = gson.fromJson(new FileReader("src/main/java/resources/JSONs/ToolCard.json"), tools);

    }

    /**
     * PublicCard Getter
     * This method returns the required PublicObjectiveCard from the imported ArrayList
     * @param cardName name of the required Card
     * @return required PublicObjectiveCard
     */
    public PublicObjectiveCard requirePublicObjectiveCard (String cardName) {
        for (PublicObjectiveCard publicObjectiveCard : publicObjectiveCards) {
            if (publicObjectiveCard.getCardName().equals(cardName)) {
                return publicObjectiveCard;
            }
        }
        return null;
    }

    /**
     * ToolCard Getter
     * This method returns the required ToolCard from the imported ArrayList
     * @param cardName name of the required Card
     * @return required ToolCard
     */
    public ToolCard requireToolCard(String cardName) {
        for (ToolCard aToolCard : toolCard) {
            if (aToolCard.getCardName().equals(cardName)) {
                return aToolCard;
            }
        }
        return null;
    }

    /**
     * PrivateCard Getter
     * This method returns the required PrivateObjectiveCard from the imported ArrayList
     * @param cardName name of the required Card
     * @return required PrivateObjectiveCard
     */
    public PrivateObjectiveCard requirePrivateObjectiveCard (String cardName) {
        for (PrivateObjectiveCard privateObjectiveCard : privateObjectiveCards) {
            if (privateObjectiveCard.getCardName().equals(cardName)) {
                return privateObjectiveCard;
            }
        }
        return null;
    }

    /**
     * Singleton Creator
     * This method create a new instance of the class if has never been done before
     * @return CardParser
     * @throws FileNotFoundException if is not possible to reach the file to parse
     */
    public static CardParser newCardParser() throws FileNotFoundException {
        if (ist == null) {
            ist = new CardParser();
        }
        return ist;
    }

    /**
     * ToolCard Randomizer
     * This method is used to get random ToolCards
     * @return a random ToolCard
     * @throws CloneNotSupportedException if is not possible to create a new ToolCard
     */
    public ToolCard getRandomToolCard() throws CloneNotSupportedException {
        Random r = new Random();
        int randomNumber = r.nextInt(toolCard.size());
        return toolCard.get(randomNumber).clone();
    }

    /**
     * PublicObjectiveCard Randomizer
     * This method is used to get random PublicObjectiveCard
     * @return a random PublicObjectiveCard
     * @throws CloneNotSupportedException if is not possible to create a new PublicObjectiveCard
     */
    public PublicObjectiveCard getRandomPublicObjectiveCard() throws CloneNotSupportedException {
        Random r = new Random();
        int randomNumber = r.nextInt(publicObjectiveCards.size());
        return publicObjectiveCards.get(randomNumber).clone();
    }

    /**
     * PrivateObjectiveCard Randomizer
     * This method is used to get random PrivateObjectiveCard
     * @return a random PrivateObjectiveCard
     * @throws CloneNotSupportedException if is not possible to create a new PrivateObjectiveCard
     */
    public PrivateObjectiveCard getRandomPrivateObjectiveCard() throws CloneNotSupportedException {
        Random r = new Random();
        int randomNumber = r.nextInt(privateObjectiveCards.size());
        return privateObjectiveCards.get(randomNumber).clone();
    }

}
