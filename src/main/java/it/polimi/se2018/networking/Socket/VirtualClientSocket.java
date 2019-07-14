package it.polimi.se2018.networking.Socket;

import it.polimi.se2018.model.ChoseInputMove;
import it.polimi.se2018.networking.Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.rmi.RemoteException;

/**
 * VirtualClientSocket Class extends Thread implements ClientInterfaceSocket
 * This class is used to establish a new Client connection
 * @author Luca Massini
 * @author Davide Lorenzi (JDocs)
 */
public class VirtualClientSocket extends Thread implements ClientInterfaceSocket {

    private final it.polimi.se2018.networking.Server server;
    private ServerInterfaceSocket serverImplementation;
    private volatile Message message;


    private Socket clientConnection;
    private ObjectOutputStream output;
    private ObjectInputStream input;

    /**
     * Constructor VirtualClientSocket
     * This method initialize the server implementation
     * @param server game server
     * @param clientConnection established
     */
    public VirtualClientSocket(it.polimi.se2018.networking.Server server, Socket clientConnection) {

        this.serverImplementation = Server.getImplementation();
        this.server = server;
        this.clientConnection = clientConnection;
        try {
            this.output = new ObjectOutputStream(clientConnection.getOutputStream());
            this.input = new ObjectInputStream(clientConnection.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Runner
     * This method creates a new thread used to initialize server connection
     */
    @Override
    public void  run(){

        try {

            System.out.println("Waiting for messages.");

            while(this.clientConnection.isConnected()) {
                    try {
                        do {
                            message = (Message) input.readObject();
                            if(message==null)
                                System.out.println("null message from client ");
                        }while(message == null);
                        String nickName = message.getNickName();
                        ChoseInputMove move = message.getMove();
                        if (nickName != null) {
                            try {
                                serverImplementation.sendNickNameToServer(nickName, this);
                            } catch (CloneNotSupportedException e) {
                                e.printStackTrace();
                            }
                        } else {
                            if (move != null) {
                                serverImplementation.sendMessageToServer(move, this);
                                System.out.println(move.getMessage());
                            }
                        }
                        this.message = null;
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    /**
     * Download Notifier
     * This method is used to update the Client situation
     */
    @Override
    public synchronized void notifyToDownload() {
        try {
            output.writeObject( serverImplementation.getMessageFromServer(this));
            Thread.sleep(1000);
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * InputMove Sender
     * This method is used to send an input move to the server
     * @param inputMove is the player's input move
     * @throws RemoteException if is not possible
     */
    @Override
    public void sendInputMove(ChoseInputMove inputMove) throws RemoteException {

    }
}
