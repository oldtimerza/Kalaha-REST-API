package kalaha.game.factories;

import kalaha.game.*;

import java.util.List;

public abstract class BoardFactory {
    public abstract GameBoard createBoard(List<Player> players) throws Exception;
}
