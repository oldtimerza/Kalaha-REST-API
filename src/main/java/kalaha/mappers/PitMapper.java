package kalaha.mappers;

import kalaha.dtos.PitJson;
import kalaha.game.Pit;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public class PitMapper {
    public PropertyMap<Pit, PitJson> map(){
        return new PropertyMap<Pit, PitJson>() {
            @Override
            protected void configure() {
                map(source.getNumber(), destination.pitNumber);
                map(source.getStones().size(), destination.numberOfStones);
                map(source.getOwner().getIndex(), destination.owningPlayerIndex);
            }
        };
    }
}
