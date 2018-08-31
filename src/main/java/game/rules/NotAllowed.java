package game.rules;

import game.GameBoard;
import game.Player;
import game.moves.Move;

public class NotAllowed extends Affirmation {

    public NotAllowed(Move move, Player player, GameBoard gameBoard) {
        super(move, player, gameBoard);
    }

    @Override
    public void thenExecute() {

    }
}
