package game.rules;

import game.GameBoard;
import game.Player;
import game.moves.Move;

public interface Rules <T extends Move>{
    Affirmation validate(T move, GameBoard gameBoard, Player player);
}
