package game.moves;

import game.GameBoard;
import game.Player;

public interface Move {
   void execute(Player player, GameBoard gameBoard);
}
