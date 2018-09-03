package kalaha.mappers;

import kalaha.dtos.KalahaDto;
import kalaha.game.Kalaha;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public class KalahaMapper {
    public PropertyMap<Kalaha, KalahaDto> map(){
        return new PropertyMap<Kalaha, KalahaDto>() {
            @Override
            protected void configure() {
                map(source.getPit().getNumber(), destination.pitNumber);
            }
        };
    }
}
