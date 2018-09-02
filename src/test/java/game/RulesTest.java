package game;

import game.moves.CaptureOpponentsStones;
import game.moves.DropStone;
import game.moves.Move;
import game.rules.CaptureStonesRules;
import game.rules.Check;
import game.rules.DropStoneRules;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class RulesTest {

    Game game;
    GameBoard gameBoard;
    List<Player> players;

    @Before
    public void init(){
        game = Mockito.mock(Game.class);
        gameBoard = Mockito.mock(GameBoard.class);
        when(game.getGameBoard()).thenReturn(gameBoard);
        players = new ArrayList<>();
        players.add(Mockito.mock(Player.class));
        players.add(Mockito.mock(Player.class));
    }

    @Test
    public void shouldExecuteDropStoneIfNotDroppingIntoOpponentKalaha(){
        List<Kalaha> kalahas = new ArrayList<>();
        Pit pit = new Pit(null, players.get(0), 0);
        Kalaha kalaha = new Kalaha(pit);
        kalahas.add(kalaha);
        when(gameBoard.getKalahas()).thenReturn(kalahas);
        DropStone dropStone = Mockito.mock(DropStone.class);
        when(dropStone.getPit()).thenReturn(pit);
        new Check(new DropStoneRules()).thatPlayer(players.get(0)).given(game).isAllowed(dropStone).thenExecute();
        verify(dropStone).execute(players.get(0), game);
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
        new Check(new DropStoneRules()).thatPlayer(players.get(1)).given(game).isAllowed(dropStone).thenExecute();
        verify(dropStone, never()).execute(players.get(0), game);
    }

    @Test
    public void shouldOnlyAllowCaptureOfStonesIfTheLastStoneWasDroppedIntoAPitIOwn(){
        Player player = players.get(0);
        Move captureStones = Mockito.mock(CaptureOpponentsStones.class);
        Pit pit = Mockito.mock(Pit.class);
        when(player.ownsPit(pit)).thenReturn(true);
        new Check(new CaptureStonesRules(pit)).thatPlayer(player).given(game).isAllowed(captureStones).thenExecute();
        verify(captureStones).execute(player, game);
    }

    @Test
    public void shouldNotAllowCaptureOfStonesIfTheStoneWasDroppedIntoAnOpponentsPit(){
        Player player = players.get(0);
        Move captureStones = Mockito.mock(CaptureOpponentsStones.class);
        Pit pit = Mockito.mock(Pit.class);
        when(player.ownsPit(pit)).thenReturn(false);
        new Check(new CaptureStonesRules(pit)).thatPlayer(player).given(game).isAllowed(captureStones).thenExecute();
        verify(captureStones, never()).execute(player, game);
    }

}
