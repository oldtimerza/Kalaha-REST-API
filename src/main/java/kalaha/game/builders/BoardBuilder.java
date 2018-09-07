package kalaha.game.builders;

import kalaha.game.*;

import java.util.List;

public abstract class BoardBuilder {
    protected List<Pit> pits;
    protected List<Stone> stones;
    protected List<Kalaha> kalahas;

    public abstract void buildPits(List<Player> players);
    public abstract void buildKalahas(List<Player> players);
    public abstract void buildStones();
    public abstract GameBoard construct(List<Player> players) throws Exception;
}
