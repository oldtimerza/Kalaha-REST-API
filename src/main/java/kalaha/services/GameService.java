package kalaha.services;

import kalaha.dtos.GameJson;
import kalaha.game.Game;
import kalaha.game.NotPlayersTurnException;
import kalaha.game.Player;
import kalaha.game.factories.GameFactory;
import kalaha.game.factories.ReconstructGame;
import kalaha.game.moves.CaptureOpponentsStones;
import kalaha.game.moves.DropStone;
import kalaha.game.moves.Sow;
import kalaha.game.moves.TakeAnotherTurn;
import kalaha.game.rules.CaptureStonesRules;
import kalaha.game.rules.Check;
import kalaha.game.rules.DropStoneRules;
import kalaha.game.rules.TakeAnotherTurnRules;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    private DropStoneRules dropStoneRules;

    private CaptureStonesRules captureStonesRules;

    private TakeAnotherTurnRules takeAnotherTurnRules;

    private GameFactory initialGame;

    private ReconstructGame gameFromRequest;

    public Game start() throws Exception {
        Game game = initialGame.createGame();
        return game;
    }

    public Game sow(GameJson gameJson, int playerIndex, int pitNumber) throws Exception, NotPlayersTurnException {
        Game game = gameFromRequest.createGame(gameJson);
        Player player = game.getPlayers().get(playerIndex);
        Check<DropStone> dropStoneCheck = new Check<>(dropStoneRules);
        Check<CaptureOpponentsStones> captureOpponentsStonesCheck = new Check<>(captureStonesRules);
        Check<TakeAnotherTurn> takeAnotherTurnCheck = new Check<>(takeAnotherTurnRules);
        Sow sow = new Sow(dropStoneCheck, captureOpponentsStonesCheck, takeAnotherTurnCheck, pitNumber);
        game = game.makeMove(player, sow);
        return game;
    }

    @Autowired
    public void setDropStoneRules(DropStoneRules dropStoneRules) {
        this.dropStoneRules = dropStoneRules;
    }

    @Autowired
    public void setCaptureStonesRules(CaptureStonesRules captureStonesRules) {
        this.captureStonesRules = captureStonesRules;
    }

    @Autowired
    public void setTakeAnotherTurnRules(TakeAnotherTurnRules takeAnotherTurnRules) {
        this.takeAnotherTurnRules = takeAnotherTurnRules;
    }

    @Autowired
    public void setInitialGame(GameFactory initialGame) {
        this.initialGame = initialGame;
    }

    @Autowired
    public void setGameFromRequest(ReconstructGame gameFromRequest) {
        this.gameFromRequest = gameFromRequest;
    }
}
