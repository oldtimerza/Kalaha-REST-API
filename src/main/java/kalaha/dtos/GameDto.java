package kalaha.dtos;

import kalaha.game.Status;

import java.util.List;

public class GameDto {
    public int currentPlayerIndex;
    public int nextPlayerIndex;
    public int numberOfPlayers;
    public List<KalahaDto> kalahas;
    public List<PitDto> pits;
    public Status status;
}
