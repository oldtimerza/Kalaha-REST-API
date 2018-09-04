package kalaha.game;

import kalaha.dtos.GameDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GameFactory {

   @Autowired
   GameBoardFactory gameBoardFactory;

    public Game fromGameDto(GameDto gameDto){
        List<Player> players = new ArrayList<>();
        for(int i=0; i< gameDto.numberOfPlayers; i++){
            players.add(new Player(i));
        }
        GameBoard gameBoard = gameBoardFactory.fromDtos(gameDto.pits, gameDto.kalahas, players);
        GameState state = new GameState();
        state.setNextPlayer(players.get(gameDto.nextPlayerIndex));
        state.setCurrentPlayer(players.get(gameDto.currentPlayerIndex));
        state.setStatus(gameDto.status);
        Game game = new Game(gameBoard,state,players);
        return game;
    }
}
