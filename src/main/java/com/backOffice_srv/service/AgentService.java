package com.backOffice_srv.service;

import com.backOffice_srv.VO.Multitransfer;
import com.backOffice_srv.VO.Transfer;
import com.backOffice_srv.VO.Client;
import com.backOffice_srv.modele.Agent;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AgentService extends UserDetailsService {

    Agent createAgent(Agent agent);

    Agent getAgentbyId(Integer id);

    ResponseEntity<?> getTransferByReference(String reference);

    ResponseEntity<?> getTransfers(Integer page);

    ResponseEntity<?> bloqueTransferByReference(String reference, String motif);

    ResponseEntity<?> unlockTransferByReference(String reference, String motif);

    ResponseEntity<?> getClientByCin(String cin);

    ResponseEntity<?> getClientByPhoneNumber(String phoneNumber);

    ResponseEntity<?> countTransferById(Integer id);

    ResponseEntity<?> countTransfers();

    ResponseEntity<?> getClients();

    ResponseEntity<?> countClients();

    ResponseEntity<?> getTransferByAll();
}
