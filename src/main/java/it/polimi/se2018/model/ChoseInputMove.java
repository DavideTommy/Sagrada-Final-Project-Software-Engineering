package it.polimi.se2018.model;

import it.polimi.se2018.model.parsers.ParserManager;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Chose Input Move Class implements InputMove, Serializable, Cloneable
 * This class is used to manage tall players'input move
 * @author Federico Lichinchi
 * @author Davide Lorenzi (JDocs)
 */

public class ChoseInputMove implements InputMove, Serializable, Cloneable {

    private String message;
    private Object element;
    private String playerAddress;
    private ParserManager parserManager;

    /**
     * ChoseInputMove Constructor
     * This method build the InputMove Constructor
     * @param message String containing the message of the move
     * @param element key object of the modification
     * @param playerAddress IP address of the player that is asking the move
     */
    public ChoseInputMove(String message, Object element, String playerAddress) {
        this.message = message;
        this.element = element;
        this.playerAddress = playerAddress;
        parserManager = ParserManager.newParserManager();
    }

    /**
     * Message Getter
     * This method returns the required message
     * @return the required message
     */
    @Override
    public String getMessage() {
        return message;
    }

    /**
     * Element Getter
     * This method returns the required element
     * @return the required element
     */
    @Override
    public Object getElement() {
        return element;
    }

    /**
     * PlayerAddress Getter
     * This method returns the required PlayerAddress
     * @return the required PlayerAddress
     */
    public String getPlayerAddress() {
        return playerAddress;
    }

    /**
     * Element Setter
     * This method configure a new element
     * @param element the element in string form
     */
    public void setElement(Object element) {
        this.element = element;
    }

    /**
     * PlayerAddress Setter
     * This method sets the IP address of a player
     * @param address IP address of the player
     */
    public void setPlayerAddress(String address) {
        this.playerAddress = address;
    }

    /**
     * ElementToString Converter
     * This method is used to print an element(ArrayList, String, Grid, Card)
     * @return the correct string of the element
     */
    public String toStringElement() {
        if(element instanceof ArrayList) {
            ArrayList<Object> arrayList = (ArrayList<Object>) element;
            StringBuilder builder = new StringBuilder();
            for(int i = 0; i < arrayList.size(); i++) {
                builder.append((i+1)+" - "+parserManager.toStringRequiredObject(arrayList.get(i)) + "\n");
            }
            return builder.toString();
        }
        else if(element instanceof String)
            return element.toString();
        else
            return parserManager.toStringRequiredObject(element);

    }

    /**
     * Move Clone
     * This method is used to clone
     * @return the chosen input move
     * @throws CloneNotSupportedException if is impossible to clone the object
     */
    public ChoseInputMove clone() throws CloneNotSupportedException {
        return (ChoseInputMove) super.clone();
    }
}