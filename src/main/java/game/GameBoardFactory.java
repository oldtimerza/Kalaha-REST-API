package game;

import java.util.ArrayList;
import java.util.List;

public class GameBoardFactory {
    public GameBoard createGameBoard(List<Player> players, GameState gameState){
        List<Pit> pits = new ArrayList<>();
        List<Kalaha> kalahas = new ArrayList<>();
        List<Stone> stones = new ArrayList<>();
        for(int i =0 ; i < 6; i++){
            stones.add(new Stone());
        }
        setupPlayerBoard(pits, kalahas, stones, players.get(0));
        setupPlayerBoard(pits, kalahas, stones, players.get(1));
        players.get(0).getKalaha().setNextPit(players.get(1).getPits().get(0));
        players.get(1).getKalaha().setNextPit(players.get(0).getPits().get(0));
        GameBoard gameBoard = new GameBoard(pits, kalahas, gameState);
        return gameBoard;
    }

    private void setupPlayerBoard(List<Pit> pits, List<Kalaha> kalahas, List<Stone> stones, Player player) {
        for(int i = 0; i < 6; i ++ ) {
            Pit pit = new Pit(new ArrayList<>(stones),player);
            if(i > 0) {
                pits.get(pits.size() - 1).setNextPit(pit);
            }
            player.addPit(pit);
            pits.add(pit);
        }
        Kalaha kalaha = new Kalaha(new ArrayList<>(), player);
        kalahas.add(kalaha);
        pits.get(pits.size() - 1).setNextPit(kalaha);
        pits.add(kalaha);
        player.setKalaha(kalaha);
    }

}
