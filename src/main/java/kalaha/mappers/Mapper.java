package kalaha.mappers;

import org.modelmapper.ModelMapper;

public class Mapper {

    private static ModelMapper mapper;

    private Mapper(){
    }

    public static ModelMapper getMapper(){
        if(mapper == null){
            mapper = new ModelMapper();
            GameMapper gameMapper = new GameMapper();
            PitMapper pitMapper = new PitMapper();
            KalahaMapper kalahaMapper = new KalahaMapper();
            mapper.addMappings(gameMapper.map());
            mapper.addMappings(pitMapper.map());
            mapper.addMappings(kalahaMapper.map());
            return mapper;}
        return mapper;
    }
}
