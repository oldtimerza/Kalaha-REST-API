package game;

import game.moves.Move;

import java.util.ArrayList;
import java.util.List;

public class GameBoard {
    private List<Pit> pits;
    private List<Kalaha> kalahas;
    private List<Move> moves = new ArrayList<>();

    public GameBoard(List<Pit> pits, List<Kalaha> kalahas){
        this.pits = pits;
        this.kalahas = kalahas;
    }

    public List<Kalaha> getKalahas() {
        return kalahas;
    }

    public List<Pit> getPits(){return pits;}

    public List<Move> getMoves(){
        return this.moves;
    }

    public Pit getPitAt(int pitNumber){
        return pits.stream().filter(p -> p.getNumber() == pitNumber).findAny().get();
    }
}
