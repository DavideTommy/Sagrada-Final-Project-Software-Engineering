package it.polimi.se2018.model.parsers;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * ParseManager
 * This class is used to manage all parsers
 * @author Federico Lichinchi
 * @author Davide Lorenzi (JDocs)
 */
public class ParserManager implements Serializable {

    private static ParserManager singleton = null;
    private ArrayList<ObjectParser> objectParsers;

    /**
     * ParserManager Creator
     * This method creates a new instance of the ParseManager
     * @return the status of instance pf the ParserManager
     */
    public static ParserManager newParserManager(){
        if(singleton == null)
            singleton = new ParserManager();
        return singleton;
    }

    /**
     * ParseManager Constructor
     * This method builds the Parse Manager
     */
    public ParserManager() {
        objectParsers = new ArrayList<>();
        objectParsers.add(new GridObjectParser());
        objectParsers.add(new ToolCardObjectParser());
        objectParsers.add(new PrivateObjectiveCardObjectParser());
        objectParsers.add(new PublicObjectiveCardObjectParser());
        objectParsers.add(new DieObjectParser());
        objectParsers.add(new PlayerObjectParser());
    }

    /**
     * String parser
     * This method returns the string that describe a specific object
     * @param object is the object to parse in string
     * @return the string that describes the object
     */
    public String toStringRequiredObject(Object object) {
        for(ObjectParser parser : objectParsers) {
            if(parser.getObjectName().equals(object.getClass().getSimpleName()))
                return parser.toStringObject(object);
        }
        return null;
    }

}
