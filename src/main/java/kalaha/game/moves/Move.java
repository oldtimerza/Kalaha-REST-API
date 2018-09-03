package kalaha.game.moves;

import kalaha.game.Game;
import kalaha.game.Player;

public interface Move {
   void execute(Player player, Game game);
}
