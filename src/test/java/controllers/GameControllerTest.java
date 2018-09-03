package controllers;

import kalaha.controllers.GameController;
import kalaha.game.GameBoardFactory;
import kalaha.game.GameState;
import kalaha.mappers.GameMapper;
import kalaha.mappers.KalahaMapper;
import kalaha.mappers.PitMapper;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;

public class GameControllerTest {

    GameController controller;

    @Before
    public void init(){
        controller = new GameController();
        controller.gameBoardFactory = new GameBoardFactory();
        controller.gameMapper = new GameMapper();
        controller.kalahaMapper = new KalahaMapper();
        controller.pitMapper = new PitMapper();
        controller.gameState = new GameState();
    }

    @Test
    public void shouldReturnInitialGameStateWhenStartIsCalled(){
        given()
                .standaloneSetup(controller)
                .when()
                .get("/game/start")
                .then()
                .statusCode(200)
                .body("activePlayerIndex", Matchers.equalTo(0))
                .body("status", Matchers.equalTo("RUNNING"))
                .body("kalahas[0].pitNumber", Matchers.equalTo(7))
                .body("kalahas[1].pitNumber", Matchers.equalTo(14))
                .body("pits[0].pitNumber", Matchers.equalTo(1))
                .body("pits[0].owningPlayerIndex", Matchers.equalTo(0))
                .body("pits[0].numberOfStones", Matchers.equalTo(6))
                .body("pits[6].pitNumber", Matchers.equalTo(7))
                .body("pits[6].owningPlayerIndex", Matchers.equalTo(0))
                .body("pits[6].numberOfStones", Matchers.equalTo(0))
                .body("pits[7].pitNumber", Matchers.equalTo(8))
                .body("pits[7].owningPlayerIndex", Matchers.equalTo(1))
                .body("pits[7].numberOfStones", Matchers.equalTo(6))
                .body("pits[13].pitNumber", Matchers.equalTo(14))
                .body("pits[13].owningPlayerIndex", Matchers.equalTo(1))
                .body("pits[13].numberOfStones", Matchers.equalTo(0))
        ;
    }
}
