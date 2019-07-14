package it.polimi.se2018.model;
import java.util.Random;
import java.util.logging.Logger;

/**
 * Bag Class
 * This class is used to create the bag containing all Die that will be used during the match
 * @author Federico Lichinchi
 */

public class Bag {

    private Die[][] die;
    private int dieNum;
    private int lastRow;
    private int lastColumn;

    /**
     * Die Bag
     * Bag Class Constructor
     * Initially, the bag has 3 groups of Die for each color of the game
     */
    public Bag() {
        String[] color = new String[5];
        color[0] = "Red";
        color[1] = "Yellow";
        color[2] = "Green";
        color[3] = "Blue";
        color[4] = "Purple";
        die = new Die[5][18];
        for(int i = 0; i < color.length; i++) {
            for(int j = 0; j < 6; j++) {
                die[i][j] = new Die(color[i],j+1);
            }
            for(int k = 6; k < 12; k++) {
                die[i][k] = new Die(color[i], k - 5);
            }
            for(int l = 12; l < 18; l++) {
                die[i][l] = new Die(color[i], l - 11);
            }
        }
        dieNum = 90;
        compound();
    }

    /**
     * Die Extractor
     * Method that casually extracts a die from the bag
     * @return Die extracted from the bag
     */
    public Die extractDie() {
        if(dieNum > 0) {
            Random rand = new Random();
            int x;
            int y;
            do {
                x = rand.nextInt(4);
                y = rand.nextInt(17);
            } while (die[x][y] == null);
            lastRow = x;
            lastColumn = y;
            dieNum--;
            Die tmp = new Die(die[x][y].getDieColor(), die[x][y].getDieNumber());
            die[x][y] = null;
            return tmp;
        }
        else {
            Logger.getLogger("There are no Die in the bag");
            return null;
        }
    }

    /**
     * Die Getter
     * This method returns a Die from the Die array of the Bag, given the column and the row of the matrix
     * @param line Line number of Bag game Die array
     * @param column Column number of Bag game Die array
     * @return Die the chosen die
     */
    public Die getDie(int line, int column) {
        return die[line][column];
    }

    /**
     *@throws IllegalArgumentException if it is not possible to insert a die in the Die bag because the bag is already full of Die
     * @param die the die to be added grid
     */
    public void reInsertDie(Die die) {
        dieNum++;
        this.die[lastRow][lastColumn] = die;
    }



    /**
     * Die Value Getter
     * This method returns the number of game Die in the bag
     * @return Number of die
     */
    public int getDieNum() {
        return dieNum;
    }

    /**
     * Random Mixer
     * This method mixes the game Die in the bag
     */
    public void compound() {
        int randNum;
        Random rand = new Random();
        Die d1;
        Die d2;
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 18; j++) {
                randNum = rand.nextInt(17);
                d1 = new Die(die[i][j].getDieColor(), die[i][j].getDieNumber());
                d2 = new Die(die[i][randNum].getDieColor(), die[i][randNum].getDieNumber());
                die[i][j] = d2;
                die[i][randNum] = d1;
            }
        }
        for(int k = 0; k < 5; k++) {
            randNum = rand.nextInt(4);
            if(k != randNum) {
                for(int l = 0; l < 18; l++) {
                    d1 = new Die(die[k][l].getDieColor(), die[k][l].getDieNumber());
                    d2 = new Die(die[randNum][l].getDieColor(), die[randNum][l].getDieNumber());
                    die[k][l] = d2;
                    die[randNum][l] = d1;
                }
            }
        }
    }
}
