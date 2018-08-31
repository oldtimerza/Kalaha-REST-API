package game;

import game.moves.DropStone;
import game.rules.Check;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class RulesTest {
    @Test
    public void shouldExecuteDropStoneIfNotDroppingIntoOpponentKalaha(){
        List<Player> players = new ArrayList<>();
        players.add(new Player());
        players.add(new Player());
        GameBoard mockGameBoard = Mockito.mock(GameBoard.class);
        List<Kalaha> kalahas = new ArrayList<>();
        Pit pit = new Pit(null, players.get(0), 0);
        Kalaha kalaha = new Kalaha(pit);
        kalahas.add(kalaha);
        when(mockGameBoard.getKalahas()).thenReturn(kalahas);
        DropStone dropStone = Mockito.mock(DropStone.class);
        when(dropStone.getPit()).thenReturn(pit);
        Check.thatPlayer(players.get(0)).given(mockGameBoard).isAllowed(dropStone).thenExecute();
        verify(dropStone).execute(players.get(0), mockGameBoard);
    }

    @Test
    public void shouldNotExecuteDropStoneIfDroppingIntoOpponentKalaha(){
        List<Player> players = new ArrayList<>();
        players.add(new Player());
        players.add(new Player());
        GameBoard mockGameBoard = Mockito.mock(GameBoard.class);
        List<Kalaha> kalahas = new ArrayList<>();
        Pit pit = new Pit(null, players.get(0), 0);
        Kalaha kalaha = new Kalaha(pit);
        kalahas.add(kalaha);
        when(mockGameBoard.getKalahas()).thenReturn(kalahas);
        DropStone dropStone = Mockito.mock(DropStone.class);
        when(dropStone.getPit()).thenReturn(new Pit(null, players.get(1), 1));
        Check.thatPlayer(players.get(1)).given(mockGameBoard).isAllowed(dropStone).thenExecute();
        verify(dropStone, never()).execute(players.get(0), mockGameBoard);
    }

}
