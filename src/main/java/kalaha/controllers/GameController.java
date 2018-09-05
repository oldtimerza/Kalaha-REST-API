package kalaha.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import kalaha.dtos.GameDto;
import kalaha.game.*;
import kalaha.game.builders.*;
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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "http://localhost", maxAge=3600)
@Controller
@RequestMapping("/game")
public class GameController {

    ModelMapper modelMapper;

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
            BoardBuilder initialBoard = new InitialBoard();
            GameBuilder gameBuilder = new InitialGame(initialBoard);
            gameBuilder.buildPlayers();
            gameBuilder.buildBoard();
            gameBuilder.buildState();
            Game game = gameBuilder.construct();
            GameDto response = modelMapper.map(game, GameDto.class);
            return ResponseEntity.ok(response);
        }catch(Exception e){
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @RequestMapping(value = "/sow", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE} , produces={MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity sow(@RequestBody Map<String, Object> json){
       try{
           setupModelMapper();
           ObjectMapper mapper = new ObjectMapper();
           GameDto gameDto = mapper.readValue(json.get("game").toString(), GameDto.class);
           BoardBuilder boardBuilder = new DtoBoard(gameDto.pits, gameDto.kalahas);
           GameBuilder gameBuilder = new DtoGame(gameDto, boardBuilder);
           gameBuilder.buildPlayers();
           gameBuilder.buildBoard();
           gameBuilder.buildState();
           Game game = gameBuilder.construct();
           int playerIndex = Integer.parseInt(json.get("playerIndex").toString());
           Player player = game.getPlayers().get(playerIndex);
           Check<DropStone> dropStoneCheck = new Check<>(new DropStoneRules());
           Check<CaptureOpponentsStones> captureOpponentsStonesCheck = new Check<>(new CaptureStonesRules());
           Check<TakeAnotherTurn> takeAnotherTurnCheck = new Check<>(new TakeAnotherTurnRules());
           int pitNumber = Integer.parseInt( json.get("pitNumber").toString());
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
