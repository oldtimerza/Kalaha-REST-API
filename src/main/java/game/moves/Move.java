package game.moves;

import game.GameBoard;
import game.Player;

public interface Move {
   public void execute(Player player, GameBoard gameBoard);
}
