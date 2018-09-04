package kalaha.game;

import kalaha.game.moves.Move;
import java.util.ArrayList;
import java.util.List;

public class Game {

    private GameBoard gameBoard;

    private GameState state;

    private List<Player> players;

    public Game(GameBoard gameBoard, GameState state, List<Player> players){
        this.gameBoard = gameBoard;
        this.state = state;
        this.players = players;
    }

    public Game makeMove(Player player, Move move) throws NotPlayersTurnException {
        if(state.getStatus().equals(Status.ENDED)){
            return this;
        }
        if(!player.equals(state.getCurrentPlayer())){
            throw new NotPlayersTurnException();
        }
        move.execute(player, this);
        checkForGameEnd();
        nextPlayersTurn();
        return this;
    }

    private void checkForGameEnd() {
        boolean ended = true;
        for(Player player : players){
            for(Pit pit: player.getPits()){
                if(pit.getStones().size() > 0 ){
                    ended = false;
                }
            }
        }
        if(ended){
            this.state.setStatus(Status.ENDED);
        }
    }

    private void nextPlayersTurn(){
        this.state.setCurrentPlayer(this.state.getNextPlayer());
        if(this.state.getNextPlayer().equals(players.get(players.size() - 1))){
            setNextPlayer(players.get(0));
        }else{
            int currentPlayerIndex = players.indexOf(this.state.getCurrentPlayer());
            setNextPlayer(players.get(currentPlayerIndex + 1));
        }
    }

    public void setNextPlayer(Player player){
        this.state.setNextPlayer(player);
    }

    public Player getNextPlayer(){
        return state.getNextPlayer();
    }

    public Player getCurrentPlayer(){
        return state.getCurrentPlayer();
    }

    public Status getStatus(){
        return state.getStatus();
    }

    public List<Player> getPlayers(){return players;}

    public GameBoard getGameBoard(){return gameBoard;}
}
