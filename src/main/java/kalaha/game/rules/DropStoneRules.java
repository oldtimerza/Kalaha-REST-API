package kalaha.game.rules;

import kalaha.game.Game;
import kalaha.game.GameBoard;
import kalaha.game.Player;
import kalaha.game.moves.DropStone;

public class DropStoneRules implements Rules<DropStone> {
    @Override
    public Affirmation validate(DropStone move, Game game, Player player) {
        GameBoard gameBoard = game.getGameBoard();
        boolean kalahaOwnsPit = gameBoard.getKalahas().stream().filter(k -> k.getPit().equals(move.getPit()) && k.getPit().getOwner().equals(player)).findFirst().isPresent();
        if(!kalahaOwnsPit){
            return new NotAllowed(move, player, game);
        }
        return new Allowed(move, player, game);
    }
}
