package it.polimi.se2018.controller;

import it.polimi.se2018.controller.moves.MoveManager;
import it.polimi.se2018.controller.toolCards.ToolCardManager;
import it.polimi.se2018.model.cards.PublicObjectiveCard;
import it.polimi.se2018.model.cards.ToolCard;
import it.polimi.se2018.model.mv.Model;
import it.polimi.se2018.model.mv.VirtualView;
import it.polimi.se2018.model.ChoseInputMove;
import it.polimi.se2018.model.rules.Rules;
import it.polimi.se2018.model.*;
import it.polimi.se2018.model.score.CalcScore;
import it.polimi.se2018.networking.RMI.ClientInterfaceRMI;
import it.polimi.se2018.networking.Socket.ClientInterfaceSocket;

import java.util.*;

/**
 * Controller Class
 * This class is the core of the game logic
 * @author Federico Lichinchi
 * @author Davide Lorenzi (JDocs)
 */
public class Controller implements Observer {

    private static final int MAX_SCORE = -20;
    private Model model;
    private MoveManager moveManager;
    private MoveParser moveParser;
    private ChoseInputMove move;
    private ChoseInputMove serverMove;
    private ToolCardManager toolCardManager;
    private ArrayList<VirtualView> virtualViews;
    private boolean alreadyChosen;
    private boolean insertedDie;
    private BoxOfSchemeCards box = BoxOfSchemeCards.newBox();
    private String key;
    private int[] coordinates = new int[2];
    private Rules rules = Rules.newRules();
    private ArrayList<Player> playersThatSkipSecondTurn;
    private ArrayList<String> disconnectedPlayerAddresses;

    /**
     * TrueChosen Setter
     * This method sets to true the alreadyChosen parameter
     */
    private void setChosenToTrue(){
        alreadyChosen = true;
    }
    /**
     * FalseChosen Setter
     * This method sets to false the alreadyChosen parameter
     */
    private void setChosenToFalse(){
        alreadyChosen = false;
    }

    /**
     * AlreadyChosen Checker
     * @return true if something has already been chosen, false otherwise
     */
    public boolean hasAlreadyBeenChosen(){
        return alreadyChosen;
    }

    /**
     * Constructor Method
     * This method creates one virtual view for each player that joined the match
     * @param nicknames is the array containing all the players' nicknames
     * @param virtualViews is the ArrayList of virtual views
     * @throws CloneNotSupportedException if is not possible to clone some elements
     */
    public Controller(List<String> nicknames,
                      List<VirtualView> virtualViews) throws CloneNotSupportedException {
        model = new Model(nicknames.size());
        moveParser = MoveParser.newMoveParser();
        moveManager = MoveManager.newMoveManager(this);
        disconnectedPlayerAddresses = new ArrayList<>();
        toolCardManager = ToolCardManager.newToolCardManager(this);
        playersThatSkipSecondTurn = new ArrayList<>(nicknames.size());
        this.virtualViews = new ArrayList<>(virtualViews.size());
        for(int i = 0; i < virtualViews.size(); i++) {
            this.virtualViews.add(virtualViews.get(i));
            this.virtualViews.get(i).addObserver(this);
        }
    }

    /**
     * Player Adder
     * This method create a new Player Object when a new player joins the Match
     * @param address is the IP address of the player
     */
    public void addPlayer(String address) {
        model.getPlayers().add(new Player(address, address, null, null, null));
    }

    /**
     * Player Color Manager
     * This method manages the assignment of a Player Color Marker
     */
    private void managePlayerColours() {
        key = "colour";
        String[] colours = {"Red", "Green", "Blue", "Purple"};
        mixColourArray(colours);
        for(int i = 0; i < virtualViews.size(); i++) {
            model.addObserver(virtualViews.get(i));
            ChoseInputMove choseInputMove = moveParser.answerMessage(key);
            choseInputMove.setElement(colours[i]);
            int found = 0;
            for(int j = 0; j < model.getMatch().getPlayers().size(); j++) {
                if(found == 0 && model.getMatch().getPlayers().get(j).getPlayerAddress().equals(virtualViews.get(i).getName())) {
                    model.getMatch().getPlayers().get(j).setColor(colours[i]);
                    choseInputMove.setPlayerAddress(virtualViews.get(i).getName());
                    found = 1;
                }
            }
            model.setMove(choseInputMove);

            model.deleteObserver(virtualViews.get(i));
        }
    }

    /**
     * Array Color Mixer
     * This method mix the colors array
     * @param colours string of all available colors
     */
    private void mixColourArray(String[] colours) {
        Random random = new Random();
        int j;
        String tmp;
        for(int i = 0; i < colours.length; i++) {
            do {
                j = random.nextInt(colours.length - 1);
            } while (j == i);
            tmp = colours[i];
            colours[i] = colours[j];
            colours[j] = tmp;
        }
    }

    /**
     * SchemeCard Chooser
     * This method is used to offer the player four SchemeCards and to take the chosen one
     */
    private void chooseSchemeCards() {
        for(int i = 0; i < virtualViews.size(); i++) {
            key = "schemeCard";
            model.addObserver(virtualViews.get(i));
            ArrayList<Grid> grids = new ArrayList<>(4);
            grids.add(new Grid(box.randomSchemeCard()));
            grids.add(new Grid(box.randomSchemeCard()));
            grids.add(new Grid(box.randomSchemeCard()));
            grids.add(new Grid(box.randomSchemeCard()));
            serverMove = moveParser.requestMessage(key);
            serverMove.setPlayerAddress(virtualViews.get(i).getName());
            serverMove.setElement(grids);
            model.setMove(serverMove);
            if(!move.getMessage().equals("exit")) {
                for (int j = 0; j < model.getMatch().getPlayers().size(); j++) {
                    if (model.getMatch().getPlayers().get(j).getPlayerAddress().equals(virtualViews.get(i).getName())) {
                        try {
                            model.getMatch().getRoundTrack().getPlayers().get(j).setGrid(grids.get(getNumberOfPlayerMove()).clone());
                        } catch (CloneNotSupportedException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                }
            } else {
                notifyDisconnectedPlayer(virtualViews.get(i).getName());
            }
            model.deleteObserver(virtualViews.get(i));
        }

    }

    /**
     * Disconnected Player Notifier
     * This method notifies all players in game that a player has left the game.
     * @param playerAddress Address of the player that has left the game
     */
    private void notifyDisconnectedPlayer(String playerAddress) {
        disconnectedPlayerAddresses.add(playerAddress);
        for (VirtualView virtualView : virtualViews) {
            if (!disconnectedPlayerAddresses.contains(virtualView.getName())) {
                model.addObserver(virtualView);
                serverMove = moveParser.answerMessage("disconnectedPlayer");
                serverMove.setPlayerAddress(virtualView.getName());
                serverMove.setElement(playerAddress);
                model.setMove(serverMove);
                model.deleteObserver(virtualView);
            }
        }
    }


    /**
     * Match Starter
     * This method throws the beginning of a match
     * @throws CloneNotSupportedException if is not possible to create a new match
     */
    public void startGame() throws CloneNotSupportedException {
        model.newMatch();
        managePlayerColours();
        chooseSchemeCards();
        int numberOfRounds = 10;
        while(model.getMatch().getRoundTrack().getRound() <= numberOfRounds) {
            startRound();
        }
        calcPlayersScore();
        showPlayersScore();
        showWinner();
    }

    /**
     * PublicObjectiveCards Getter
     * This method gets the list of Public Objective Cards
     * @return list of Public Objective Cards
     */
    private List<PublicObjectiveCard> getPublicObjectiveCards() {
        return model.getMatch().getRoundTrack().getPublicObjectiveCards();
    }


    /**
     * Players Getter
     * This method gets the list of players
     * @return the required list of players
     */
    private List<Player> getMatchPlayers() {
        return model.getMatch().getRoundTrack().getPlayers();
    }

    /**
     * Winner Announcer
     * This method shows the player that won the match
     */
    private void showWinner() {
        int maxScore = MAX_SCORE;
        String winnerPlayerName = "none";
        for(Player player : getMatchPlayers()) {
            if (player.getScore() >= maxScore) {
                maxScore = player.getScore();
                winnerPlayerName = player.getPlayerName();
            }
        }
        for(VirtualView virtualView : virtualViews) {
            key = "winner";
            model.addObserver(virtualView);
            serverMove = moveParser.answerMessage(key);
            serverMove.setElement(winnerPlayerName);
            serverMove.setPlayerAddress(virtualView.getName());
            model.setMove(serverMove);

            model.deleteObserver(virtualView);
        }
    }

    /**
     * DieNewValue Getter
     * This method gets the new value of a chosen die
     * @param playerAddress is the IP Address of a player
     * @return the message to the server
     */
    public int getDieNewValue(String playerAddress) {
        key = "changeDieValue";
        serverMove = moveParser.requestMessage(key);
        serverMove.setPlayerAddress(playerAddress);
        model.setMove(serverMove);
        if(!move.getMessage().equals("exit"))
            return getNumberOfPlayerMove()+1;
        else
        {
            notifyDisconnectedPlayer(playerAddress);
            return -1;
        }
    }

    /**
     * Players Score Getter
     * Tis method shows the score of all players
     */
    private void showPlayersScore() {
        for(VirtualView virtualView : virtualViews) {
            model.addObserver(virtualView);
            key = "playerScore";
            serverMove = moveParser.answerMessage(key);
            serverMove.setPlayerAddress(virtualView.getName());
            serverMove.setElement(getMatchPlayers());
            model.setMove(serverMove);
            model.deleteObserver(virtualView);
        }
    }

    /**
     * Score Calculator
     * This method starts the score computing of the players
     */
    private void calcPlayersScore() {
        CalcScore calcScore = new CalcScore();
        for(Player player : getMatchPlayers())
            player.setScore(calcScore.calcScore(player, getPublicObjectiveCards()));
    }

    /**
     * Player Skipper
     * This method blocks the second turn of a player after the application of a particular ToolCard
     * @param playerAddress is the player IP Address
     * @return true if a player must skip the second turn or not
     */
    private boolean playerWillSkipSecondTurn(String playerAddress) {
        for(Player player : playersThatSkipSecondTurn) {
            if(player.getPlayerAddress().equals(playerAddress)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Second Turn Players Skipper
     * This method adds players to a list of players that must skip a turn
     * @param player is the player that must skip a turn
     */
    public void addPlayerThatWillSkipSecondTurn(Player player) {
        try {
            playersThatSkipSecondTurn.add(player.clone());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Second Turn Players Not Skipper
     * This method removes players from a list of players that had skip a turn
     * @param player is the player that must skip a turn
     */
    private void removePlayerThatSkippedSecondTurn(Player player) {
        playersThatSkipSecondTurn.remove(player);
    }

    /**
     * Second Turn Notifier
     * This method notifies that this is his second turn
     * @param playerAddress is the IP Address of the player
     */
    public void notifyPlayerSecondTurn(String playerAddress) {
        key = "isSecondTurn";
        serverMove = moveParser.answerMessage(key);
        serverMove.setPlayerAddress(playerAddress);
        model.setMove(serverMove);
    }

    /**
     * This method shows to the player with address 'playerAddress' the number of the round of the match.
     * @param playerAddress Address of the player
     */
    public void showRound(String playerAddress) {
        key = "round";
        ChoseInputMove tmp = moveParser.answerMessage(key);
        tmp.setPlayerAddress(playerAddress);
        tmp.setElement(""+model.getMatch().getRoundTrack().getRound());
        model.setMove(tmp);
    }

    /**
     * This method shows to the player with address 'playerAddress' the number of the turn of the round of the match.
     * @param playerAddress Address of the player
     */
    public void showTurn(String playerAddress) {
        key = "turn";
        ChoseInputMove tmp = moveParser.answerMessage(key);
        tmp.setPlayerAddress(playerAddress);
        tmp.setElement(""+model.getMatch().getRoundTrack().getPlayerTurn());
        model.setMove(tmp);
    }

    /**
     * This method shows to the player with address 'playerAddress' the name of the player of current turn.
     * @param playerAddress Address of the player
     */
    public void showPlayerOfCurrentTurn(String playerAddress) {
        key = "playerTurn";
        ChoseInputMove tmp = moveParser.answerMessage(key);
        tmp.setPlayerAddress(playerAddress);
        tmp.setElement(getPlayerOfCurrentTurn().getPlayerName());
        model.setMove(tmp);
    }

    /**
     * This methods remove the 'playerAddress' from the ArrayList that contains the addresses of the players that
     * have left the match. This method is called when a player has rejoined the game. This method returns 'true' if
     * this address is found and remove successfully from the ArrayList, 'false' otherwise.
     * @param playerAddress Address of the player to remove from the ArrayList
     * @return true if the player correctly re joined the match, false otherwise
     */
    public boolean rejoinPlayer(String playerAddress) {
        for (String address : disconnectedPlayerAddresses) {
            if (address.equals(playerAddress)) {
                for(VirtualView virtualView : virtualViews) {
                    if (virtualView.getName().equals(playerAddress)) {
                        disconnectedPlayerAddresses.remove(address);
                        virtualViews.remove(virtualView);
                        return true;
                    }
                }
                return false;
            }
        }
        return false;
    }

    /**
     * This method, called by the server, is used to add a new VirtualView class to the virtualViews ArrayList of
     * Controller, with socket connection. This method is used when a players rejoined the match.
     * @param virtualView The new VirtualView object to add
     * @param clientInterfaceSocket The client interface RMI to add to the virtualView
     */
    public void addNewVirtualView(VirtualView virtualView, ClientInterfaceSocket clientInterfaceSocket) {
        virtualView.setClientSocket(clientInterfaceSocket);
        virtualViews.add(virtualView);
    }

    /**
     * This method, called by the server, is used to add a new VirtualView class to the virtualViews ArrayList of
     * Controller, with RMI connection. This method is used when a players rejoined the match.
     * @param virtualView The new VirtualView object to add
     * @param clientInterfaceRMI The client interface RMI to add to the virtualView
     */
    public void addNewVirtualView(VirtualView virtualView, ClientInterfaceRMI clientInterfaceRMI) {
        virtualView.setClientRMI(clientInterfaceRMI);
        virtualViews.add(virtualView);
    }

    /**
     * Round Starter
     * This method initialize the beginning of the round
     */
    public void startRound() {
        if(model.getMatch().getRoundTrack().getBound()) {
            for (int i = 0; i < (2 * model.getMatch().getPlayers().size()) + 1; i++) {
                Die d = model.getMatch().getBag().extractDie();
                model.getMatch().getReserve().addDie(d);
            }
            model.getMatch().getRoundTrack().setBound();
        }
        if(!disconnectedPlayerAddresses.contains(getPlayerOfCurrentTurn().getPlayerAddress()) &&
                (model.getMatch().getRoundTrack().getPlayerTurn() == 1 || !playerWillSkipSecondTurn(getPlayerOfCurrentTurn().getPlayerAddress()))) {
            Grid grid = null;
            int j = 0;
            for (int i = 0; i < model.getMatch().getRoundTrack().getPlayers().size(); i++) {

                model.addObserver(virtualViews.get(i));

                showRound(virtualViews.get(i).getName());
                showTurn(virtualViews.get(i).getName());

                if (getPlayerOfCurrentTurn().getPlayerAddress().equals(virtualViews.get(i).getName()))
                    j = i;
                else if(!disconnectedPlayerAddresses.contains(virtualViews.get(i).getName()))
                    showPlayerOfCurrentTurn(virtualViews.get(i).getName());

                model.deleteObserver(virtualViews.get(i));
            }

            model.addObserver(virtualViews.get(j));
            try {
                grid = getPlayerOfCurrentTurn().getGrid().clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            showStatus(virtualViews.get(j).getName(), grid);
            chooseMove(j);
            setInsertedDie(false);
            if(!move.getMessage().equals("exit"))
                moveManager.applyRequiredMove(getNumberOfPlayerMove()+1);
            else
                notifyPlayerSecondTurn(getPlayerOfCurrentTurn().getPlayerAddress());
            model.deleteObserver(virtualViews.get(j));
        }
        else
            removePlayerThatSkippedSecondTurn(getPlayerOfCurrentTurn());
        model.getMatch().getRoundTrack().nextTurn();
        setChosenToFalse();

        if(model.getMatch().getRoundTrack().getBound()) {
            for (int i = 0; i < model.getMatch().getReserve().getDie().size(); i++) {
                model.getMatch().getRoundTrack().addDie(model.getMatch().getReserve().getDie().get(i));
            }
            model.getMatch().getReserve().toEmptyDice();
        }
    }

    /**
     * Inserted Die Getter
     * This method gets the die that has just been inserted
     * @return the required die
     */
    public boolean getInsertedDie() {
        return insertedDie;
    }

    /**
     * DieRoller Shower
     * This method allows to show
     * @param playerAddress is the player IP address
     * @param die is the chosen die on which ask the die rolling
     */
    public void showRolledDie(String playerAddress, Die die) {
        key = "rolledDie";
        serverMove = moveParser.answerMessage(key);
        serverMove.setPlayerAddress(playerAddress);
        serverMove.setElement(die);
        model.setMove(serverMove);
    }

    /**
     * RandomDie Getter
     * This method extract a random die from the bag
     * @param playerAddress is the player IP address
     * @return the required die
     */
    public Die getRandomDieFromBag(String playerAddress) {
        Die die = model.getMatch().getBag().extractDie();
        key = "extractDieFromBag";
        serverMove = moveParser.answerMessage(key);
        serverMove.setPlayerAddress(playerAddress);
        serverMove.setElement(die);
        model.setMove(serverMove);
        return die;
    }

    /**
     * DiePlacer Caller
     * This method ask the player to move a die
     * @param playerAddress is the player IP address
     * @return true if the message has been received, false otherwise
     */
    public boolean askToAddDie(String playerAddress) {
        setInsertedDie(true);
        key = "askToAddDie";
        serverMove = moveParser.requestMessage(key);
        serverMove.setPlayerAddress(playerAddress);
        model.setMove(serverMove);
        if(!move.getMessage().equals("exit"))
            return getNumberOfPlayerMove() == 0;
        else {
            notifyDisconnectedPlayer(playerAddress);
            return false;
        }
    }

    /**
     * ToolCardApply Caller
     * This method calls the application of a ToolCard
     * @param playerAddress is the player IP address
     * @return true if the message has been received, false otherwise
     */
    public boolean askToApplyToolCard(String playerAddress) {
        setInsertedDie(true);
        key = "askToApplyToolCard";
        serverMove = moveParser.requestMessage(key);
        serverMove.setPlayerAddress(playerAddress);
        model.setMove(serverMove);
        if(move.getMessage().equals("exit")) {
            notifyDisconnectedPlayer(playerAddress);
            return false;
        }
        else
            return getNumberOfPlayerMove() == 0;
    }

    /**
     * Move Chooser
     * This method set a message coming
     * @param j is the index of the player on the player Array
     */
    private void chooseMove(int j) {
        key = "chooseMove";
        serverMove = moveParser.requestMessage(key);
        serverMove.setPlayerAddress(virtualViews.get(j).getName());
        model.setMove(serverMove);
    }


    /**
     * ReserveDie Chooser
     * This method allows to chose a die from the reserve
     * @param playerAddress is the player IP address
     */
    public void chooseDieFromReserve(String playerAddress) {
        key = "ReserveChooseDie";
        serverMove = moveParser.requestMessage(key);
        ArrayList<Die> dice = (ArrayList<Die>) getDiceInReserve();
        serverMove.setPlayerAddress(playerAddress);
        serverMove.setElement(dice);
        model.setMove(serverMove);
    }

    /**
     * RoundTrackDie Chooser
     * This method allows to chose a die from the RoundTrack
     * @param playerAddress is the player IP address
     */
    private void chooseDieFromRoundTrack(String playerAddress) {
        key = "RoundTrackChooseDieSubstitution";
        serverMove = moveParser.requestMessage(key);
        ArrayList<Die> dice = (ArrayList<Die>) getRoundTrackDice();
        serverMove.setPlayerAddress(playerAddress);
        serverMove.setElement(dice);
        model.setMove(serverMove);
    }


    /**
     * Position Setter
     * This method sets the position of the placement of a die on the grid
     * @param playerAddress is the player IP address
     * @param grid player's grid
     */
    private void chooseGridPlace(String playerAddress, Grid grid) {
        key = "ReserveChoosePlace";
        serverMove = moveParser.requestMessage(key);
        serverMove.setPlayerAddress(playerAddress);
        serverMove.setElement(grid);
        model.setMove(serverMove);
        key = "gridRow";
        serverMove = moveParser.requestMessage(key);
        serverMove.setPlayerAddress(playerAddress);
        model.setMove(serverMove);
        if(!move.getMessage().equals("exit")) {
            key = "gridColumn";
            setCoordinates(0, getNumberOfPlayerMove()+1);
            serverMove = moveParser.requestMessage(key);
            serverMove.setPlayerAddress(playerAddress);
            model.setMove(serverMove);
            if(!move.getMessage().equals("exit"))
                setCoordinates(1, getNumberOfPlayerMove()+1);
            else {
                setCoordinates(1, 0);
                notifyDisconnectedPlayer(playerAddress);
            }
        } else {
            setCoordinates(0,0);
            setCoordinates(1,0);
            notifyDisconnectedPlayer(playerAddress);
        }
    }

    /**
     * Die Adder
     * This method adds a die to the grid of the player who is playing his turn
     * @param playerAddress address of the player who will add Die to his scheme card
     * @param grid grid of the player
     * @param tmp is the chosen die
     */
    public void addDie(String playerAddress, Grid grid, Die tmp) {
        model.getMatch().getReserve().removeDie(tmp);
        boolean checkedPosition = false;
        do {
            chooseGridPlace(playerAddress,grid);
            if(isConnected(playerAddress)) {
                if ((coordinates[0] != -1 && coordinates[1] != -1) && rules.checkAllRules(tmp, coordinates[0], coordinates[1], grid)) {
                    key = "insertedDie";
                    getPlayerOfCurrentTurn().getGrid().addDie(tmp, coordinates[0], coordinates[1]);
                    setChosenToTrue();
                    serverMove = moveParser.answerMessage(key);
                    serverMove.setPlayerAddress(playerAddress);
                    serverMove.setElement(getPlayerOfCurrentTurn().getGrid());
                    model.setMove(serverMove);
                    checkedPosition = true;
                } else {
                    if (coordinates[0] != -1 && coordinates[1] != -1)
                        showBadConstraint();
                }
            }
        } while((coordinates[0] != -1 && coordinates[1] != -1) && !checkedPosition && isConnected(playerAddress));
        if(checkedPosition)
            setInsertedDie(true);
    }

    /**
     * Bad Constraint Error Thrower
     * This method throws an error message if constraints are not respected
     */
    public void showBadConstraint(){
        key = "uncorrectPlace";
        serverMove = moveParser.answerMessage(key);
        serverMove.setPlayerAddress(getPlayerOfCurrentTurn().getPlayerAddress());
        model.setMove(serverMove);
    }

    /**
     * Connection player checker
     * This method checks if a player is connected to the game
     * @param playerAddress Address of the player
     * @return true if player is connected, false otherwise
     */
    public boolean isConnected(String playerAddress) {
        return !disconnectedPlayerAddresses.contains(playerAddress);
    }

    /**
     * ReserveChosenDie Getter
     * This method gets the chosen die from the reserve
     * @return the required die
     */
    public Die getReserveChosenDie(){
        String playerAddress = getPlayerOfCurrentTurn().getPlayerAddress();
        chooseDieFromReserve(playerAddress);
        if(!move.getMessage().equals("exit") && getNumberOfPlayerMove() >= 0)
            return model.getMatch().getReserve().getDie().get(getNumberOfPlayerMove());
        else if(move.getMessage().equals("exit")) {
            notifyDisconnectedPlayer(playerAddress);
            return null;
        }
        else
            return null;
    }

    /**
     * RoundTrackChosenDie Getter
     * This method gets the chosen die from the RoundTrack
     * @return the required die
     */
    public Die getRoundTrackChosenDie(){
        String playerAddress = getPlayerOfCurrentTurn().getPlayerAddress();
        chooseDieFromRoundTrack(playerAddress);
        chooseDieFromRoundTrack(playerAddress);
        if(!move.getMessage().equals("exit"))
            return model.getMatch().getRoundTrack().getRemainingDice().get(getNumberOfPlayerMove());
        else {
            notifyDisconnectedPlayer(playerAddress);
            return null;
        }
    }

    /**
     * NumberMove Getter
     * This method gets the number of move done by a player
     * @return required player move
     */
    public int getNumberOfPlayerMove() {
        return Integer.parseInt(move.getMessage()) - 1;
    }


    /**
     * IncrementDecrementValue Caller
     * @return true if is possible to decrement the value of the die, false otherwise
     */
    public boolean getChoseDieValueOption() {
        String playerAddress = getPlayerOfCurrentTurn().getPlayerAddress();
        Grid grid = getPlayerOfCurrentTurn().getGrid();
        showStatus(playerAddress,grid);
        key = "decrementOrEnhancement";
        serverMove = moveParser.requestMessage(key);
        serverMove.setPlayerAddress(playerAddress);
        model.setMove(serverMove);
        int number = getNumberOfPlayerMove()+1;
        if(number == 1) {
            key = "ToolCardChooseDieEnhance";
            serverMove = moveParser.requestMessage(key);
            serverMove.setPlayerAddress(playerAddress);
            model.setMove(serverMove);
            return false;
        }
        else {
            key = "ToolCardChooseDieDecrement";
            serverMove = moveParser.requestMessage(key);
            serverMove.setPlayerAddress(playerAddress);
            model.setMove(serverMove);
            return true;
        }
    }

    /**
     * Position Getter
     * This method gets the coordinates of a cell of the grid
     * @return cell coordinates
     */
    private int[] getPositionCoordinates() {
        String playerAddress = getPlayerOfCurrentTurn().getPlayerAddress();
        Grid grid = getPlayerOfCurrentTurn().getGrid();
        showStatus(playerAddress,grid);
        int row;
        int column;
        row = getGridRow(playerAddress);
        if(row > 0) {
            column = getGridColumn(playerAddress);
            if(column > 0)
                return new int[] {row-1, column-1};
            else
                return new int[] {-1, -1};
        }
        else
            return new int[] {-1, -1};
    }

    /**
     * Grid Column Getter
     * This method gets a column of a player's grid
     * @param playerAddress is the IP Address of a player
     * @return the message that confirm the not execution of the action
     */
    private int getGridColumn(String playerAddress) {
        key = "gridColumn";
        serverMove = moveParser.requestMessage(key);
        serverMove.setPlayerAddress(playerAddress);
        model.setMove(serverMove);
        if(!move.getMessage().equals("exit"))
            return getNumberOfPlayerMove()+1;
        else {
            notifyDisconnectedPlayer(playerAddress);
            return 0;
        }
    }

    /**
     * Grid Row Getter
     * This method gets a row of a player's grid
     * @param playerAddress is the IP Address of a player
     * @return the message that confirm the not execution of the action
     */
    private int getGridRow(String playerAddress) {
        key = "gridRow";
        serverMove = moveParser.requestMessage(key);
        serverMove.setPlayerAddress(playerAddress);
        model.setMove(serverMove);
        if(!move.getMessage().equals("exit"))
            return getNumberOfPlayerMove()+1;
        else {
            notifyDisconnectedPlayer(playerAddress);
            return 0;
        }
    }

    /**
     * NewPosition Getter
     * This method gets the new position of die we wanna place after the application of a ToolCard
     * @return new die coordinates
     */
    public int[] getNewDiePositionCoordinates() {
        String playerAddress = getPlayerOfCurrentTurn().getPlayerAddress();
        key = "ToolCardChooseNewDiePosition";
        serverMove = moveParser.requestMessage(key);
        serverMove.setPlayerAddress(playerAddress);
        model.setMove(serverMove);
        return getPositionCoordinates();
    }

    /**
     * ChangePosition Getter
     * This method gets the coordinates of the change of position of a die
     * @return the position coordinates
     */
    public int [] getDiePositionChangeCoordinates() {
        String playerAddress = getPlayerOfCurrentTurn().getPlayerAddress();
        Grid grid = getPlayerOfCurrentTurn().getGrid();
        showStatus(playerAddress,grid);
        key = "ToolCardChooseDiePosition";
        serverMove = moveParser.requestMessage(key);
        serverMove.setPlayerAddress(playerAddress);
        model.setMove(serverMove);
        return getPositionCoordinates();
    }


    /**
     * ToolCard Buyer
     * This method allows to buy a ToolCard
     * @param playerAddress IP address of the player
     * @param grid grid of the player
     */
    public void buyToolCard(String playerAddress,Grid grid) {
        ToolCard tmp = getToolCardChoice(playerAddress);
        if(tmp != null && toolCardManager.applyRequiredToolCard(tmp)) {
            notifyToolCardSuccess(playerAddress);
            showStatus(playerAddress,grid);
            getPlayerOfCurrentTurn().decrementNumFavCounter(tmp.getPrice());
        }
        else if(isConnected(playerAddress)) {
            notifyToolCardFailed(playerAddress);
            if(tmp != null)
                buyToolCard(playerAddress,grid);
        }
    }

    /**
     * ToolCard Choice Getter
     * This method returns the selection of a chosen ToolCard
     * @param playerAddress IP address of the player
     * @return the chosen ToolCard
     */
    private ToolCard getToolCardChoice(String playerAddress) {
        key = "buyToolCard";
        serverMove = moveParser.requestMessage("buyToolCard");
        serverMove.setPlayerAddress(playerAddress);
        serverMove.setElement(model.getMatch().getRoundTrack().getToolCards());
        model.setMove(serverMove);
        if(!move.getMessage().equals("exit") && getNumberOfPlayerMove() >= 0)
            return getToolCardFromIndex(getNumberOfPlayerMove());
        else
            return null;
    }

    /**
     * ToolCard Application Notifier
     * This method notifies the player if a ToolCard has been correctly applied
     * @param playerAddress IP address of the player
     */
    private void notifyToolCardSuccess(String playerAddress) {
        key = "toolCardSuccessfullyApplied";
        serverMove = moveParser.answerMessage(key);
        serverMove.setPlayerAddress(playerAddress);
        model.setMove(serverMove);
    }

    /**
     * ToolCard Application Notifier
     * This method notifies the player if a ToolCard has been correctly applied
     * @param playerAddress IP address of the player
     */
    private void notifyToolCardFailed(String playerAddress) {
        key = "toolCardFailed";
        serverMove = moveParser.answerMessage(key);
        serverMove.setPlayerAddress(playerAddress);
        model.setMove(serverMove);
    }

    /**
     * ToolCard Getter
     * This method catch a ToolCard using his array index
     * @param index is the index of the ToolCard in the array
     * @return the required toolcard
     */
    private ToolCard getToolCardFromIndex(int index) {
        return model.getMatch().getRoundTrack().getToolCards().get(index);
    }

    /**
     * WhoIsPlaying Getter
     * This method gets the current playing player
     * @return the required player
     */
    public Player getPlayerOfCurrentTurn() {
        return model.getMatch().getRoundTrack().getPlayerOfCurrentTurn();
    }

    /**
     * Status Updater
     * This method shows tha status of the match
     * @param playerAddress the address of the player on this turn.
     * @param grid Grid of the current turn player
     */
    private void showStatus(String playerAddress, Grid grid) {
        key = "yourSchemeCard";
        serverMove = moveParser.answerMessage(key);
        serverMove.setPlayerAddress(playerAddress);
        serverMove.setElement(grid);
        model.setMove(serverMove);
        key = "yourPrivateObjectiveCard";
        serverMove = moveParser.answerMessage(key);
        serverMove.setPlayerAddress(playerAddress);
        serverMove.setElement(getPlayerOfCurrentTurn().getPrivateObjectiveCard());
        model.setMove(serverMove);
        showReserve();
    }

    /**
     * Reserve Viewer
     * This method shows the status of the reserve
     */
    public void showReserve(){
        key = "diceInReserve";
        serverMove = moveParser.answerMessage(key);
        serverMove.setPlayerAddress(getPlayerOfCurrentTurn().getPlayerAddress());
        serverMove.setElement(getDiceInReserve());
        model.setMove(serverMove);
    }

    /**
     * RoundTrack Dice Getter
     * This method shows dice that are in the RoundTrack
     * @param playerAddress IP Address of the player
     */
    private void showRoundTrackDie(String playerAddress) {
        key = "getRoundTrackDice";
        serverMove = moveParser.answerMessage(key);
        serverMove.setPlayerAddress(playerAddress);
        serverMove.setElement(getRoundTrackDice());
        model.setMove(serverMove);
    }

    /**
     * This method asks for the new position of the die selected from the player.
     * @param playerAddress Address of the player in current turn
     * @return Coordinates of new position of the die
     */
    public int[] askForNewPositionDie(String playerAddress) {
        key = "ToolCardChooseNewDiePosition";
        serverMove = moveParser.requestMessage(key);
        serverMove.setPlayerAddress(playerAddress);
        model.setMove(serverMove);
        return getPositionCoordinates();
    }

    /**
     * This method shows to the player the message that the selected die is not correct because its colour is not present
     * in colours of the round track Die.
     * @param playerAddress Address of the player in current turn
     */
    public void showInvalidColourDie(String playerAddress) {
        key = "uncorrectDieColour";
        serverMove = moveParser.requestMessage(key);
        serverMove.setPlayerAddress(playerAddress);
        model.setMove(serverMove);
    }

    /**
     * This method returns the first or the second die whose position will be changed.
     * @param playerAddress Address of the player in current turn
     * @param num This number assumes 1 value if player has to choose the first die, 2 otherwise.
     * @return the die whose position will be changed
     */
    public int[] getDieToMoveFromSchemeCard(String playerAddress, int num) {
        if(num == 1)
            key = "ToolCardChooseFirstDiePosition";
        else
            key = "ToolCardChooseSecondDiePosition";
        showRoundTrackDie(playerAddress);
        serverMove = moveParser.requestMessage(key);
        serverMove.setPlayerAddress(playerAddress);
        model.setMove(serverMove);
        return getPositionCoordinates();
    }




    /**
     * This method asks the player if he wants to use the die whose number is changed
     * @param die chosen die to place
     * @return true if the player wants to use that die
     */
    public boolean askForUseNewDiceValue(Die die) {
        key = "useNewValueDie";
        serverMove = moveParser.requestMessage(key);
        serverMove.setPlayerAddress(getPlayerOfCurrentTurn().getPlayerAddress());
        serverMove.setElement(die);
        model.setMove(serverMove);
        if(!move.getMessage().equals("exit"))
            return getNumberOfPlayerMove() == 0;
        else
            return false;
    }

    /**
     * RoundTrackDie Getter
     * This method gets the ArrayList containing all RoundTrack Die
     * @return required ArrayList
     */
    public List<Die> getRoundTrackDice() {
        return model.getMatch().getRoundTrack().getRemainingDice();
    }

    /**
     * ReserveDie Getter
     * This method gets the ArrayList containing all Reserve Die
     * @return required ArrayList
     */
    public List<Die> getDiceInReserve() {
        return model.getMatch().getReserve().getDie();
    }

    /**
     * Model Getter
     * This method gets the current model status
     * @return the current model status
     */
    public Model getModel() {
        return model;
    }

    /**
     * RoundTrackDie Adder
     * This method allows to add a die into the RoundTrack
     * @param die  is the chosen/left die to add to the RoundTrack
     */
    public void addDieInRoundTrack(Die die) {
        model.getMatch().getRoundTrack().addDie(die);
    }

    /**
     * RoundTrackDie Remover
     * This method allows to remove a die into the RoundTrack
     * @param die  is the chosen die to remove from the RoundTrack
     */
    public void removeDieFromRoundTrack(Die die) {
        model.getMatch().getRoundTrack().removeDie(die);
    }

    /**
     * ReserveDie Adder
     * This method allows to add a die into the reserve
     * @param die  is the chosen/left die to add to the reserve
     */
    public void addDieInReserve(Die die) {
        model.getMatch().getReserve().addDie(die);
    }

    /**
     * ReserveDie Remover
     * This method allows to remove a die from the reserve
     * @param die  is the chosen/left die to remove from the reserve
     */
    public void removeDieFromReserve(Die die) {
        model.getMatch().getReserve().removeDie(die);
    }

    /**
     * Updater
     * This method calls the update of the View after the change status on the Model
     * @param o caller of the status change
     * @param arg is the object involved into the Observer Interface
     */
    @Override
    public void update(Observable o, Object arg) {
        if(!disconnectedPlayerAddresses.contains(((VirtualView) o).getMove().getPlayerAddress()) &&
                !((VirtualView) o).getMove().getMessage().equals("S")) {
            if (moveParser.checkInput(((VirtualView) o).getMove(), getKey())) {
                move = ((VirtualView) o).getMove();
            } else {
                showInvalidInput(((VirtualView) o).getMove().getPlayerAddress());
                model.setMove(serverMove);
            }
        }
    }

    /**
     * ValidInput Error
     * This method shows the error about a wrong input date
     * @param playerAddress is the IP of the player that sent the wrong input
     */
    public void showInvalidInput(String playerAddress) {
        ChoseInputMove choseInputMove = moveParser.answerMessage("invalidInput");
        choseInputMove.setPlayerAddress(playerAddress);
        model.setMove(choseInputMove);
    }

    /**
     * Key Getter
     * This method get the required object key
     * @return the required key
     */
    private String getKey() {
        return key;
    }

    /**
     * ChooseCoordinates Setter
     * This method is used to set a chosen coordinate
     * @param value boolean value to set
     */
    public void setInsertedDie(boolean value) {
        insertedDie = value;
    }

    /**
     * Coordinates Setter
     * This method is used to set a die into the grid converting the "1 starting" counting of cells to the
     * "0 starting" notation of the grid
     * @param index index of the grid equivalent to the number
     * @param number number of the chosen coordinate
     */
    private void setCoordinates(int index, int number) {
        coordinates[index] = number-1;
    }

}