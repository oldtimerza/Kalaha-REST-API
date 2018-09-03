package kalaha.game.rules;

import kalaha.game.Game;
import kalaha.game.Player;
import kalaha.game.moves.Move;

public interface Rules <T extends Move>{
    Affirmation validate(T move, Game game, Player player);
}
