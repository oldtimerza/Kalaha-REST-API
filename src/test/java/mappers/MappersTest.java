package mappers;

import kalaha.dtos.GameDto;
import kalaha.game.*;
import kalaha.mappers.GameMapper;
import kalaha.mappers.KalahaMapper;
import kalaha.mappers.PitMapper;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.modelmapper.ModelMapper;
import java.util.ArrayList;
import java.util.List;

public class MappersTest {
    @Test
    public void shouldMapGameToGameDto(){
        ModelMapper mapper = new ModelMapper();
        GameMapper gameMapper = new GameMapper();
        KalahaMapper kalahaMapper = new KalahaMapper();
        PitMapper pitMapper = new PitMapper();
        mapper.addMappings(gameMapper.map());
        mapper.addMappings(kalahaMapper.map());
        mapper.addMappings(pitMapper.map());

        Integer playerIndex = 0;
        Player currentPlayer = new Player(playerIndex);
        Integer nextPlayerIndex = 1;
        Player nextPlayer =new Player(nextPlayerIndex);
        List<Player> players = new ArrayList<>();
        players.add(currentPlayer);
        players.add(nextPlayer);

        Integer pitNumber = 1;
        Pit pit = new Pit(new ArrayList<>(), currentPlayer, pitNumber);
        List<Pit> pits = new ArrayList<>();
        pits.add(pit);

        Kalaha kalaha = new Kalaha(pit);
        List<Kalaha> kalahas = new ArrayList<>();
        kalahas.add(kalaha);

        GameBoard gameBoard = new GameBoard(pits, kalahas);
        GameState state = new GameState();
        state.setStatus(Status.RUNNING);
        state.setCurrentPlayer(currentPlayer);
        state.setNextPlayer(nextPlayer);
        Game game = new Game(gameBoard, state, players);

        GameDto dto = mapper.map(game, GameDto.class);
        Assert.assertThat(dto.currentPlayerIndex, Matchers.equalTo(playerIndex));
        Assert.assertThat(dto.nextPlayerIndex, Matchers.equalTo(nextPlayerIndex));
        Assert.assertThat(dto.numberOfPlayers, Matchers.equalTo(2));
        Assert.assertThat(dto.status, Matchers.equalTo(Status.RUNNING));
        Assert.assertThat(dto.kalahas.size(), Matchers.equalTo(1));
        Assert.assertThat(dto.pits.size(), Matchers.equalTo(1));
        Assert.assertThat(dto.kalahas.get(0).pitNumber, Matchers.equalTo(pitNumber));
        Assert.assertThat(dto.pits.get(0).numberOfStones, Matchers.equalTo(0));
        Assert.assertThat(dto.pits.get(0).owningPlayerIndex, Matchers.equalTo(playerIndex));
        Assert.assertThat(dto.pits.get(0).pitNumber, Matchers.equalTo(pitNumber));
    }
}
