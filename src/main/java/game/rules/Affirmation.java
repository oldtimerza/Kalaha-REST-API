package game.rules;

import game.Game;
import game.Player;
import game.moves.Move;

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
