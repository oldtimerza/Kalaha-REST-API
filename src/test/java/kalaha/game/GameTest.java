package kalaha.game;

import kalaha.game.moves.Move;
import kalaha.game.moves.Sow;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

public class GameTest {
    GameBoard gameBoard;

    List<Player> players;

    GameState state;

    Game game;

    @Before
    public void init(){
        players = new ArrayList<>();
        players.add(new Player(0));
        players.add(new Player(1));
        state = new GameState();
        state.setCurrentPlayer(players.get(0));
        state.setNextPlayer(players.get(1));
        gameBoard = Mockito.mock(GameBoard.class);
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

    @Test
    public void shouldEndGameIfAPlayerHasNoStonesLeftInPit() throws NotPlayersTurnException {
        Move move = Mockito.mock(Sow.class);
        Player player = players.get(0);
        Pit pit = Mockito.mock(Pit.class);
        when(pit.getStones()).thenReturn(new ArrayList<>());
        player.addPit(pit);
        game.makeMove(player, move);
        Assert.assertThat(state.getStatus(), Matchers.equalTo(Status.ENDED));
    }
}
