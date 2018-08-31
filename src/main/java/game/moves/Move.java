package game.moves;

import game.Game;
import game.Player;

public interface Move {
   void execute(Player player, Game game);
}
