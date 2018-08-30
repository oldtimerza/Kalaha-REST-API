package game;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;

public class GameBoardFactoryTest {
    @Test
    public void shouldGetTheCorrectGameBoardConfig(){
        List<Player> players = new ArrayList<>();
        players.add(new Player());
        players.add(new Player());
        GameBoardFactory factory = new GameBoardFactory();
        GameState gameState = new GameState();
        GameBoard gameBoard = factory.createGameBoard(players, gameState);

        Assert.assertThat(gameBoard.getKalahas().size(), equalTo(2));
        Assert.assertThat(gameBoard.getPits().size(), equalTo(14));
        Pit pit = gameBoard.getPits().get(0);
        assertPit(pit, players.get(0));
        for(int i =0; i < 5; i ++){
            pit = pit.getNextPit();
            assertPit(pit, players.get(0));
        }
        Kalaha kalaha = (Kalaha)pit.getNextPit();
        assertKalaha(kalaha, players.get(0));
        Assert.assertThat(players.get(0).getKalaha(), equalTo(kalaha));
        pit = kalaha.getNextPit();
        assertPit(pit, players.get(1));
        for(int i =0; i < 5 ;i++ )
        {
            pit = pit.getNextPit();
           assertPit(pit,players.get(1));
        }
        kalaha = (Kalaha) pit.getNextPit();
        Assert.assertThat(players.get(1).getKalaha(), equalTo(kalaha));
    }

    private void assertKalaha(Kalaha kalaha, Player player){
        Assert.assertThat(kalaha.getOwner(), equalTo(player));
        Assert.assertThat(kalaha.getStones().size(), equalTo(0));
    }

    private void assertPit(Pit pit,Player player){
        Assert.assertThat(pit.getOwner(), equalTo(player));
        Assert.assertThat(pit.getStones().size(), equalTo(6));
    }
}
