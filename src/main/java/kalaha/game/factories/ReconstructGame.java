package kalaha.game.factories;

import kalaha.dtos.GameJson;
import kalaha.game.Game;

public interface ReconstructGame {
     Game createGame(GameJson json) throws Exception;
}
