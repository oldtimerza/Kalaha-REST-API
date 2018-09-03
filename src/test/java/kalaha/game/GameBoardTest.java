package kalaha.game;


import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class GameBoardTest {

    @Test
    public void shouldReturnTheCorrectPitGivenThePitNumber() throws Exception {
        List<Player> players = new ArrayList<Player>();
        players.add(new Player(0));
        players.add(new Player(1));
        GameBoard gameBoard = new GameBoardFactory().createGameBoard(players);
        Pit actual = gameBoard.getPitAt(1);
        Assert.assertThat(actual, Matchers.equalTo(gameBoard.getPits().get(0)));
    }

}
