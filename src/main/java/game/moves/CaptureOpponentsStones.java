package game.moves;

import game.*;

import java.util.ArrayList;
import java.util.List;

public class CaptureOpponentsStones implements Move {

    private Pit pit;

    public CaptureOpponentsStones(Pit pit){
        this.pit = pit;
    }

    @Override
    public void execute(Player player, Game game) {
        List<Player> players = game.getPlayers();
        Player opposingPlayer = players.stream().filter(p -> !p.equals(player)).findFirst().get();
        int pitIndex = player.getPits().indexOf(pit);
        int numberOfPits = opposingPlayer.getPits().size();
        Pit oppositePit = opposingPlayer.getPits().get(numberOfPits - pitIndex);
        List<Stone> stones = new ArrayList<>();
        Kalaha kalaha = player.getKalaha();
        stones.addAll(pit.removeStones());
        stones.addAll(oppositePit.removeStones());
        kalaha.getPit().addStones(stones);
    }

    public Pit getPit(){
        return pit;
    }
}
