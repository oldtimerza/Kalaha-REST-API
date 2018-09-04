package kalaha.game.rules;

import kalaha.game.*;
import kalaha.game.moves.DropStone;

public class DropStoneRules implements Rules<DropStone> {
    @Override
    public Affirmation validate(DropStone move, Game game, Player player) {
        GameBoard gameBoard = game.getGameBoard();
        boolean pitBelongsToAKalaha = gameBoard.getKalahas().stream().filter(k -> k.getPit().equals(move.getPit())).findFirst().isPresent();
        Kalaha kalaha = player.getKalaha();
        Pit pit = kalaha.getPit();
        boolean kalahaBelongsToMe = pit.equals(move.getPit());
        if(pitBelongsToAKalaha && !kalahaBelongsToMe){
            return new NotAllowed(move, player, game);
        }
        return new Allowed(move, player, game);
    }
}
