package game;

import game.moves.Move;

import java.util.ArrayList;
import java.util.List;

public class GameBoard {
    private List<Pit> pits;
    private List<Kalaha> kalahas;
    private GameState gameState;
    private List<Move> moves = new ArrayList<>();

    public GameBoard(List<Pit> pits, List<Kalaha> kalahas, GameState gameState){
        this.pits = pits;
        this.kalahas = kalahas;
        this.gameState = gameState;
    }

    public List<Kalaha> getKalahas() {
        return kalahas;
    }

    public List<Pit> getPits() {
        return pits;
    }

    public GameBoard makeMove(Player player, Move move){
       moves.add(move);
       move.execute(player, this);
       return this;
    }

    public void Sow(Player player, int pitNumber){

    }
}
