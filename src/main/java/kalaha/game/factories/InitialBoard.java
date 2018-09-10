package kalaha.game.factories;

import kalaha.game.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InitialBoard implements BoardFactory {
    @Override
    public GameBoard createBoard(List<Player> players) throws Exception {
        List<Pit> pits = new ArrayList<>();
        List<Kalaha> kalahas = new ArrayList<>();
        if(players.size() < 2){
            throw new Exception("Please include 2 players");
        }
        setupPlayerBoard(pits, kalahas, players.get(0));
        setupPlayerBoard(pits, kalahas, players.get(1));
        players.get(0).getKalaha().getPit().setNextPit(players.get(1).getPits().get(0));
        players.get(1).getKalaha().getPit().setNextPit(players.get(0).getPits().get(0));
        GameBoard board = new GameBoard(pits, kalahas);
        return board;
    }


    private void setupPlayerBoard(List<Pit> pits, List<Kalaha> kalahas, Player player) {
        List<Stone> stones = new ArrayList<>();
        for(int i=0; i < 6; i++){
            stones.add(new Stone());
        }
        for(int i = 0; i < 6; i ++ ) {
            Pit pit = new Pit(new ArrayList<>(stones), player, pits.size() + 1);
            if(i > 0) {
                pits.get(pits.size() - 1).setNextPit(pit);
            }
            player.addPit(pit);
            pits.add(pit);
        }
        Pit kalahaPit = new Pit(new ArrayList<>(), player, pits.size() + 1);
        Kalaha kalaha = new Kalaha(kalahaPit);
        kalahas.add(kalaha);
        pits.get(pits.size() - 1).setNextPit(kalahaPit);
        pits.add(kalahaPit);
        player.setKalaha(kalaha);
    }
}
