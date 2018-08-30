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

    public List<Move> getMoves(){
        return this.moves;
    }

    public GameBoard makeMove(Player player, Move move) throws NotPlayersTurnException {
        if(!player.equals(gameState.currentPlayer)){
           throw new NotPlayersTurnException();
        }
       moves.add(move);
       move.execute(player, this);
       return this;
    }

    public void Sow(Player player, int pitNumber){
        Pit pit = this.pits.get(pitNumber - 1);
        List<Stone> stones = pit.removeStones();
        for(Stone stone : stones){
           pit = pit.getNextPit();
           List<Stone> stonesToAdd = new ArrayList<>();
           stonesToAdd.add(stone);
           pit.addStones(stonesToAdd);
        }
    }
}
