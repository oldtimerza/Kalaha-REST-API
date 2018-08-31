package game.rules;

import game.Game;
import game.Player;
import game.moves.Move;

public class NotAllowed extends Affirmation {

    public NotAllowed(Move move, Player player, Game game) {
        super(move, player, game);
    }

    @Override
    public boolean ok() {
        return false;
    }

    @Override
    public void thenExecute() {

    }
}
