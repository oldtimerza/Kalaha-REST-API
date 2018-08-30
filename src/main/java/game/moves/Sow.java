package game.moves;

import game.GameBoard;
import game.Player;

public class Sow  implements Move{

    private int pitNumber;

    public Sow(int pitNumber) {
        this.pitNumber = pitNumber;
    }

    @Override
    public void execute(Player player, GameBoard gameBoard) {
       gameBoard.Sow(player, this.pitNumber);
    }
}
