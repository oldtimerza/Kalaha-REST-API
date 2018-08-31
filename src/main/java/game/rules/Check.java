package game.rules;

import game.GameBoard;
import game.Kalaha;
import game.Player;
import game.moves.DropStone;

public class Check {

    private Check check;
    private GameBoard gameBoard;
    private Player player;

    private Check(){}

    public static Check thatPlayer(Player player){
        Check check = new Check();
        check.player = player;
        return check;
    }

    public Check given(GameBoard gameBoard){
        this.gameBoard = gameBoard;
        return this;
    }

    public Affirmation isAllowed(DropStone dropStone){
       boolean kalahaOwnsPit = gameBoard.getKalahas().stream().filter(k -> k.getPit().equals(dropStone.getPit()) && k.getPit().getOwner().equals(player)).findFirst().isPresent();
        if(!kalahaOwnsPit){
            return new NotAllowed(dropStone, player, gameBoard);
        }
        return new Allowed(dropStone, player, gameBoard);
    }
}
