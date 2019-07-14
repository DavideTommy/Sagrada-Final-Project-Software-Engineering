package it.polimi.se2018.networking.Socket;

import it.polimi.se2018.controller.MoveParser;
import it.polimi.se2018.model.ChoseInputMove;
import it.polimi.se2018.model.mv.VirtualView;
import it.polimi.se2018.networking.GameStarter;
import it.polimi.se2018.networking.RMI.ClientInterfaceRMI;
import it.polimi.se2018.networking.SenderRMI;
import it.polimi.se2018.networking.SenderSocket;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * ServerImplementation Class extends Thread implements ClientInterfaceSocket
 * This class the core ov the server code
 * @author Luca Massini
 * @author Davide Lorenzi (JDocs)
 */
public class ServerImplementationSocket implements ServerInterfaceSocket,SenderSocket {
    private static ServerImplementationSocket ist = null;
    private final it.polimi.se2018.networking.Server server;
    private ArrayList<ClientInterfaceSocket> clients;
    private ArrayList<VirtualView> virtualViews = new ArrayList<>();
    private ChoseInputMove move;
    private GameStarter starter;
    private boolean nameDifferent;

    public void setVirtualViews(ArrayList<VirtualView> virtualViews){
        this.virtualViews = virtualViews;
    }


    /**
     * Singleton Caller
     * This method creates a new instance of ServerSocket Implementation
     * @param server server of the match
     * @param starter trigger that calls the beginning of the match
     * @return the instance of the ServerImplementation
     */
    public static synchronized ServerImplementationSocket newServerImplementationSocket(it.polimi.se2018.networking.Server server,GameStarter starter) {
        if(ist == null) ist = new ServerImplementationSocket(server, starter);
        return ist;
    }

    /**
     * ServerImplementationSocket Constructor
     * @param server game server
     * @param starter trigger that calls the beginning of the match
     */
    private ServerImplementationSocket(it.polimi.se2018.networking.Server server, GameStarter starter){
        this.server = server;
        this.starter = starter;
        clients = server.getClients();
    }


    /**
     * MessageToServer SenderRMI
     * This method send the incoming move to the server
     * @param inputMove move of the player
     * @param client player tha have done the move
     */
    @Override
    public synchronized void sendMessageToServer(ChoseInputMove inputMove, ClientInterfaceSocket client) {
        move = inputMove;
        notifyServer(client);
    }

    /**
     * Message Getter
     * This method receive all messages coming from the server to the Client
     * @param client player that receive the message
     * @return a new message
     */
    @Override
    public synchronized Message getMessageFromServer(ClientInterfaceSocket client) {
       return new Message(null,move,nameDifferent);
    }

    /**
     * NickName SenderRMI
     * @param nickName player's NickName
     * @param client player's Client side
     */
    @Override
    public synchronized void sendNickNameToServer(String nickName, ClientInterfaceSocket client) {
               nameDifferent = starter.addNickNameSocket(nickName);
               move = MoveParser.newMoveParser().answerMessage("connectionReached");
               client.notifyToDownload();
    }

    /**
     * Move Chooser
     * This method catch the player's input move
     * @return the player's move
     */
    public synchronized ChoseInputMove getMessageFromServerOffline(){
        return move;
    }

    /**
     * OfflineMessage SenderRMI
     * This method is used to send message among server side components
     * @param inputMove player's move
     * @param client Client interface of the player that have done the move
     */
    @Override
    public synchronized void sendMessageToServerOffline(ChoseInputMove inputMove, ClientInterfaceSocket client){
        move = inputMove;
        client.notifyToDownload();
    }

    /**
     * Clients Getter
     * This method is used to get the ArrayList of players
     * @return the required ArrayList
     */
    public synchronized ArrayList<ClientInterfaceSocket> getClients() {
        return server.getClients();
    }

    /**
     * Server Notifier
     * This method is used to notify the server from the virtual view
     * @param client Client side of the current player
     */
    @Override
    public synchronized void notifyServer(ClientInterfaceSocket client) {
        for (VirtualView virtualView : virtualViews) {
            if (virtualView.getClientInterface().equals(client)) {
                virtualView.setMove(move);
            }
        }
    }

}
