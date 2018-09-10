package kalaha.mappers;

import kalaha.dtos.KalahaJson;
import kalaha.game.Kalaha;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public class KalahaMapper {
    public PropertyMap<Kalaha, KalahaJson> map(){
        return new PropertyMap<Kalaha, KalahaJson>() {
            @Override
            protected void configure() {
                map(source.getPit().getNumber(), destination.pitNumber);
            }
        };
    }
}
