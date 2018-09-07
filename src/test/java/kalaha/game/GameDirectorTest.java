package kalaha.game;

import kalaha.game.builders.GameBuilder;
import kalaha.game.builders.GameDirector;
import kalaha.game.builders.InitialGame;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.verify;

public class GameDirectorTest {
    GameDirector director;
    GameBuilder gameBuilder;
    @Before
    public void init(){
        gameBuilder = Mockito.mock(InitialGame.class);
        director = new GameDirector(gameBuilder);
    }

    @Test
    public void shouldCreateAGameUsingCorrectBuilderMethods() throws Exception {
        Game game = director.getGame();
        verify(gameBuilder).buildPlayers();
        verify(gameBuilder).buildState();
        verify(gameBuilder).buildBoard();
        verify(gameBuilder).construct();
    }

}
