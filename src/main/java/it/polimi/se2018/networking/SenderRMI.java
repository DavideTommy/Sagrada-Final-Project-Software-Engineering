package it.polimi.se2018.networking;

import it.polimi.se2018.model.ChoseInputMove;
import it.polimi.se2018.networking.RMI.ClientInterfaceRMI;

/**
 * Sender RMI Interface
 * This interface manages the message sending using the RMI server type connection
 * @author Luca Massini
 * @author Davide Lorenzi (JDocs)
 */
public interface SenderRMI {
    /**
     * Offline Message Sender
     * This method sends moves offline to the server using RMI
     * @param inputMove is the player move
     * @param client is the player that has done the move
     */
    void sendMessageToServerOffline(ChoseInputMove inputMove, ClientInterfaceRMI client);
}
