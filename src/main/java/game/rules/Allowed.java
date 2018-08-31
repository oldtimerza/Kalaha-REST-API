package game.rules;

import game.Game;
import game.Player;
import game.moves.Move;

public class Allowed extends Affirmation {

    public Allowed(Move move, Player player, Game game) {
        super(move, player, game);
    }

    @Override
    public boolean ok() {
        return true;
    }

    @Override
    public void thenExecute() {
       this.move.execute(this.player, this.game);
    }
}
