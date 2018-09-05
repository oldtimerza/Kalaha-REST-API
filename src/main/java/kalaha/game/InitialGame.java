package kalaha.game;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class InitialGame extends GameBuilder{

    public InitialGame(BoardBuilder boardBuilder) {
        super(boardBuilder);
    }

    @Override
    public void buildBoard() throws Exception {
        boardBuilder.buildStones();
        boardBuilder.buildPits(players);
        boardBuilder.buildKalahas(players);
        board = boardBuilder.construct(players);
    }

    @Override
    public void buildState() {
        state = new GameState();
        state.setCurrentPlayer(players.get(0));
        state.setNextPlayer(players.get(1));
        state.setStatus(Status.RUNNING);
    }

    @Override
    public void buildPlayers() {
        players = new ArrayList<>();
        players.add(new Player(0));
        players.add(new Player(1));
    }

    @Override
    public Game construct() {
        Game game = new Game(board,state, players);
        return game;
    }
}
