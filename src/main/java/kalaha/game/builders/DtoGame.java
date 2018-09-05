package kalaha.game.builders;

import kalaha.dtos.GameDto;
import kalaha.game.Game;
import kalaha.game.GameState;
import kalaha.game.Player;

import java.util.ArrayList;

public class DtoGame extends GameBuilder {

    private GameDto gameDto;

    public DtoGame(GameDto gameDto, BoardBuilder boardBuilder){
       super(boardBuilder);
        this.gameDto = gameDto;
    }

    @Override
    public Game construct() {
        Game game = new Game(board,state,players);
        return game;
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
        state.setNextPlayer(players.get(gameDto.nextPlayerIndex));
        state.setCurrentPlayer(players.get(gameDto.currentPlayerIndex));
        state.setStatus(gameDto.status);
    }

    @Override
    public void buildPlayers() {
        players = new ArrayList<>();
        for(int i=0; i< gameDto.numberOfPlayers; i++){
            players.add(new Player(i));
        }
    }
}
