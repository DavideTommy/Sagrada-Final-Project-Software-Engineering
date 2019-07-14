package it.polimi.se2018.networking.Socket;

import it.polimi.se2018.controller.MoveParser;
import it.polimi.se2018.model.ChoseInputMove;
import it.polimi.se2018.view.LogIn;

import java.io.*;
import java.rmi.RemoteException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import static java.lang.System.in;

/**
 * ClientImplementationSocket implements ClientInterfaceSocket
 * This class is the core of teh Client interface and manages teh Client connection and the message sending to the server
 * @author Luca Massini
 */
public class ClientImplementationSocket implements ClientInterfaceSocket {

    private boolean nameDifferent;
    private NetworkHandler serverClientSide;
    private MoveParser moveParser = MoveParser.newMoveParser();
    private ChoseInputMove move;
    private int timeoutSocket;
    private boolean isNameDifferentChanged = false;
    private BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private String tmp;

    public void setNameDifferentChanged(){
        isNameDifferentChanged = !isNameDifferentChanged;
    }

    private static final int PORT = 1111;

    /**
     * ClientImplementationSocket
     * This method starts a new network handler
     * @param ip is the server IP
     */
    public ClientImplementationSocket(String ip){

        serverClientSide = new NetworkHandler(ip,PORT,this);
        serverClientSide.start();
        LogIn log = new LogIn();
        log.setClientSocket(this);
        log.log();
        try {
            timeoutSocket = Integer.parseInt(new BufferedReader(
                    new FileReader(new File("src/main/java/MoveTimer.txt"))).readLine());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * InputMove SenderRMI
     * This method sends the player's move
     * @param inputMove player's input move
     */
    public void sendInputMove(ChoseInputMove inputMove)  {
        serverClientSide.sendMessageToServer(inputMove,this);
    }


    /**
     * ServerLogger
     * This method is used to establish a connection to the server
     * @param NickName player's nickname used to log to the server
     */
    public void logToServer(String NickName) {
        serverClientSide.sendNickNameToServer(NickName,this);
    }

    /**
     * MoveDownloader
     * This method downloads the move coming from the server
     * @return the downloaded input move
     */
    private ChoseInputMove downloadInputMove() {
        Message downloadedMessage;
        downloadedMessage = serverClientSide.getMessageFromServer(this);
        if(downloadedMessage.getNameDifferent()!=nameDifferent)
            setNameDifferentChanged();
        nameDifferent = downloadedMessage.getNameDifferent();
        return downloadedMessage.getMove();
    }

    public boolean getNameDifferent(){
        return nameDifferent;
    }

    /**
     * Download Notifier
     * This method is used to update the Client situation
     */
    @Override
    public  void notifyToDownload()  {
        try {
            Thread.sleep(5000);
            try {
                move = downloadInputMove().clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            System.out.println("\n" + move.getMessage() + "\n" + move.toStringElement());
            if(!moveParser.haveToAnswer(move)) {
                if(!move.getMessage().contains("Connection reached"))
                    sendInputMove(new ChoseInputMove("S", null, move.getPlayerAddress()));
            }
            else {
                tmp = "";

                TimerTask task = new TimerTask()
                {
                    public void run()
                    {
                        if(tmp.equals("")) {
                            sendInputMove(new ChoseInputMove("exit", null, move.getPlayerAddress()));
                            System.out.println( "Timeout expired. Connection closed." );
                            System.exit( 0 );
                        }
                    }
                };
                Timer timer = new Timer();
                timer.schedule( task, (long)timeoutSocket*1000 );
                tmp = in.readLine();
                sendInputMove(new ChoseInputMove(tmp, null, move.getPlayerAddress()));
                timer.cancel();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Move Getter
     * This move catch che incoming player move
     * @return ce chosen input move
     */
    public  ChoseInputMove getMove() {
        return move;
    }
}
