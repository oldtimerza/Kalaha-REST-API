package game;

import game.moves.Move;
import game.moves.Sow;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class GameTest {
    GameBoard gameBoard;

    List<Player> players;

    Game game;

    @Before
    public void init() throws Exception {
        players = new ArrayList<>();
        players.add(new Player());
        players.add(new Player());
        GameState state = new GameState();
        state.setCurrentPlayer(players.get(0));
        state.setNextPlayer(players.get(1));
        gameBoard = new GameBoardFactory().createGameBoard(players);
        game = new Game(gameBoard, state ,players);
    }

    @Test(expected = NotPlayersTurnException.class)
    public void shouldStopNonCurrentPlayerFromMakingMove() throws NotPlayersTurnException {
        Move move = Mockito.mock(Sow.class);
        game.makeMove(players.get(1), move);
    }

    @Test
    public void shouldExecuteMoveThatIsMadeByCurrentPlayer() throws NotPlayersTurnException {
        Move move = Mockito.mock(Sow.class);
        game.makeMove(players.get(0), move);
        Mockito.verify(move).execute(players.get(0), game);
    }
}
