package game;

import game.moves.Sow;
import org.junit.Test;
import org.mockito.Mockito;

public class MovesTest {
    @Test
    public void sowShouldCallGameBoardSow(){
        int pitNumber = 1;
        Sow sow = new Sow(pitNumber);
        GameBoard mockGameBoard = Mockito.mock(GameBoard.class);
        Player player = new Player();
        sow.execute(player, mockGameBoard);
        Mockito.verify(mockGameBoard).Sow(player, pitNumber);
    }
}
