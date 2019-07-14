package it.polimi.se2018.model;
import java.util.ArrayList;
import java.util.List;

/**
 * Reserve Class
 * This Class creates the Die reserve
 * @author Federico Lichinchi
 */
public class Reserve {

    private int len;
    private ArrayList<Die> die;

    /**
     * Reserve Class Constructor
     * This method initialize the Die Reserve
     * @param dice Array of Die to add in the reserve
     * @throws CloneNotSupportedException if is not possible to clone a die
     */
    public Reserve(List<Die> dice) throws CloneNotSupportedException {
        len = dice.size();
        die = new ArrayList<>(dice.size());
        for(int i = 0; i < dice.size(); i++) {
            die.add(dice.get(i).clone());
        }

    }

    /**
     * NumberOfDice Getter
     * This method returns the number of Die in the Reserve
     * @return number of Die
     */
    public int getLen() {
        return len;
    }

    /**Die Getter
     * This method returns the array of Die in the Reserve
     * @return Array of Die
     */
    public List<Die> getDie() {
        return die;
    }

    /**
     * Die Remover
     * This method removes a die from the Reserve
     * @param die the die to delete from the Reserve
     */
    public void removeDie(Die die) {
        for(Die dice: this.die)
            if (dice.getDieNumber() == die.getDieNumber() && dice.getDieColor().equals(die.getDieColor())) {
                this.die.remove(dice);
                break;
            }
    }

    /**
     * Die Adder
     * This method adds a new Die in the Reserve
     * @param die the die to add in the Reserve
     */
    public void addDie(Die die) {
        this.die.add(die);
        len = this.die.size();
    }

    /**
     * Reserve Sweeper
     * This method clear the reserve
     */
    public void toEmptyDice() {
        len = 0;
        this.die.clear();
    }

}
