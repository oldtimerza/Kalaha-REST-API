package game.moves;

import game.*;

import java.util.ArrayList;
import java.util.List;

public class DropStone implements Move {

    private Stone stone;

    private Pit pit;

    public DropStone(Stone stone, Pit pit){
        this.stone = stone;
        this.pit = pit;
    }

    @Override
    public void execute(Player player, Game game) {
        List<Stone> stones = new ArrayList<>();
        stones.add(stone);
        pit.addStones(stones);
    }

    public Stone getStone() {
        return stone;
    }

    public Pit getPit() {
        return pit;
    }
}
