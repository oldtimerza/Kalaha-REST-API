package game;

import java.util.ArrayList;
import java.util.List;

public class Pit {

    private Player owner;

    private List<Stone> stones;

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

    public void setNextPit(Pit nextPit) {
        this.nextPit = nextPit;
    }

    public Pit(List<Stone> stones, Player player){
        this.stones = stones;
        this.owner = player;
    }

    public void addStones(List<Stone> stones){
       this.stones.addAll(stones);
    }

    public List<Stone> removeStones(){
        List<Stone> copyStones = stones.subList(0, stones.size());
        stones = new ArrayList<>();
        return copyStones;
    }

}
