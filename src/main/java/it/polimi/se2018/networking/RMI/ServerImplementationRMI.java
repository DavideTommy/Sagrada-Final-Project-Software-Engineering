package it.polimi.se2018.networking.RMI;

import it.polimi.se2018.controller.MoveParser;
import it.polimi.se2018.model.ChoseInputMove;
import it.polimi.se2018.model.mv.VirtualView;
import it.polimi.se2018.networking.GameStarter;
import it.polimi.se2018.networking.SenderRMI;

import java.io.IOException;
import java.rmi.ConnectException;
import java.rmi.RemoteException;
import java.rmi.UnmarshalException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * Server Implementation extends UnicastRemoteObject implements ServerInterface
 * This class implements all server functions
 * @author Luca Massini (all the code except the notifyServer method and the JDocs)
 * @author Federico Lichinchi (notifyServer method)
 * @author Davide Lorenzi (JDocs)
 */
public class ServerImplementationRMI extends UnicastRemoteObject implements ServerInterface,SenderRMI {

    private static ServerImplementationRMI ist = null;
    private ChoseInputMove moveToBeSentToClient;
    private ArrayList<ClientInterfaceRMI> clientsInterfaces = new ArrayList<>();
    private GameStarter starter;
    private ArrayList<VirtualView> virtualViews;
    private ArrayList<ClientInterfaceRMI> disconnectedClients = new ArrayList<>();

    public void setVirtualViews(ArrayList<VirtualView> virtualViews) {
        this.virtualViews = virtualViews;
    }

    /**
     * Singleton Caller
     * This method instance a new ServerImplementationRMI if it hasn't already been done
     * @param starter is the trigger coming from the game starter that is gonna initialize the match
     * @return a new ServerImplementationRMI instance
     */
    public static ServerImplementationRMI newServerImplementation(GameStarter starter) {
        if (ist == null) {
            try {
                ist = new ServerImplementationRMI(starter);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ist;
    }

    /**
     * ClientInterfaceRMI Adder
     * This method adds a Client interface
     * @param client Client just connected to the server
     */
    private void addClientInterface(ClientInterfaceRMI client){
        clientsInterfaces.add(client);
    }


    /**
     * ServerImplementationRMI Constructor
     * This method sets the serverImplementation
     * @throws IOException if there's a wrong port input
     */
    private ServerImplementationRMI(GameStarter starter) throws IOException {
        super(0);
        this.starter=starter;
    }

    /**
     * Message SenderRMI
     * This method sends messages to the server
     * @param inputMove move that must be sent to the server
     * @param client Client that sent the move
     */
    @Override
    public void sendMessageToServer(ChoseInputMove inputMove,ClientInterfaceRMI client) {
        try {
            this.moveToBeSentToClient = inputMove.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Message Getter
     * This method is used to receive messages from the server
     * @param client target of the message
     * @return move to be sent to the Client
     */
    @Override
    public ChoseInputMove getMessageFromServer(ClientInterfaceRMI client) {
        return moveToBeSentToClient;
    }

    /**
     * NickName SenderRMI
     * This method sends all set NickNames to the server
     * @param nickName string of nicknames
     * @param client player's interface
     * @throws CloneNotSupportedException if is not possible to launch a new match
     * @throws IllegalArgumentException if is passed an improper argument
     */
    @Override
    public boolean sendNickNameToServer(String nickName, ClientInterfaceRMI client) throws CloneNotSupportedException {
        if(starter.addNickNameRmi(nickName)) {
            addClientInterface(client);
            sendMessageToServer(MoveParser.newMoveParser().answerMessage("connectionReached"),client);
            return true;
        }
        return false;
    }

    /**
     * OfflineMessage SenderRMI
     * This method is used to send message among server classes
     * @param inputMove move coming from che player
     * @param client player that is asking the move
     */
    @Override
    public void sendMessageToServerOffline(ChoseInputMove inputMove, ClientInterfaceRMI client){
        moveToBeSentToClient=inputMove;
        try {
            client.notifyToDownload();
        }
        catch(ConnectException | UnmarshalException e){
            System.out.println("Client is disconnected");
            moveToBeSentToClient = null;
            disconnectedClients.add(client);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /**
     * OfflineMessage Receiver
     * This method is used to receive message among server classes
     * @return the move to be sent
     */
    public ChoseInputMove getMessageFromServerOffline(){
        return moveToBeSentToClient;
    }

    /**
     * Server Notifier
     * This method is used to notify a change into the server status
     * @param client the Client that is asking the notification
     */
    public void notifyServer(ClientInterfaceRMI client) {
        for (VirtualView virtualView : virtualViews) {
            if (virtualView.getClientInterface().equals(client)) {
                virtualView.setMove(moveToBeSentToClient);
            }
        }
    }

    /**
     * RMIInstance Getter
     * This method gets the instance of the RMI implementation
     * @return the RMI implementation instance
     */
    public static ServerImplementationRMI getIst() {
        return ist;
    }

    /**
     * RMIClient Getter
     * This method gets the RMI Client interface
     * @return Client interface
     */
    public ArrayList<ClientInterfaceRMI> getClientInterfaceRMI() {
        return clientsInterfaces;
    }


}
