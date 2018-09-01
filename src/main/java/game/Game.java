package game;

import game.moves.Move;
import java.util.ArrayList;
import java.util.List;

public class Game {

    private GameBoard gameBoard;

    private GameState state;

    private List<Player> players;

    private List<Move> moves;

    public Game(GameBoard gameBoard, GameState state, List<Player> players){
        this.gameBoard = gameBoard;
        this.state = state;
        this.players = players;
        moves = new ArrayList<>();
    }

    public Game makeMove(Player player, Move move) throws NotPlayersTurnException {
        if(!player.equals(state.getCurrentPlayer())){
            throw new NotPlayersTurnException();
        }
        moves.add(move);
        move.execute(player, this);
        return this;
    }

    public List<Move> getMoves(){
        return this.moves;
    }

    public List<Player> getPlayers(){return players;}

    public GameBoard getGameBoard(){return gameBoard;}
}
