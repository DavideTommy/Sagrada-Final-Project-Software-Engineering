package it.polimi.se2018.networking.Socket;

import it.polimi.se2018.model.ChoseInputMove;

import java.io.Serializable;

/**
 * Message Class implements Serializable
 * This class manages the move message creation. This type of message is used in the socket connection.
 * @author Luca Massini
 * @author Davide Lorenzi (JDocs)
 */
public class Message implements Serializable {
    private String nickName;
    private ChoseInputMove move;
    private Boolean isNameDifferent;

    /**
     * Message Constructor
     * This method initialize the player's nickName and his move
     * @param nickName player's nickName
     * @param move player's move
     * @param isNameDifferent is the boolean that verifies if a NickName has been already used
     */
    public Message(String nickName, ChoseInputMove move,Boolean isNameDifferent){
       this.nickName = nickName;
       this.move = move;
       this.isNameDifferent = isNameDifferent;
    }

    public boolean getNameDifferent(){
        return isNameDifferent;
    }

    /**
     * NickName Getter
     * This method gets the required player's nickName
     * @return player's nickName
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * Move Getter
     * This method gets the required player's move
     * @return player's move
     */
    public ChoseInputMove getMove() {
        return move;
    }
}
