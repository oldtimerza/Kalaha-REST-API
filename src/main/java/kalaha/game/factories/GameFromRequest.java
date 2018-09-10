package kalaha.game.factories;

import kalaha.dtos.GameJson;
import kalaha.game.Game;
import kalaha.game.GameBoard;
import kalaha.game.GameState;
import kalaha.game.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GameFromRequest implements ReconstructGame {

    private ReconstructBoard reconstructBoard;

    @Override
    public Game createGame(GameJson json) throws Exception {
        List<Player> players = new ArrayList<>();
        for(int i = 0; i< json.numberOfPlayers; i++){
            players.add(new Player(i));
        }
        GameState state = new GameState();
        state.setNextPlayer(players.get(json.nextPlayerIndex));
        state.setCurrentPlayer(players.get(json.currentPlayerIndex));
        state.setStatus(json.status);
        GameBoard board = reconstructBoard.createBoard(players, json.pits, json.kalahas);
        Game game = new Game(board,state,players);
        return game;
    }

    @Autowired
    public void setReconstructBoard(ReconstructBoard reconstructBoard) {
        this.reconstructBoard = reconstructBoard;
    }
}
