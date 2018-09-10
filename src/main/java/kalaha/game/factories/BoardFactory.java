package kalaha.game.factories;

import kalaha.game.*;

import java.util.List;

public interface BoardFactory {
    GameBoard createBoard(List<Player> players) throws Exception;
}
