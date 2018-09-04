package kalaha.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import kalaha.dtos.GameDto;
import kalaha.game.*;
import kalaha.game.moves.CaptureOpponentsStones;
import kalaha.game.moves.DropStone;
import kalaha.game.moves.Sow;
import kalaha.game.moves.TakeAnotherTurn;
import kalaha.game.rules.CaptureStonesRules;
import kalaha.game.rules.Check;
import kalaha.game.rules.DropStoneRules;
import kalaha.game.rules.TakeAnotherTurnRules;
import kalaha.mappers.GameMapper;
import kalaha.mappers.KalahaMapper;
import kalaha.mappers.PitMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/game")
public class GameController {

    ModelMapper modelMapper;

    @Autowired
    public GameFactory gameFactory;

    @Autowired
    public GameBoardFactory gameBoardFactory;

    @Autowired
    public GameState gameState;

    @Autowired
    public GameMapper gameMapper;

    @Autowired
    public PitMapper pitMapper;

    @Autowired
    public KalahaMapper kalahaMapper;

    @RequestMapping(value = "/start", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity startGame(){
        try {
            setupModelMapper();
            List<Player> players = new ArrayList<>();
            players.add(new Player(0));
            players.add(new Player(1));
            GameBoard board = gameBoardFactory.createInitialBoard(players);
            Game game = new Game(board, gameState, players);
            GameDto response = modelMapper.map(game, GameDto.class);
            return ResponseEntity.ok(response);
        }catch(Exception e){
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @RequestMapping(value = "/sow", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity sow(int playerIndex, int pitNumber,@RequestParam("game") String gameJson){
       try{
           setupModelMapper();
           ObjectMapper mapper = new ObjectMapper();
           GameDto gameDto = mapper.readValue(gameJson, GameDto.class);
           Game game = gameFactory.fromGameDto(gameDto);
           Player player = game.getPlayers().get(playerIndex);
           Check<DropStone> dropStoneCheck = new Check<>(new DropStoneRules());
           Check<CaptureOpponentsStones> captureOpponentsStonesCheck = new Check<>(new CaptureStonesRules());
           Check<TakeAnotherTurn> takeAnotherTurnCheck = new Check<>(new TakeAnotherTurnRules());
           Sow sow = new Sow(dropStoneCheck, captureOpponentsStonesCheck, takeAnotherTurnCheck, pitNumber);
           game = game.makeMove(player, sow);
           GameDto response = modelMapper.map(game, GameDto.class);
           return ResponseEntity.ok(response);
       }catch(Exception e){
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
       } catch (NotPlayersTurnException e) {
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
       }
    }

    private void setupModelMapper(){
        modelMapper = new ModelMapper();
        modelMapper.addMappings(gameMapper.map());
        modelMapper.addMappings(pitMapper.map());
        modelMapper.addMappings(kalahaMapper.map());
    }
}
