package it.polimi.se2018.networking.RMI;

import it.polimi.se2018.model.ChoseInputMove;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Server Interface
 * This is the interface that manages all server functions
 * @author Luca Massini
 * @author Federico Lichinchi
 * @author Davide Lorenzi (JDocs)
 */
public interface ServerInterface extends Remote {

    /**
     * Server Message Sender
     * This message sends messages to the server
     * @param inputMove is the player input move
     * @param client is the player that required the move
     * @throws RemoteException if is not possible to send a message
     */
      void sendMessageToServer(ChoseInputMove inputMove, ClientInterfaceRMI client) throws RemoteException;

      /**
       * Server RMI Message Sender
       * This message sends messages to the server by RMI
       * @param client is the player that required the move
       * @throws RemoteException if is not possible to send a message
       * @return the message from the server
       */
      ChoseInputMove getMessageFromServer(ClientInterfaceRMI client) throws RemoteException;

     /**
      * NickName Server Sender
      * This method sends a player's nickname to the server
      * @param nickName is the player' nickname
      * @param client is the player command interface
      * @return the required nickname
      * @throws RemoteException if is not possible to reach the server
      * @throws CloneNotSupportedException if is not possible to clone the nickname
      */
       boolean sendNickNameToServer(String nickName, ClientInterfaceRMI client) throws RemoteException, CloneNotSupportedException;

     /**
      * RMI Server Notifier
      * This method notifies the server by RMI
      * @param client is the client that ask the notification
      * @throws RemoteException if is not possible to reach the server
      */
       void notifyServer(ClientInterfaceRMI client) throws RemoteException;
}
