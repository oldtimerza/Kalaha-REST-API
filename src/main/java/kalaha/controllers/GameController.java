package kalaha.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import kalaha.dtos.GameJson;
import kalaha.game.*;
import kalaha.mappers.Mapper;
import kalaha.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(value="http://localhost", maxAge = 3600)
@Controller
@RequestMapping("/game")
public class GameController {

    @Autowired
    public GameService gameService;

    @RequestMapping(value = "/start", method = RequestMethod.GET)
    public ResponseEntity startGame() {
        try {
            Game game = gameService.start();
            GameJson jsonResponse = Mapper.getMapper().map(game, GameJson.class);
            return ResponseEntity.ok(jsonResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @RequestMapping(value = "/sow", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity sow(@RequestBody Map<String, Object> json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String gameKey = "game";
            String playerIndexKey = "playerIndex";
            String pitNumberKey = "pitNumber";
            GameJson gameJson = mapper.readValue(json.get(gameKey).toString(), GameJson.class);
            int playerIndex = Integer.parseInt(json.get(playerIndexKey).toString());
            int pitNumber = Integer.parseInt(json.get(pitNumberKey).toString());
            Game game = gameService.sow(gameJson, playerIndex, pitNumber);
            GameJson jsonResponse = Mapper.getMapper().map(game, GameJson.class);
            return ResponseEntity.ok(jsonResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        } catch (NotPlayersTurnException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
