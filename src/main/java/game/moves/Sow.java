package game.moves;

import game.*;
import game.rules.Affirmation;
import game.rules.Check;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

public class Sow  implements Move{

    @Autowired
    public Check<DropStone> dropStoneCheck;

    @Autowired
    public Check<CaptureOpponentsStones> captureOpponentsStonesCheck;

    private final int maxAttempts = 100;

    private int pitNumber;

    public Sow(int pitNumber) {
        this.pitNumber = pitNumber;
    }

    @Override
    public void execute(Player player, Game game) {
        Pit pit = game.getGameBoard().getPitAt(pitNumber);
        List<Stone> stones = pit.removeStones();
        Stone stone = null;
        for(int i=0; i<stones.size(); i++){
            stone = stones.get(i);
            pit = pit.getNextPit();
            DropStone dropStone = new DropStone(stone, pit);
            Affirmation affirmation = dropStoneCheck.thatPlayer(player).given(game).isAllowed(dropStone);
            int attempt = 0;
            while(!affirmation.ok() && pit != null && attempt < maxAttempts){
                pit=pit.getNextPit();
                dropStone = new DropStone(stone, pit);
                affirmation = dropStoneCheck.isAllowed(dropStone);
                attempt++;
            }
            affirmation.thenExecute();
        }
        captureOpponentsStonesCheck.thatPlayer(player).given(game).isAllowed(new CaptureOpponentsStones(pit)).thenExecute();
    }
}
