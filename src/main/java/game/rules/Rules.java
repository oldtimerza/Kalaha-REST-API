package game.rules;

import game.Game;
import game.Player;
import game.moves.Move;

public interface Rules <T extends Move>{
    Affirmation validate(T move, Game game, Player player);
}
