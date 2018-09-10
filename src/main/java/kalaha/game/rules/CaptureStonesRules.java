package kalaha.game.rules;

import kalaha.game.Game;
import kalaha.game.Player;
import kalaha.game.moves.CaptureOpponentsStones;
import org.springframework.stereotype.Component;

@Component
public class CaptureStonesRules implements Rules<CaptureOpponentsStones> {
    @Override
    public Affirmation validate(CaptureOpponentsStones move, Game game, Player player) {
        if(player.ownsPit(move.getPit()) && move.getPit().getStones().size() == 0) {
            return new Allowed(move, player, game);
        }
        return new NotAllowed(move, player, game);
    }
}
