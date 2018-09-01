package game.moves;

import game.*;

import java.util.ArrayList;
import java.util.List;

public class CaptureOpponentsStones implements Move {

    private int pitIndex;

    public CaptureOpponentsStones(int pitIndex){
        this.pitIndex = pitIndex;
    }

    @Override
    public void execute(Player player, Game game) {
        List<Player> players = game.getPlayers();
        Player opposingPlayer = players.stream().filter(p -> !p.equals(player)).findFirst().get();
        Pit pit = player.getPits().get(pitIndex);
        int numberOfPits = opposingPlayer.getPits().size();
        Pit oppositePit = opposingPlayer.getPits().get(numberOfPits - pitIndex);
        List<Stone> stones = new ArrayList<>();
        Kalaha kalaha = player.getKalaha();
        stones.addAll(pit.removeStones());
        stones.addAll(oppositePit.removeStones());
        kalaha.getPit().addStones(stones);
    }
}
