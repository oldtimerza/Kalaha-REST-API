package kalaha.game.factories;

import kalaha.dtos.KalahaJson;
import kalaha.dtos.PitJson;
import kalaha.game.GameBoard;
import kalaha.game.Player;

import java.util.List;

public interface ReconstructBoard {
     GameBoard createBoard(List<Player> players, List<PitJson> pitJsons, List<KalahaJson> kalahaJsons);
}
