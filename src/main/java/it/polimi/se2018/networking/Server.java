package it.polimi.se2018.networking;

import it.polimi.se2018.networking.RMI.ServerImplementationRMI;
import it.polimi.se2018.networking.Socket.*;

import java.io.*;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * Server Class
 * This class is the core of serverSocket connection and create a new instance of the serverSocket if it hasn't already been done
 * @author Luca Massini
 * @author Davide Lorenzi (JDocs)
 */
public class Server{

    private static final int PORT = 1111;
    private static ServerImplementationSocket serverSocket;
    private static Server ist = null;

    private ArrayList<ClientInterfaceSocket> clients = new ArrayList<>();

    /**
     * Server Creator
     * This method creates a new instance of the server
     */
    public static void newServer(){
        if(ist==null) ist = new Server();
    }


    /**
     * Server Constructor
     * This method creates a new game server
     */
    public Server() {


            int port = 1099;

        try {
            GameStarter starter=GameStarter.newGameStarter(this);
            serverSocket = starter.getServerSocket();
            (new ClientGatherer(this, PORT)).start();
            //File file = new File("/Timer.txt");
            InputStream stream = getClass().getResourceAsStream("/Timer.txt");
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream))) {
                starter.setTimeout(Integer.parseInt(bufferedReader.readLine()) * 10000);
            }
            /*try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
                starter.setTimeout(Integer.parseInt(bufferedReader.readLine())*1000);
            }*/
            String name = "//"+InetAddress.getLocalHost().getHostAddress()+"/MyServer";
            ServerImplementationRMI serverRmi = starter.getServerRMI();
            LocateRegistry.createRegistry(port);
            Naming.rebind(name,serverRmi);
            System.out.println(InetAddress.getLocalHost().getHostAddress());
            System.out.println("Server is up!");

        } catch (MalformedURLException e) {
            System.err.println("Impossible to register the indicated object");
        } catch (RemoteException e) {
            System.err.println("Error of connection: " + e.getMessage() + "!");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * ServerStatus Verifier
     * This method is used to know if the serverSocket has been started or not
     * @return Server Instance status
     */
    public static Server getServerSocket(){
        return ist;
    }


    /**
     * Implementation Getter
     * This method return the serverSocket implementation
     * @return the serverSocket
     */
    public static synchronized ServerInterfaceSocket getImplementation() {
        return serverSocket;
    }

    /**
     * Client Remover
     * This method is used to remove a specific Client from the match
     * @param client Client that has to be removed
     */
    public synchronized void removeClient(ClientInterfaceSocket client){
        clients.remove(client);
    }

    /**
     * Client Adder
     * This method is used to add a player that is tying to join the match
     * @param clientConnection is the player's connection request
     */
    public synchronized void  addClient(Socket clientConnection) {
        VirtualClientSocket client = new VirtualClientSocket(this, clientConnection);
        clients.add(client);
        client.start();
    }


    /**
     * Clients Getter
     * This method is used to get the ArrayList of players that joined the match
     * @return the required Client ArrayList
     */
    public ArrayList<ClientInterfaceSocket> getClients() {
        return clients;
    }

}
