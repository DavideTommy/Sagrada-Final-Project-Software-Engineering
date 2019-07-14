package it.polimi.se2018.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.Random;
import java.util.logging.Logger;

/**
 * BoxOfSchemeCards Class
 * This class allocate all the SchemeCards reading them from JSON
 * @author Luca Massini
 * @author Davide Lorenzi (JDocs)
 */
public class BoxOfSchemeCards {
    private static BoxOfSchemeCards singleton = null;
    private SchemeCard[] arrayOfSchemeCards;
    private int[] arrayOfUsedGridsNumber;
    private int lastIndex;

    /**
     * Index Zeroing
     * This method sets LastIndex to zero and initializes an array of schemeCards of 24 elements
     * loaded from file
     */
    private BoxOfSchemeCards(){
        lastIndex = 0;
        try {
            Gson gson = new Gson();
            Type type=new TypeToken<SchemeCard[]>() {}.getType();
            arrayOfSchemeCards = gson.fromJson(new FileReader("src/main/java/resources/JSONs/SchemeCard.json"), type);
            arrayOfUsedGridsNumber = new int[arrayOfSchemeCards.length];
        }
        catch (FileNotFoundException e){
            Logger.getLogger("File not found in resources.");
        }
    }


    /**
     * Singleton Caller
     * If singleton attribute is null it goes to instance a new BoxOfSchemeCards
     * If singleton attribute isn't null it returns singleton variable
     * @return a new BoxOfSchemeCards (doing singleton=new BoxOfSchemeCards())
     * if it has not already been built once (singleton=null). If the object singleton has already
     * been constructed it returns singleton.
     *
     */
    public static BoxOfSchemeCards newBox(){
        if(singleton == null)
            singleton = new BoxOfSchemeCards();
        return singleton;
    }

    /**
     * SchemeCard Parser
     * This method if the SchemeCard that is requested is just in arrayOfSchemeCards
     * the method returns the SchemeCard in the array. Otherwise if the condition written
     * above is not respected it download the right SchemeCard from the json file "SchemeCard.json"
     * @param name is the name of the SchemeCard that is required
     * @return the SchemeCard whose name is passed like parameter in this method
     *
     */
    public SchemeCard request(String name){
            for (int i = 0; i < arrayOfSchemeCards.length; i++) {
                if (arrayOfSchemeCards[i].getSchemeCardName().equals(name)) {
                    arrayOfUsedGridsNumber[lastIndex] = i;
                    if(!arrayGridsNumbersContains(i))
                       lastIndex++;
                    return arrayOfSchemeCards[i];
                }
            }
            return null;
        }

    /**
     * Card Presence Checker
     * This method verifies if
     * @param number is the required number of a SchemeCard
     * @return true if a Scheme Card is present, false otherwise
     */
    private boolean numberPresent(int number) {
        for (int anArrayOfUsedGridsNumber : arrayOfUsedGridsNumber) {
            if (anArrayOfUsedGridsNumber == number) {
                return true;
            }
        }
        return false;
    }

    /**
     * Random Scheme Card Getter
     * This method returns an array of scheme card
     * @return an array of scheme cards
     */
    public SchemeCard randomSchemeCard(){
        Random r = new Random();
        int randomNumber;
        do {
            randomNumber = r.nextInt(arrayOfSchemeCards.length);
        } while(numberPresent(randomNumber) );
        arrayOfUsedGridsNumber[lastIndex] = randomNumber;
        lastIndex++;
        return arrayOfSchemeCards[randomNumber];
    }

    /**
     * Number Of SchemeCard Checker
     * This method checks the number of used scheme card
     * @param number is the number of the chosen SchemeCard
     * @return true if a scheme card has been already used, false otherwise
     */
    private boolean arrayGridsNumbersContains(int number){
        if(arrayOfUsedGridsNumber == null)
            return false;
        int length= arrayOfUsedGridsNumber.length;
        int i;
        for(i=0;i<length;i++){
            if(number == arrayOfUsedGridsNumber[i] )
                return true;
        }
        return false;
    }
}
