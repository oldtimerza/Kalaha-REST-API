package kalaha.mappers;

import kalaha.dtos.GameJson;
import kalaha.game.Game;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public class GameMapper {
    public PropertyMap<Game, GameJson> map(){
       return new PropertyMap<Game, GameJson>() {
           @Override
           protected void configure() {
               map(source.getCurrentPlayer().getIndex(), destination.currentPlayerIndex);
               map(source.getNextPlayer().getIndex(), destination.nextPlayerIndex);
               map(source.getGameBoard().getKalahas(), destination.kalahas);
               map(source.getGameBoard().getPits(), destination.pits);
               map(source.getStatus(), destination.status);
               map(source.getPlayers().size(), destination.numberOfPlayers);
           }
       };
    }
}
