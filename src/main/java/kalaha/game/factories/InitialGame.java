package kalaha.game.factories;

import kalaha.game.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InitialGame implements GameFactory {

    private BoardFactory boardFactory;

    @Override
    public Game createGame() throws Exception {
        List<Player> players = new ArrayList<>();
        players.add(new Player(0));
        players.add(new Player(1));
        GameBoard board = boardFactory.createBoard(players);
        GameState state = new GameState();
        state.setCurrentPlayer(players.get(0));
        state.setNextPlayer(players.get(1));
        state.setStatus(Status.RUNNING);
        Game game = new Game(board,state, players);
        return game;
    }

    @Autowired
    public void setBoardFactory(BoardFactory boardFactory) {
        this.boardFactory = boardFactory;
    }
}
