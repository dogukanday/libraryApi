package dev.dodo.library.core.config.modelMapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModelManagerService implements IModelMapperService{

    private final ModelMapper modelMapper;

    @Autowired
    public ModelManagerService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public ModelMapper forRequest() {
        this.modelMapper.getConfiguration().setAmbiguityIgnored(true).setMatchingStrategy(org.modelmapper.convention.MatchingStrategies.STANDARD);
        return this.modelMapper;
    }

    @Override
    public ModelMapper forResponse() {
        this.modelMapper.getConfiguration().setAmbiguityIgnored(true).setMatchingStrategy(org.modelmapper.convention.MatchingStrategies.LOOSE);
        return this.modelMapper;
    }
}
