package game.moves;

import game.GameBoard;
import game.Pit;
import game.Player;
import game.Stone;
import game.rules.Affirmation;
import game.rules.Check;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;

public class Sow  implements Move{

    @Autowired
    public Check<DropStone> check;

    private final int maxAttempts = 100;

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
            DropStone dropStone = new DropStone(stone, pit);
            Affirmation affirmation = check.thatPlayer(player).given(gameBoard).isAllowed(dropStone);
            int attempt = 0;
            while(!affirmation.ok() && pit != null && attempt < maxAttempts){
                pit=pit.getNextPit();
                dropStone = new DropStone(stone, pit);
                affirmation = check.isAllowed(dropStone);
                attempt++;
            }
            affirmation.thenExecute();
        }
    }
}
