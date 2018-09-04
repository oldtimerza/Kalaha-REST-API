package kalaha.game;

import kalaha.dtos.KalahaDto;
import kalaha.dtos.PitDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GameBoardFactory {
    public GameBoard createInitialBoard(List<Player> players) throws Exception {
        if(players.size() < 2){
            throw new Exception("Please include 2 players");
        }
        List<Pit> pits = new ArrayList<>();
        List<Kalaha> kalahas = new ArrayList<>();
        List<Stone> stones = new ArrayList<>();
        for(int i =0 ; i < 6; i++){
            stones.add(new Stone());
        }
        setupPlayerBoard(pits, kalahas, stones, players.get(0));
        setupPlayerBoard(pits, kalahas, stones, players.get(1));
        players.get(0).getKalaha().getPit().setNextPit(players.get(1).getPits().get(0));
        players.get(1).getKalaha().getPit().setNextPit(players.get(0).getPits().get(0));
        GameBoard gameBoard = new GameBoard(pits, kalahas);
        return gameBoard;
    }

    public GameBoard fromDtos(List<PitDto> pitDtos, List<KalahaDto> kalahaDtos, List<Player> players){
        List<Pit> pits = new ArrayList<>();
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
        List<Kalaha> kalahas = new ArrayList<>();
        for(KalahaDto kalahaDto: kalahaDtos){
            Pit pit = pits.stream().filter(p -> p.getNumber() == kalahaDto.pitNumber).findFirst().get();
            Kalaha kalaha = new Kalaha(pit);
            kalahas.add(kalaha);
            pit.getOwner().setKalaha(kalaha);
        }
        pits.get(0).setNextPit(pits.get(1));
        players.get(0).getKalaha().getPit().setNextPit(players.get(1).getPits().get(0));
        players.get(1).getKalaha().getPit().setNextPit(players.get(0).getPits().get(0));
        GameBoard board = new GameBoard(pits, kalahas);
        return board;
    }

    private void setupPlayerBoard(List<Pit> pits, List<Kalaha> kalahas, List<Stone> stones, Player player) {
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
