package kalaha.game.rules;

import kalaha.game.Game;
import kalaha.game.Pit;
import kalaha.game.Player;
import kalaha.game.moves.TakeAnotherTurn;

public class TakeAnotherTurnRules implements Rules<TakeAnotherTurn> {
    @Override
    public Affirmation validate(TakeAnotherTurn move, Game game, Player player) {
        Pit pit = move.getPit();
        boolean belongsToAKalaha = game.getGameBoard().getKalahas().stream().anyMatch(k -> k.getPit().equals(pit));
        if(player.ownsPit(pit) && belongsToAKalaha){
            return new Allowed(move, player, game);
        }
        return new NotAllowed(move, player, game);
    }
}
