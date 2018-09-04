package kalaha.game;

import java.util.List;

public class GameBoard {
    private List<Pit> pits;
    private List<Kalaha> kalahas;

    public GameBoard(List<Pit> pits, List<Kalaha> kalahas){
        this.pits = pits;
        this.kalahas = kalahas;
    }

    public List<Kalaha> getKalahas() {
        return kalahas;
    }

    public List<Pit> getPits(){return pits;}

    public Pit getPitAt(int pitNumber){
        return pits.stream().filter(p -> p.getNumber() == pitNumber).findAny().get();
    }
}
