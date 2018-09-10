package kalaha.game.factories;

import kalaha.dtos.KalahaJson;
import kalaha.dtos.PitJson;
import kalaha.game.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BoardFromRequest implements ReconstructBoard {
    @Override
    public GameBoard createBoard(List<Player> players, List<PitJson> pitJsons, List<KalahaJson> kalahaJsons){
        List<Pit> pits = createPits(players, pitJsons);
        List<Kalaha> kalahas = createKalahas(pits, kalahaJsons);
        pits.get(0).setNextPit(pits.get(1));
        players.get(0).getKalaha().getPit().setNextPit(players.get(1).getPits().get(0));
        players.get(1).getKalaha().getPit().setNextPit(players.get(0).getPits().get(0));
        GameBoard board = new GameBoard(pits, kalahas);
        return board;
    }

    private List<Kalaha> createKalahas(List<Pit> pits, List<KalahaJson> kalahaJsons) {
        List<Kalaha> kalahas = new ArrayList<>();
        for(KalahaJson kalahaJson : kalahaJsons){
            Pit pit = pits.stream().filter(p -> p.getNumber() == kalahaJson.pitNumber).findFirst().get();
            Kalaha kalaha = new Kalaha(pit);
            kalahas.add(kalaha);
            pit.getOwner().setKalaha(kalaha);
        }
        return kalahas;
    }

    private List<Pit> createPits(List<Player> players, List<PitJson> pitJsons) {
        List<Pit> pits = new ArrayList<>();
        for(PitJson pitJson : pitJsons) {
            List<Stone> stones = new ArrayList<>();
            for(int i = 0; i < pitJson.numberOfStones; i++){
                stones.add(new Stone());
            }
            Player owner = players.get(pitJson.owningPlayerIndex);
            Pit pit = new Pit(stones, owner, pitJson.pitNumber);
            if(pits.size() > 1){
                pits.get(pits.size() - 1).setNextPit(pit);
            }
            pits.add(pit);
            owner.addPit(pit);
        }
        return pits;
    }
}
