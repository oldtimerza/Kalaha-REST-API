package kalaha.game.rules;

import kalaha.game.Game;
import kalaha.game.Player;
import kalaha.game.moves.CaptureOpponentsStones;

public class CaptureStonesRules implements Rules<CaptureOpponentsStones> {
    @Override
    public Affirmation validate(CaptureOpponentsStones move, Game game, Player player) {
        if(player.ownsPit(move.getPit())) {
            return new Allowed(move, player, game);
        }
        return new NotAllowed(move, player, game);
    }
}
