package it.polimi.se2018.view;

import it.polimi.se2018.networking.RMI.ClientRMI;
import it.polimi.se2018.networking.Socket.ClientImplementationSocket;

import java.util.Scanner;

import static java.lang.System.in;

/**
 * LogIn Class
 * This class manages the login of a player and his connection to the server
 * @author Luca Massini
 * @author Davide Lorenzi (JDocs)
 */
public class LogIn {
    private ClientImplementationSocket clientSocket = null;
    private ClientRMI clientRMI = null;
    private boolean isRMI = false;
    private Scanner scanner = new Scanner(in);


    public void setClientRMI(ClientRMI clientRMI) {
        this.clientRMI = clientRMI;
        this.isRMI=true;
    }

    public void setClientSocket(ClientImplementationSocket clientSocket) {
        this.clientSocket = clientSocket;
        this.isRMI=false;
    }



    private void nameAlreadyChosen(){
        System.out.println("This name is already used by other players: please choose another name and retry: ");
    }

    private void askName(){
        System.out.println("What's your nickName: ");
    }

    private void logSocket(){
        askName();
        String name = scanner.nextLine();
        clientSocket.logToServer(name);
    }

    private void logRMI(){
        boolean entrance;
        askName();
        String name = scanner.nextLine();
        entrance = clientRMI.logToServer(name);
        while(!entrance){
            nameAlreadyChosen();
            name = scanner.nextLine();
            entrance = clientRMI.logToServer(name);
        }
    }


    public void log(){
        if(isRMI)
            logRMI();
        else
            logSocket();
    }
}
