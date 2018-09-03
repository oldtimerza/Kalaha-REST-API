package kalaha.controllers;

import kalaha.dtos.GameDto;
import kalaha.game.*;
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
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/game")
public class GameController {

    ModelMapper modelMapper;

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
            modelMapper = new ModelMapper();
            modelMapper.addMappings(gameMapper.map());
            modelMapper.addMappings(pitMapper.map());
            modelMapper.addMappings(kalahaMapper.map());
            List<Player> players = new ArrayList<>();
            players.add(new Player(0));
            players.add(new Player(1));
            GameBoard board = gameBoardFactory.createGameBoard(players);
            Game game = new Game(board, gameState, players);
            GameDto response = modelMapper.map(game, GameDto.class);
            return ResponseEntity.ok(response);
        }catch(Exception e){
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
