package game.rules;

import game.Game;
import game.GameBoard;
import game.Player;
import game.moves.Move;

public class Check<T extends Move>{

    private Game game;

    private Player player;
    private Rules<T> rules;

    public Check(Rules<T> rules){
        this.rules = rules;
    }

    public Check thatPlayer(Player player){
        this.player = player;
        return this;
    }

    public Check given(Game game){
        this.game = game;
        return this;
    }

    public Affirmation isAllowed(T move){
        if(player != null && game != null) {
            return rules.validate(move, game, player);
        }
        return null;
    }
}
