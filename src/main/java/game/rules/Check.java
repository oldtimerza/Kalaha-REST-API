package game.rules;

import game.GameBoard;
import game.Player;
import game.moves.Move;

public class Check<T extends Move>{

    private GameBoard gameBoard;

    private Player player;
    private Rules<T> rules;

    public Check(Rules<T> rules){
        this.rules = rules;
    }

    public Check thatPlayer(Player player){
        this.player = player;
        return this;
    }

    public Check given(GameBoard gameBoard){
        this.gameBoard = gameBoard;
        return this;
    }

    public Affirmation isAllowed(T move){
        if(player != null && gameBoard != null) {
            return rules.validate(move, gameBoard, player);
        }
        return null;
    }
}
