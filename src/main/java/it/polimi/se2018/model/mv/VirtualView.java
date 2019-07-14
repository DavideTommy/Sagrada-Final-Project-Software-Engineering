package it.polimi.se2018.model.mv;


import it.polimi.se2018.networking.RMI.ClientInterfaceRMI;
import it.polimi.se2018.model.ChoseInputMove;
import it.polimi.se2018.networking.SenderRMI;
import it.polimi.se2018.networking.SenderSocket;
import it.polimi.se2018.networking.Socket.ClientInterfaceSocket;

import java.util.Observable;
import java.util.Observer;

/**
 * Virtual View Class
 * This class manages the virtual view on the server side
 * @author Federico Lichinchi
 * @author Davide Lorenzi (JDocs)
 */
public class VirtualView extends Observable implements Observer {

    private Model modelCopy;
    private volatile ChoseInputMove move;
    private ClientInterfaceRMI interfaceRMI;
    private ClientInterfaceSocket interfaceSocket;
    private SenderRMI senderRMI;
    private SenderSocket senderSocket;
    private String name;

    /**
     * Virtual View Updater
     * This method update the Virtual View when there is a change on the Model status
     * @param o is the caller of the change status
     * @param arg is the message containing the change status
     */
    @Override
    public  void update(Observable o, Object arg) {
        try {
            move = ((Model) o).getMove().clone();
            modelCopy = ((Model) o).clone();
            System.out.print("message da inviare dalla virtualview: ");
            System.out.println(move.getMessage());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        if(interfaceRMI!=null)
            sendOfflineMoveToServer(move, interfaceRMI);
        else
            sendOfflineMoveToServer(move, interfaceSocket);
    }

    /**
     * Move Getter
     * This method gets the move that changed the Model Status
     * @return a copy of the model updated
     */
    public ChoseInputMove getMove() {
        return move;
    }

    /**
     * SetMove Manager
     * This method manages the correct processing of a move
     * @param choseInputMove is the incoming move
     */
    public void setMove(ChoseInputMove choseInputMove) {
        move = choseInputMove;
        notifyObservers();
        setChanged();
    }

    /**
     * Next Turn Notifier
     * This method notifies the name of the next player
     * @return the name of the player that has to play after the actual one
     */
    public String getTurn() {
        return "Now is " + modelCopy.getMatch().getRoundTrack().getPlayers().get(modelCopy.getMatch().getRoundTrack().getPlayerTurn()).getPlayerName() + "'s turn";
    }

    /**
     * ClientInterfaceRMI Setter
     * This method is used to set the ClientInterfaceRMI of a player
     * @param clientInterfaceRMI interface of the player
     */
    public void setClientRMI(ClientInterfaceRMI clientInterfaceRMI) {
        this.interfaceRMI = clientInterfaceRMI;
    }

    /**
     * ClientInterfaceSocket Setter
     * This method is used to set the ClientInterfaceSocket of a player
     * @param client socket interface of the player
     */
    public void setClientSocket(ClientInterfaceSocket client){
        this.interfaceSocket = client;
    }

    /**
     * ServerImplementationRMI Setter
     * This method sets a new server implementation
     * @param sender is the server implementation
     */
    public void setSenderRmi(SenderRMI sender) {
        this.senderRMI = sender;
    }

    /**
     * ServerImplementationSocket Setter
     * This method sets a new Socket server implementation
     * @param sender is the Socket server implementation
     */
    public void setSenderSocket(SenderSocket sender){
        this.senderSocket = sender;
    }

    /**
     * Offline Communicator
     * This method is used to send messages among server's control units passing by RMI connection
     * @param output is the input move to send
     * @param client is the Client from which is coming the message
     */
    private void sendOfflineMoveToServer(ChoseInputMove output, ClientInterfaceRMI client) {
        senderRMI.sendMessageToServerOffline(output,client);
    }

    /**
     * Offline Communicator
     * This method is used to send messages among server's control units passing by Socket connection
     * @param output is the input move to send
     * @param client is the Client from which is coming the message
     */
    private void sendOfflineMoveToServer(ChoseInputMove output, ClientInterfaceSocket client){
        senderSocket.sendMessageToServerOffline(output,client);
    }

    /**
     * ClientInterfaceRMI Getter
     * This method returns the required Client interface
     * @return the Client interface
     */
    public ClientInterfaceRMI getClientInterface() {
        if(interfaceRMI != null)
            return interfaceRMI;
        return interfaceSocket;
    }

    /**
     * Name Setter
     * This class is used to set the name of the virtual view
     * @param name is the name of the virtual view
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Name Getter
     * This method is used to obtain the name of virtual view
     * @return the name of the virtual view
     */
    public String getName() {
        return name;
    }

}
