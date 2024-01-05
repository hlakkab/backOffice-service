package com.backOffice_srv.modele.listeners;

import com.backOffice_srv.modele.Agent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.persistence.PrePersist;

@Component
public class AgentListener {
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AgentListener(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @PrePersist
    public void encodePasswordBeforeInsertion(Agent agent) {
        agent.setPassword(
                passwordEncoder.encode(agent.getPassword())
        );
    }
}
