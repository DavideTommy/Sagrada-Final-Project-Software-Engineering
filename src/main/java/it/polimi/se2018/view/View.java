package it.polimi.se2018.view;

import it.polimi.se2018.networking.RMI.ClientRMI;
import it.polimi.se2018.networking.Socket.ClientImplementationSocket;

import java.util.Observable;
import java.util.Scanner;

import static java.lang.System.in;

/**
 * View Class extends Observable
 * @author Federico Lichinchi
 * @author Luca Massini
 * @author Davide Lorenzi (JDocs)
 */
public class View extends Observable {

    /**
     * Choice Checker
     * This method is used to check if the player input is correct or not
     * @param i is the choice of the player
     * @return true if the choice made by the player is correct, false otherwise
     */
    private boolean rightChoice(int i){
        return i==1 || i==2;
    }

    /**
     * Char Presence Checker
     * this method verifies if there is a char in a string
     * @param word is the string in input
     * @return true if the first char is a letter, false if the word is null
     */
    private boolean isThereAChar(String word){
        if(word == null)
            return false;
        Character firstChar = word.charAt(0);
        return Character.isLetter(firstChar);
    }

    /**
     * Connection Choice Error
     * This method throws an error if the player choices a wrong connection method
     */
    private void printBadConnectionChoice(){
        System.out.println("This isn't a right number (choice) to choose your connection. Please Retry: ");
    }

    /**
     * IP Validator
     * This method verifies that the received IP is in the correct form
     * @param ip is the server IP
     * @return true if the IP is in a correct form, false otherwise
     */
    private boolean isValidIp(String ip){
        boolean validIP;
        validIP = ip!= null;
        if(validIP )
            validIP = !ip.equals("");
        return validIP;
    }

    /**
     * IP Getter
     * This method ask the player to put the IP of the server
     * @return the server IP
     */
    private String askIP(){
        Scanner scanner = new Scanner(in);
        String ip;
        boolean validIP;
        System.out.print("Insert the IP address of the server you want to connect: ");
        ip = scanner.nextLine();
        validIP = isValidIp(ip);
        while(!validIP){
            System.out.print("This is not a valid IP address, Please retry: ");
            ip=scanner.nextLine();
            validIP = isValidIp(ip);
        }
        return ip;
    }

    /**
     * View Constructor
     * This method is used to build
     * @throws InterruptedException If client process can't start
     */
    public View () throws InterruptedException {
        System.out.println("Welcome to Sagrada!! ");
        int choice=0;
        System.out.println("Digit 1 if you want an RMI connection or digit 2 if you want a SOCKET connection");
        System.out.println("1: RMI");
        System.out.println("2: Socket");
        System.out.println("Choose your connection please: ");
        Scanner scanner = new Scanner(System.in);
        String choiceCheck ;
        boolean correct = false;
        while(!correct) {
            choiceCheck = scanner.nextLine();
            if (choiceCheck != null) {
                if (!isThereAChar(choiceCheck) && choiceCheck.length() == 1) {
                    choice = Character.getNumericValue(choiceCheck.charAt(0));
                    if (rightChoice(choice)) {
                        correct = true;
                    } else
                        printBadConnectionChoice();
                } else
                    printBadConnectionChoice();
            }
        }
        String ip = askIP();
        if(choice == 1) {
            ClientRMI clientRMI= new ClientRMI(ip);
        }
        else{
            new ClientImplementationSocket(ip);
        }
    }

    /**
     * Main Initializer
     * This method initializes the view
     * @param args all elements in view
     */
    public static void main(String[] args) {
        try {
            new View();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
