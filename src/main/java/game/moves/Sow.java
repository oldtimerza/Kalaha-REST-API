package game.moves;

import game.GameBoard;
import game.Pit;
import game.Player;
import game.Stone;

import java.util.ArrayList;
import java.util.List;

public class Sow  implements Move{

    private int pitNumber;

    public Sow(int pitNumber) {
        this.pitNumber = pitNumber;
    }

    @Override
    public void execute(Player player, GameBoard gameBoard) {
        Pit pit = gameBoard.getPitAt(pitNumber);
        List<Stone> stones = pit.removeStones();
        for(Stone stone : stones){
            pit = pit.getNextPit();
            if(pit == null){
                return;
            }
            List<Stone> stonesToAdd = new ArrayList<>();
            stonesToAdd.add(stone);
            pit.addStones(stonesToAdd);
        }
    }
}
