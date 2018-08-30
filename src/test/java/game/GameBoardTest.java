package game;

import game.moves.Move;
import game.moves.Sow;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;

public class GameBoardTest {

   GameBoard gameBoard;

   List<Player> players;

   GameState gameState;

   @Before
   public void init(){
       players = new ArrayList<>();
       players.add(new Player());
       players.add(new Player());
       gameState = new GameState();
       gameState.currentPlayer = players.get(0);
       gameBoard = new GameBoardFactory().createGameBoard(players, gameState);
   }

    @Test(expected = NotPlayersTurnException.class)
    public void shouldStopNonCurrentPlayerFromMakingMove() throws NotPlayersTurnException {
        Move move = Mockito.mock(Sow.class);
        gameBoard.makeMove(players.get(1), move);
    }

    @Test
    public void shouldExecuteMoveThatIsMadeByCurrentPlayer() throws NotPlayersTurnException {
        Move move = Mockito.mock(Sow.class);
        gameBoard.makeMove(players.get(0), move);
        Mockito.verify(move).execute(players.get(0), gameBoard);
    }

    @Test
    public void shouldSowTheStonesByAddingOneToEachSubsequentPit() throws NotPlayersTurnException {
        int pitNumber = players.get(0).getPits().get(0).getNumber();
        Move sow = new Sow(pitNumber);
        GameBoard actual = gameBoard.makeMove(players.get(0), sow);
        Assert.assertTrue(gameBoard.getMoves().contains(sow));
        Assert.assertThat(actual.getPits().get(0).getStones().size(), equalTo(0));
        for(int i=1; i < 6; i++) {
            Assert.assertThat(actual.getPits().get(i).getStones().size(), equalTo(7));
        }
        Assert.assertThat(actual.getKalahas().get(0).getStones().size(), equalTo(1));
    }
}
