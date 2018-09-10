package kalaha.game.factories;

import kalaha.game.Game;
import org.springframework.stereotype.Component;

@Component
public abstract class GameFactory {
    public abstract Game createGame() throws Exception;
}
