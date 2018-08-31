package game.rules;

import game.GameBoard;
import game.Player;
import game.moves.Move;

public abstract class Affirmation {
    protected Move move;
    protected Player player;
    protected GameBoard gameBoard;

    public Affirmation(Move move, Player player, GameBoard gameBoard) {
        this.move = move;
        this.player = player;
        this.gameBoard = gameBoard;
    }

    public abstract void thenExecute();
}
