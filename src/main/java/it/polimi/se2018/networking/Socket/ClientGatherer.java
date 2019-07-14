package it.polimi.se2018.networking.Socket;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * ClientGatherer Class extends Thread
 * This class is used to establish a new Client connection
 * @author Luca Massini
 * @author Davide Lorenzi (JDocs)
 */
public class ClientGatherer extends Thread {

    private final it.polimi.se2018.networking.Server server;
    private final int port;
    private ServerSocket serverSocket;


    /**
     * ClientGatherer Constructor
     * This method generates the ClientGatherer
     * @param server game server
     * @param port connection port
     */
    public ClientGatherer(it.polimi.se2018.networking.Server server, int port) {
        this.server = server;
        this.port = port;

        try {
            this.serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            System.out.println("Server Socket creation failed.");
        }
    }


    /**
     * Runner
     * This method initialize the Client thread and so the server connection
     */
    @Override
    public void run(){

        //awaits the connections from clients
        System.out.println("Waiting for clients.\n");

        while(true) {

            Socket newClientConnection;

            try {

                newClientConnection = serverSocket.accept();
                newClientConnection.setKeepAlive(true);
                server.addClient(newClientConnection);
                System.out.println("Client " + newClientConnection.getLocalAddress() + " connected");

            } catch (IOException e) {
                System.out.println("Connection with client failed.");
            }
        }
    }
}
