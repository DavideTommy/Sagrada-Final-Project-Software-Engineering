package it.polimi.se2018.networking.Socket;

import it.polimi.se2018.model.ChoseInputMove;

import java.io.*;
import java.net.Socket;

/**
 * NetworkHandler Class extends Thread implements ServerInterfaceSocket
 * This class manages all network functions including the handling of connection and the message system
 * @author Luca Massini
 * @author Davide Lorenzi (JDocs)
 */
public class NetworkHandler extends Thread implements ServerInterfaceSocket {

    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private ClientInterfaceSocket client;
    private volatile Message message;
    private Socket socket;

    /**
     * NetworkHandler Constructor
     * @param host server
     * @param port server access port
     * @param client player
     */
    public NetworkHandler(String host, int port, ClientInterfaceSocket client) {

        try {
            socket = new Socket(host, port);
            this.outputStream = new ObjectOutputStream((socket.getOutputStream()));
            this.inputStream = new ObjectInputStream(socket.getInputStream());
            this.client = client;


        } catch (IOException e) {
            System.out.println("Connection Error.");
            e.printStackTrace();
        }
    }


    /**
     * Runner
     * This method initialize a new thread that is waiting for message from the server
     */
    @Override
    public  void run(){
        while(socket.isConnected()) {
                try {
                    do {
                        this.message = (Message) this.inputStream.readObject();
                    } while (message == null);
                    client.notifyToDownload();
                    this.message = null;
                }
             catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * MessageToTheServer SenderRMI
     * This method is used to send messages
     * @param inputMove player's move
     * @param client players interface
     */
    @Override
    public synchronized void sendMessageToServer(ChoseInputMove inputMove, ClientInterfaceSocket client) {
        this.message = new Message(null,inputMove,null);
        notifyServer(client);
    }

    /**
     * Message Getter
     * This method gets messages from the server
     * @param client destination
     * @return message coming from server
     */
    @Override
    public synchronized Message getMessageFromServer(ClientInterfaceSocket client) {
        return this.message;
    }

    /**
     * NickName SenderRMI
     * This method in used to send player's NickName to the server
     * @param nickName player's NickName
     * @param client Client source of the message
     */
    @Override
    public synchronized void sendNickNameToServer(String nickName, ClientInterfaceSocket client) {
        this.message  = new Message(nickName,null,null);
        notifyServer(client);
    }

    /**
     * Server Notifier
     * This method notifies the server sending a message
     * @param client player that sent the message
     */
    @Override
    public void notifyServer(ClientInterfaceSocket client) {
        try {
            boolean pass = false;
            while(!pass) {
                if (message != null)
                    pass = true;
            }
            outputStream.writeObject(this.message);
            Thread.sleep(1000);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
