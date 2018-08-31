package game;

import game.moves.DropStone;
import game.moves.Sow;
import game.rules.Affirmation;
import game.rules.Allowed;
import game.rules.Check;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class MovesTest {

    List<Player> players;
    GameState state;
    GameBoard gameBoard;
    Game game;

    @Before
    public void init(){
        players = new ArrayList<>();
        players.add(new Player());
        players.add(new Player());
        state = Mockito.mock(GameState.class);
        when(state.getCurrentPlayer()).thenReturn(players.get(0));
        gameBoard = Mockito.mock(GameBoard.class);
        game = new Game(gameBoard, state, players);
    }

    @Test
    public void dropStoneShouldAddTheStoneToThePit(){
        Pit pit = Mockito.mock(Pit.class);
        Stone stone = Mockito.mock(Stone.class);
        DropStone dropStone = new DropStone(stone, pit);
        dropStone.execute(players.get(0), game);
        List<Stone> expectedStones = new ArrayList<>();
        expectedStones.add(stone);
        verify(pit, atLeastOnce()).addStones(ArgumentMatchers.eq(expectedStones));
    }

    @Test
    public void sowShouldDropStonesIntoSubsequentPits() throws NotPlayersTurnException {
        int pitNumber = 0;
        List<Stone> stones = new ArrayList<>();
        stones.add(new Stone());
        stones.add(new Stone());
        stones.add(new Stone());
        List<Pit> pits = new ArrayList<>();
        int numberOfPits = 3;
        for(int i = 0; i < numberOfPits; i++){
            Pit pit = new Pit(new ArrayList<>(stones), players.get(0), i + 1);
            pits.add(pit);
            if(pits.size() > 1) {
                pits.get(i - 1).setNextPit(pit);
            }
        }
        Pit starterPit = new Pit(stones, players.get(0), pitNumber);
        starterPit.setNextPit(pits.get(0));
        when(gameBoard.getPitAt(pitNumber)).thenReturn(starterPit);
        Sow sow = new Sow(pitNumber);
        Check<DropStone> check = (Check<DropStone>) Mockito.mock(Check.class);
        Affirmation mockAllowed = Mockito.mock(Allowed.class);
        when(mockAllowed.ok()).thenReturn(true);
        when(check.given(ArgumentMatchers.any(Game.class))).thenReturn(check);
        when(check.thatPlayer(ArgumentMatchers.any(Player.class))).thenReturn(check);
        when(check.isAllowed(ArgumentMatchers.any(DropStone.class))).thenReturn(mockAllowed);
        sow.check = check;
        sow.execute(players.get(0), game);
        Assert.assertThat(starterPit.getStones().size(), Matchers.equalTo(0));
        verify(mockAllowed, atLeast(numberOfPits)).thenExecute();
    }
}
