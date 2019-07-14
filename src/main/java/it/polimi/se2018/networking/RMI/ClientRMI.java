package it.polimi.se2018.networking.RMI;

import it.polimi.se2018.controller.MoveParser;
import it.polimi.se2018.model.ChoseInputMove;
import it.polimi.se2018.view.LogIn;

import java.io.*;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.rmi.ConnectException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import static java.lang.System.in;

/**
 * ClientRMI Class implements ClientInterfaceRMI
 * This class manages the Client connection side
 * @author Luca Massini (all the code except the constructor method, the notifytodownload method and the JDocs)
 * @author Federico Lichinchi (constructor method and notifytodownload method)
 * @author Davide Lorenzi (JDocs)
 */
public class ClientRMI implements ClientInterfaceRMI {


    private ServerInterface server;
    private MoveParser moveParser = MoveParser.newMoveParser();
    private int timeoutRMI;
    private ChoseInputMove move;
    private String tmp;
    public String ip;
    private BufferedReader in = new BufferedReader(
            new InputStreamReader( System.in ) );

    //public void setIp(String ip) {
      //  this.ip = ip;
    //}

    /**
     * ClientRMI Constructor
     * This method initializes the Client side and throws exceptions in chase of errors
     * @param ip is the server IP
     * @throws InterruptedException if server stop to work
     */
    public ClientRMI(String ip) throws InterruptedException {

        try {
            timeoutRMI = Integer.parseInt(new BufferedReader(
                    new FileReader(new File("src/main/java/MoveTimer.txt"))).readLine());
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        this.ip = ip;
        boolean serverAlive;
        try {

            String name ="//"+ip+"/MyServer";
            server = (ServerInterface) Naming.lookup(name);
            UnicastRemoteObject.exportObject(this, 0);
            serverAlive = true;
            if(serverAlive) {
                LogIn log = new LogIn();
                log.setClientRMI(this);
                log.log();
            }
        } catch (NotBoundException e) {
            System.out.println("The passed reference is not associated with anything!");
            e.printStackTrace();
        } catch (MalformedURLException e) {
            System.out.println("URL not found");
            e.printStackTrace();
        } catch(ConnectException e){
            System.out.println("Sagrada's server is down at the moment. Please try again later");
            System.out.println("This process will close within 10 seconds");
            int i=0;
            while(i<10) {
                Thread.sleep(1000);
                System.out.println(10-i);
                i++;
            }
        } catch (RemoteException e) {
            System.err.println("Connection error: " + e.getMessage() + "!");
            e.printStackTrace();
        }
    }

    /**
     * MoveDownloader
     * This method downloads the move coming from the server
     * @return the downloaded input move
     * @throws RemoteException if there is a problem sending player's nickname
     */
    private ChoseInputMove downloadInputMove() throws RemoteException {
        ChoseInputMove downloadedInputMove;
        downloadedInputMove = server.getMessageFromServer(this);
        return downloadedInputMove;
    }

    /**
     * ServerLogger
     * This method is used to establish a connection to the server
     * @param nickName player's nickname used to log to the server
     * @return nicknames if nicknames are correctly sent to the server, false otherwise
     */
    public boolean logToServer(String nickName){
        try {
            return server.sendNickNameToServer(nickName, this);
        } catch (RemoteException | CloneNotSupportedException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Input Move SenderRMI
     * @param inputMove is the chosen move
     * @throws RemoteException if is not possible to notify the remote server
     */
    @Override
    public void sendInputMove(ChoseInputMove inputMove) throws RemoteException {
        server.sendMessageToServer(inputMove,this);
        server.notifyServer(this);
    }


    /**
     * Download Notifier
     * This method is used to update the Client situation
     */
    @Override
    public void notifyToDownload() {
        try {
            move = downloadInputMove();
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
                        try {
                            if(tmp.equals("")) {
                                sendInputMove(new ChoseInputMove("exit", null, move.getPlayerAddress()));
                                System.out.println( "Timeout expired. Connection closed." );
                                System.exit( 0 );
                            }
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                };
                Timer timer = new Timer();
                timer.schedule( task, (long)timeoutRMI*1000 );
                tmp = in.readLine();
                sendInputMove(new ChoseInputMove(tmp, null, move.getPlayerAddress()));
                timer.cancel();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

