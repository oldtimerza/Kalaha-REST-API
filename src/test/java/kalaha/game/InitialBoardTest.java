package kalaha.game;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;

public class InitialBoardTest {
    @Test
    public void shouldCreateAnInitialBoardSetup() throws Exception {
        List<Player> players = new ArrayList<>();
        players.add(new Player(0));
        players.add(new Player(1));
        InitialBoard builder = new InitialBoard();
        builder.buildStones();
        builder.buildPits(players);
        builder.buildKalahas(players);
        GameBoard gameBoard = builder.construct(players);

        Assert.assertThat(gameBoard.getKalahas().size(), equalTo(2));
        Assert.assertThat(gameBoard.getPits().size(), equalTo(14));
        Pit pit = gameBoard.getPits().get(0);
        assertPit(pit, players.get(0));
        for(int i =0; i < 5; i ++){
            pit = pit.getNextPit();
            assertPit(pit, players.get(0));
        }
        Kalaha kalaha = gameBoard.getKalahas().get(0);
        assertKalaha(kalaha, players.get(0));
        Assert.assertThat(players.get(0).getKalaha(), equalTo(kalaha));
        pit = kalaha.getPit().getNextPit();
        assertPit(pit, players.get(1));
        for(int i =0; i < 5 ;i++ )
        {
            pit = pit.getNextPit();
            assertPit(pit,players.get(1));
        }
        kalaha = gameBoard.getKalahas().get(1);
        Assert.assertThat(players.get(1).getKalaha(), equalTo(kalaha));
    }

    private void assertKalaha(Kalaha kalaha, Player player){
        Assert.assertThat(kalaha.getPit().getOwner(), equalTo(player));
        Assert.assertThat(kalaha.getPit().getStones().size(), equalTo(0));
    }

    private void assertPit(Pit pit,Player player){
        Assert.assertThat(pit.getOwner(), equalTo(player));
        Assert.assertThat(pit.getStones().size(), equalTo(6));
    }
}
