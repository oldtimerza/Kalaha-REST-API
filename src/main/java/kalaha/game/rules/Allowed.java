package kalaha.game.rules;

import kalaha.game.Game;
import kalaha.game.Player;
import kalaha.game.moves.Move;

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
