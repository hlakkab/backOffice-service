package com.backOffice_srv.controller.converter;


import com.backOffice_srv.DTO.AgentDTO;
import com.backOffice_srv.modele.Agent;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AgentConverter implements AbstractConverter<Agent, AgentDTO>{

    private final ModelMapper modelMapper;

    @Autowired
    public AgentConverter(ModelMapper modelMapper) {
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);

        this.modelMapper = modelMapper;
    }

    @Override
    public Agent convertToDM(AgentDTO agentDTO) {
        if (agentDTO == null)
            return null;

        return modelMapper.map(agentDTO, Agent.class);
    }

    @Override
    public AgentDTO convertToDTO(Agent agent) {
        if (agent == null)
            return null;
        return modelMapper.map(agent, AgentDTO.class);
    }
}
