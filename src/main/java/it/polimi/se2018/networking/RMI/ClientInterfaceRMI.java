package it.polimi.se2018.networking.RMI;

import it.polimi.se2018.model.ChoseInputMove;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * ClientRMI Interface extends Remote
 * This interface is used to notify the download of a new move
 * @author Luca Massini
 * @author Davide Lorenzi (JDocs)
 */
public interface ClientInterfaceRMI extends Remote {

    /**
     * Download Notifier
     * This method notifies the download of information
     * @throws RemoteException if isn't possible to download
     */
    void notifyToDownload() throws RemoteException;

    /**
     * input Move Sender
     * This method is used to send input moves to the server
     * @param inputMove is the move of the player
     * @throws RemoteException if is not possible to contact the server
     */
    void sendInputMove(ChoseInputMove inputMove) throws RemoteException;
}
