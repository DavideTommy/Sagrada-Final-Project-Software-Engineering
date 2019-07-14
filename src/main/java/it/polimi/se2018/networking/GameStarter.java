package it.polimi.se2018.networking;

import it.polimi.se2018.networking.RMI.ClientInterfaceRMI;
import it.polimi.se2018.networking.RMI.ServerImplementationRMI;
import it.polimi.se2018.networking.Socket.ClientInterfaceSocket;
import it.polimi.se2018.networking.Socket.ServerImplementationSocket;
import it.polimi.se2018.controller.Controller;
import it.polimi.se2018.model.mv.VirtualView;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Iterator;


/**
 * GameStarter Class
 * This method initializes all parameters necessaries to create a new match
 * @author Luca Massini
 * @author Davide Lorenzi (JDocs)
 */
public class GameStarter {
    private ArrayList<String> nickNamesSocket = new ArrayList<>();
    private ArrayList<String> nickNamesRmi = new ArrayList<>();
    private int timeout;
    private ServerImplementationSocket serverSocket;
    private ServerImplementationRMI serverRMI;
    private TimeoutExpired time = new TimeoutExpired();
    private ArrayList<ClientInterfaceSocket> clientsSocket;
    private ArrayList<ClientInterfaceRMI> clientsRMI;


    /**
     * Instance Getter
     * This method returns the status of the GameStarter
     * @return the actual GameStarter Instance
     */
    public GameStarter getIst(){
        return ist;
    }

    private static GameStarter ist = null;

    /**
     * Singleton Caller
     * This method instance a nev GameStarter instance if it hasn't already been done
     * @param server server that will manage the entire match
     * @return the actual status of the  GameStarter just after the initialization
     */
    public static GameStarter newGameStarter(Server server) {
        if(ist == null) ist = new GameStarter(server);
        return ist;
    }

    /**
     * ServerRMI Getter
     * This method is used to create a RMI type connection between player and server
     * @return the required RMI server
     */
    public ServerImplementationRMI getServerRMI() {
        return serverRMI;
    }

    /**
     * ServerSocket Getter
     * This method is used to create a ServerSocket type connection between player and server
     * @return the required ServerSocket server
     */
    public ServerImplementationSocket getServerSocket() {
        return serverSocket;
    }

    /**
     * GameStarter Constructor
     * This method initialize all needed connections
     * @param server server that will manage the match
     */
    private GameStarter(Server server){
            this.serverSocket = ServerImplementationSocket.newServerImplementationSocket(server,this);
            clientsSocket = serverSocket.getClients();
            this.serverRMI = ServerImplementationRMI.newServerImplementation(this);
            clientsRMI = serverRMI.getClientInterfaceRMI();
    }


    /**
     * DifferentNickname Checker
     * This method verifies that players have different nickNames
     * @param nickName chosen nickname
     * @return true if nickname is not used by one other player and false otherwise
     */
    private boolean isNickNameDifferentFromTheOthers(String nickName) {
        Iterator<String> nickNamesIteratorSocket = nickNamesSocket.iterator();
        for (String aNickNamesRmi : nickNamesRmi) {
            if (aNickNamesRmi.equalsIgnoreCase(nickName))
                return false;
        }
        while(nickNamesIteratorSocket.hasNext()){
            if(nickNamesIteratorSocket.next().equalsIgnoreCase(nickName))
                return false;
        }
        return true;
    }

    /**
     * Match Beginner
     * This method controls the game starting (checking the required number of players and the timer countdown)
     */
    private void controllerOfGameStarting(){
        if (nickNamesSocket.size() + nickNamesRmi.size() == 2)
            time.start();
        synchronized (this) {
            if(isAbleNewControllerCreation()) {
                try {
                    time.interrupt();
                    startGame(clientsRMI, serverRMI);
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Controller Initializer
     * This method starts the creation of a new controller for the game
     * @return true if there are 4 players connected
     */
    private synchronized boolean isAbleNewControllerCreation(){
        return nickNamesRmi.size()+nickNamesSocket.size() == 4;
    }

    /**
     * Client Updater
     * This method is used to update the ArrayList of clients
     */
    private void updateClients(){
        clientsRMI = serverRMI.getClientInterfaceRMI();
        clientsSocket = serverSocket.getClients();
    }

    /**
     * NickName Controller
     * This method verifies te population of the array of nicknames to check the beginning of the match
     * @param nickName is the new player's nickname
     * @param nickNames is the ArrayList of players
     * @return true if 4 players joined the match, false otherwise
     */
    private synchronized boolean controllerNicknames(String nickName, ArrayList<String> nickNames){
        if (isNickNameDifferentFromTheOthers(nickName)) {
            nickNames.add(nickName);
            controllerOfGameStarting();
            return true;
        }
        else
            return false;
    }

    /**
     * ServerNickName Adder
     * This method add a new NickName of a player that wants to use a Socket connection
     * @param nickName the nickname that must be added to the ArrayList of NickNames
     * @return all nicknames passed by the controller on Socket connection
     */
    public synchronized boolean addNickNameSocket(String nickName) {
        updateClients();
        return controllerNicknames(nickName,nickNamesSocket);
    }
    /**
     * RMINickName Adder
     * This method add a new nickName of a player that use an RMI connection
     * @param nickName the nickname that must be added to the ArrayList of NickNames
     * @return all nicknames passed by the controller on rmi connection
     */
    public synchronized  boolean addNickNameRmi(String nickName) {
        updateClients();
        return controllerNicknames(nickName,nickNamesRmi);
    }


    /**
     * Timeout Setter
     * This method sets the timeout before the match begins
     * @param timeout chosen timeout before the start of the match
     */
    public void setTimeout(int timeout){
        this.timeout=timeout;
    }

    /**
     * MatchStarter Class
     * This class manages the beginning of a new game
     * @throws CloneNotSupportedException if is impossible to create a new match by clone
     */

    private synchronized void startGame(ArrayList<ClientInterfaceRMI> clientsInterfaces, ServerImplementationRMI server) throws CloneNotSupportedException {
        ArrayList<VirtualView> virtualViewsRmi = new ArrayList<>(nickNamesRmi.size());
        ArrayList<VirtualView> virtualViewsSocket = new ArrayList<>(nickNamesSocket.size());
        ArrayList<VirtualView> totalVirtualViews = new ArrayList<>(nickNamesSocket.size()+nickNamesRmi.size());
        serverSocket.setVirtualViews(virtualViewsSocket);
        serverRMI.setVirtualViews(virtualViewsRmi);
        int length = nickNamesRmi.size();
        for(int i = 0; i < length; i++) {
            virtualViewsRmi.add(new VirtualView());
            virtualViewsRmi.get(i).setClientRMI(clientsInterfaces.get(i));
            virtualViewsRmi.get(i).setSenderRmi(server);
            virtualViewsRmi.get(i).setName(nickNamesRmi.get(i));
            totalVirtualViews.add(virtualViewsRmi.get(i));
        }
        length = nickNamesSocket.size();
        for(int i =0; i<length;i++){
            virtualViewsSocket.add(new VirtualView());
            virtualViewsSocket.get(i).setClientSocket(clientsSocket.get(i));
            virtualViewsSocket.get(i).setSenderSocket(serverSocket);
            virtualViewsSocket.get(i).setName(nickNamesSocket.get(i));
            totalVirtualViews.add(virtualViewsSocket.get(i));
        }
        System.out.println("Nomi: ");
        for(VirtualView virtualView: totalVirtualViews){
            System.out.println(virtualView.getName());
        }
        Controller matchController = new Controller(nickNamesRmi, totalVirtualViews);
        for (String nickName : nickNamesRmi) {
            matchController.addPlayer(nickName);
        }
        for (String nickName : nickNamesSocket){
            matchController.addPlayer(nickName);
        }
        matchController.startGame();
    }

    /**
     * Timeout Manager extends Thread
     * This method manages the timeout. It interrupts the thread if the countdown goes to 0 or if join 4 players
     */

    private class TimeoutExpired extends Thread {

        void timeExpiring(ArrayList<ClientInterfaceRMI> clientsInterfaces, ServerImplementationRMI server){
            new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            try {
                                startGame(clientsInterfaces, server);
                            } catch (CloneNotSupportedException e) {
                                e.printStackTrace();
                            }
                        }
                    }, timeout
            );
        }

        /**
         * Runner
         * This method initialize a thread for the match countdown
         */
        @Override
        public void run(){
            timeExpiring( serverRMI.getClientInterfaceRMI(), serverRMI);
        }

    }




}
