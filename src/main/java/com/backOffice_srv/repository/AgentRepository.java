package com.backOffice_srv.repository;

import com.backOffice_srv.modele.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AgentRepository extends JpaRepository<Agent,Integer> {

    Optional<Agent> findFirstByEmail(String username);
}
