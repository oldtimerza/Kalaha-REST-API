package kalaha.game;

import java.util.ArrayList;
import java.util.List;

public class Pit {

    private Player owner;

    private List<Stone> stones;

    private int number;

    private Pit nextPit;

    public Player getOwner() {
        return owner;
    }

    public List<Stone> getStones() {
        return stones;
    }

    public Pit getNextPit() {
        return nextPit;
    }

    public int getNumber() {
        return number;
    }

    public void setNextPit(Pit nextPit) {
        this.nextPit = nextPit;
    }

    public Pit(List<Stone> stones, Player owner, int number){
        this.stones = stones;
        this.owner = owner;
        this.number = number;
    }

    public void addStones(List<Stone> stones){
       this.stones.addAll(stones);
    }

    public List<Stone> removeStones(){
        List<Stone> copyStones = new ArrayList<>(stones);
        stones = new ArrayList<>();
        return copyStones;
    }

}
