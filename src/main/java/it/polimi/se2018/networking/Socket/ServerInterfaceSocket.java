package it.polimi.se2018.networking.Socket;

import it.polimi.se2018.model.ChoseInputMove;


/**
 * ServerInterfaceSocket Interface
 * This interface is used to call all server functions
 * @author Luca Massini
 * @author Davide Lorenzi (JDocs)
 */
public interface ServerInterfaceSocket  {
    /**
     * Message Sender
     * This method sends messages to the server
     * @param inputMove is the move coming from
     * @param client is the player that has done the move
     */
    void sendMessageToServer(ChoseInputMove inputMove, ClientInterfaceSocket client);

    /**
     * Message Getter
     * This method gets messages from the server
     * @param client is the player that has to receive the message
     * @return the required message
     */
    Message getMessageFromServer(ClientInterfaceSocket client);

    /**
     * NickName Sender
     * @param nickName is the nickname chosen by player
     * @param client is the player
     * @throws CloneNotSupportedException if is not possible to clone the nickname
     */
    void sendNickNameToServer(String nickName, ClientInterfaceSocket client) throws CloneNotSupportedException;

    /**
     * Server Notifier
     * This method notifies the server of a change
     * @param client is the client
     */
    void notifyServer(ClientInterfaceSocket client);
}
