package game.moves;

import game.Game;
import game.Pit;
import game.Player;

public class TakeAnotherTurn implements Move {
    private Pit pit;

    public TakeAnotherTurn(Pit pit){
        this.pit = pit;
    }

    @Override
    public void execute(Player player, Game game) {
        game.setNextPlayer(player);
    }

    public Pit getPit() {
        return pit;
    }
}
