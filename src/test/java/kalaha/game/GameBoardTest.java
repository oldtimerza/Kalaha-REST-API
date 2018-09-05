package kalaha.game;


import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

public class GameBoardTest {

    @Test
    public void shouldReturnTheCorrectPitGivenThePitNumber() throws Exception {
        List<Player> players = new ArrayList<Player>();
        players.add(new Player(0));
        players.add(new Player(1));
        List<Pit> pits = new ArrayList<>();
        Pit pit = Mockito.mock(Pit.class);
        Integer pitNumber = 1;
        when(pit.getNumber()).thenReturn(pitNumber);
        pits.add(pit);
        GameBoard gameBoard = new GameBoard(pits, null);
        Pit actual = gameBoard.getPitAt(1);
        Assert.assertThat(actual, Matchers.equalTo(gameBoard.getPits().get(0)));
    }

}
