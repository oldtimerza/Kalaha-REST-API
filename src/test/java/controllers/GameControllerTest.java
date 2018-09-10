package controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import kalaha.controllers.GameController;
import kalaha.game.factories.*;
import kalaha.game.rules.CaptureStonesRules;
import kalaha.game.rules.DropStoneRules;
import kalaha.game.rules.TakeAnotherTurnRules;
import kalaha.services.GameService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.Map;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;

public class GameControllerTest {

    GameController controller;
    GameService gameService;

    @Before
    public void init(){
        gameService = new GameService();
        gameService.setCaptureStonesRules(new CaptureStonesRules());
        gameService.setDropStoneRules(new DropStoneRules());
        gameService.setGameFromRequest(new GameFromRequest());
        gameService.setTakeAnotherTurnRules(new TakeAnotherTurnRules());
        InitialGame initialGame = new InitialGame();
        InitialBoard initialBoard = new InitialBoard();
        initialGame.setBoardFactory(initialBoard);
        gameService.setInitialGame(initialGame);
        BoardFromRequest boardFromRequest = new BoardFromRequest();
        GameFromRequest gameFromRequest = new GameFromRequest();
        gameFromRequest.setReconstructBoard(boardFromRequest);
        gameService.setGameFromRequest(gameFromRequest);
        controller = new GameController();
        controller.gameService = gameService;
    }

    @Test
    public void shouldReturnInitialGameStateWhenStartIsCalled() throws IOException {
        File responseFile = new File("src/test/data/startResponse.json");
        String expectedResponse = readFile(responseFile);
        given()
                .standaloneSetup(controller)
                .when()
                .get("/game/start")
                .then()
                .statusCode(200)
                .assertThat()
                .body(Matchers.equalTo(expectedResponse));
    }

    @Test
    public void shouldReturnACorrectBoardStateAfterSowMoveMade() throws IOException {
        File mockRequest = new File("src/test/data/mockRequest.json");
        File responseFile = new File("src/test/data/sowResponse.json");
        Map<String, Object> request = readJsonFromFile(mockRequest);
        String expectedResponse = readFile(responseFile);
        given()
                .standaloneSetup(controller)
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post("/game/sow")
                .then()
                .statusCode(200)
                .assertThat()
                .body(Matchers.equalTo(expectedResponse));
    }

    private String readFile(File file) throws IOException {
        byte[] json = Files.readAllBytes(file.toPath());
        return new String(json, Charset.defaultCharset());
    }

    private Map<String,Object> readJsonFromFile(File inFile) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        byte[] json = Files.readAllBytes(inFile.toPath());
        return mapper.readValue(json, new TypeReference<Map<String, Object>>(){});
    }
}
