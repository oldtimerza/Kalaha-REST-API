package game.rules;

import game.GameBoard;
import game.Player;
import game.moves.Move;

public class Allowed extends Affirmation {

    public Allowed(Move move, Player player, GameBoard gameBoard) {
        super(move, player, gameBoard);
    }

    @Override
    public void thenExecute() {
       this.move.execute(this.player, this.gameBoard);
    }
}
