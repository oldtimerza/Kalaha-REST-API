package kalaha.game;

import kalaha.game.builders.BoardBuilder;
import kalaha.game.builders.BoardDirector;
import kalaha.game.builders.InitialBoard;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import java.util.List;

import static org.mockito.Mockito.verify;

public class BoardDirectorTest {

    BoardDirector director;
    BoardBuilder boardBuilder;
    @Before
    public void init(){
       boardBuilder = Mockito.mock(InitialBoard.class);
       director = new BoardDirector(boardBuilder);
    }

    @Test
    public void shouldCreateBoardUsingCorrectBuilderMethods() throws Exception {
       verify(boardBuilder).buildStones();
        verify(boardBuilder).buildPits((List<Player>)Matchers.any(List.class));
        verify(boardBuilder).buildKalahas((List<Player>)Matchers.any(List.class));
        verify(boardBuilder).construct((List<Player>)Matchers.any(List.class));
    }
}
