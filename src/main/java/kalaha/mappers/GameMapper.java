package kalaha.mappers;

import kalaha.dtos.GameDto;
import kalaha.game.Game;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public class GameMapper {
    public PropertyMap<Game, GameDto> map(){
       return new PropertyMap<Game, GameDto>() {
           @Override
           protected void configure() {
              map(source.getCurrentPlayer().getIndex(), destination.activePlayerIndex);
              map(source.getGameBoard().getKalahas(), destination.kalahas);
              map(source.getGameBoard().getPits(), destination.pits);
              map(source.getStatus(), destination.status);
           }
       };
    }
}
