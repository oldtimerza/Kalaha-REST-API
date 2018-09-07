package kalaha.game.builders;

import kalaha.game.GameBoard;
import kalaha.game.Player;

import java.util.List;

public class BoardDirector {
    private BoardBuilder boardBuilder;

    public BoardDirector(BoardBuilder boardBuilder) {
        this.boardBuilder = boardBuilder;
    }

    public GameBoard getBoard(List<Player> players) throws Exception {
        boardBuilder.buildStones();
        boardBuilder.buildPits(players);
        boardBuilder.buildKalahas(players);
        return boardBuilder.construct(players);
    }
}
