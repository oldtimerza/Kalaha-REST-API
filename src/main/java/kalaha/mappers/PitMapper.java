package kalaha.mappers;

import kalaha.dtos.PitDto;
import kalaha.game.Pit;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public class PitMapper {
    public PropertyMap<Pit, PitDto> map(){
        return new PropertyMap<Pit, PitDto>() {
            @Override
            protected void configure() {
                map(source.getNumber(), destination.pitNumber);
                map(source.getStones().size(), destination.numberOfStones);
                map(source.getOwner().getIndex(), destination.owningPlayerIndex);
            }
        };
    }
}
