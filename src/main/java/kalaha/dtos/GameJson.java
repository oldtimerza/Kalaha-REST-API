package kalaha.dtos;

import kalaha.game.Status;

import java.util.List;

public class GameJson {
    public int currentPlayerIndex;
    public int nextPlayerIndex;
    public int numberOfPlayers;
    public List<KalahaJson> kalahas;
    public List<PitJson> pits;
    public Status status;
}
