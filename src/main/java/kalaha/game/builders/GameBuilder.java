package kalaha.game.builders;

import kalaha.game.Game;
import kalaha.game.GameBoard;
import kalaha.game.GameState;
import kalaha.game.Player;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public abstract class GameBuilder {

    protected BoardBuilder boardBuilder;
    protected GameBoard board;
    protected GameState state;
    protected List<Player> players;

    public GameBuilder(BoardBuilder boardBuilder){
        this.boardBuilder = boardBuilder;
    }

    public abstract void buildBoard() throws Exception;
    public abstract void buildState();
    public abstract void buildPlayers();
    public abstract Game construct();

}