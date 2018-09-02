package game.rules;

import game.Game;
import game.Pit;
import game.Player;
import game.moves.CaptureOpponentsStones;

public class CaptureStonesRules implements Rules<CaptureOpponentsStones> {

    private Pit pit;

    public CaptureStonesRules(Pit pit){
        this.pit = pit;
    }

    @Override
    public Affirmation validate(CaptureOpponentsStones move, Game game, Player player) {
        if(player.ownsPit(pit)) {
            return new Allowed(move, player, game);
        }
        return new NotAllowed(move, player, game);
    }
}
