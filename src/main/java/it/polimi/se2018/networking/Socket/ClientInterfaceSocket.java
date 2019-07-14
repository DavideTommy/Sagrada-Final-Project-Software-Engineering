package it.polimi.se2018.networking.Socket;


import it.polimi.se2018.networking.RMI.ClientInterfaceRMI;

/**
 * ClientInterfaceSocket Interface
 * This interface calls the notify downloader
 * @author Luca Massini
 */
public interface ClientInterfaceSocket extends ClientInterfaceRMI {
    /**
     * Download Notifier
     * This method notifies the model to download the status
     */
    void notifyToDownload();
}
