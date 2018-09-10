package kalaha.game.factories;

import kalaha.game.Game;
import org.springframework.stereotype.Component;

@Component
public interface GameFactory {
    Game createGame() throws Exception;
}
