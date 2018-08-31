package game.rules;

import game.GameBoard;
import game.Player;
import game.moves.DropStone;

public class DropStoneRules implements Rules<DropStone> {
    @Override
    public Affirmation validate(DropStone move, GameBoard gameBoard, Player player) {
        boolean kalahaOwnsPit = gameBoard.getKalahas().stream().filter(k -> k.getPit().equals(move.getPit()) && k.getPit().getOwner().equals(player)).findFirst().isPresent();
        if(!kalahaOwnsPit){
            return new NotAllowed(move, player, gameBoard);
        }
        return new Allowed(move, player, gameBoard);
    }
}
