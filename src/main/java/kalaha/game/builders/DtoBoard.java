package kalaha.game.builders;

import kalaha.dtos.KalahaDto;
import kalaha.dtos.PitDto;
import kalaha.game.*;

import java.util.ArrayList;
import java.util.List;

public class DtoBoard extends BoardBuilder{

    private List<PitDto> pitDtos;
    private List<KalahaDto> kalahaDtos;

    public DtoBoard(List<PitDto> pitDtos, List<KalahaDto> kalahaDtos){
        this.pitDtos = pitDtos;
        this.kalahaDtos = kalahaDtos;
    }

    @Override
    public void buildPits(List<Player> players) {
        pits = new ArrayList<>();
        for(PitDto pitDto : pitDtos) {
            List<Stone> stones = new ArrayList<>();
            for(int i = 0; i < pitDto.numberOfStones; i++){
                stones.add(new Stone());
            }
            Player owner = players.get(pitDto.owningPlayerIndex);
            Pit pit = new Pit(stones, owner, pitDto.pitNumber);
            if(pits.size() > 1){
                pits.get(pits.size() - 1).setNextPit(pit);
            }
            pits.add(pit);
            owner.addPit(pit);
        }
    }

    @Override
    public void buildKalahas(List<Player> players) {
        kalahas = new ArrayList<>();
        for(KalahaDto kalahaDto: kalahaDtos){
            Pit pit = pits.stream().filter(p -> p.getNumber() == kalahaDto.pitNumber).findFirst().get();
            Kalaha kalaha = new Kalaha(pit);
            kalahas.add(kalaha);
            pit.getOwner().setKalaha(kalaha);
        }
    }

    @Override
    public void buildStones() {
    }

    @Override
    public GameBoard construct(List<Player> players){
        pits.get(0).setNextPit(pits.get(1));
        players.get(0).getKalaha().getPit().setNextPit(players.get(1).getPits().get(0));
        players.get(1).getKalaha().getPit().setNextPit(players.get(0).getPits().get(0));
        GameBoard board = new GameBoard(pits, kalahas);
        return board;
    }
}
