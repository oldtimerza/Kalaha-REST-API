package kalaha.game.rules;

import kalaha.game.Game;
import kalaha.game.Player;
import kalaha.game.moves.Move;

public abstract class Affirmation {
    protected Move move;
    protected Player player;
    protected Game game;

    public Affirmation(Move move, Player player, Game game) {
        this.move = move;
        this.player = player;
        this.game = game;
    }

    public abstract boolean ok();

    public abstract void thenExecute();
}
