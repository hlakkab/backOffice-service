package com.backOffice_srv.service.impl;


import com.backOffice_srv.handler.RestTemplateResponseErrorHandler;
import com.backOffice_srv.modele.Agent;
import com.backOffice_srv.repository.AgentRepository;
import com.backOffice_srv.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AgentServiceImpl implements AgentService {

    private final String transfer_rest_url = "http://localhost:9191/api_transfer/";
    private final String client_rest_url = "http://localhost:9191/api_client/";

//    private final String transfer_rest_url = "https://transfer-microserv.herokuapp.com/api_transfer/";
//    private final String client_rest_url = "https://client-microserv.herokuapp.com/api_client/";

//    private final String transfer_rest_url = "http://localhost:8082/api_transfer/";
//    private final String client_rest_url = "http://localhost:9000/api_client/";

    @Autowired
    private AgentRepository agentRepository;

    @Autowired
    private RestTemplate restTemplate;


    @Override
    public Agent createAgent(Agent agent) {
        return agentRepository.save(agent);
    }

    @Override
    public Agent getAgentbyId(Integer id) {
        return agentRepository.findById(id).get();
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return agentRepository
                .findFirstByEmail(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException(String.format("Username %s not found.", username))
                );
    }



    @Override
    public ResponseEntity<?> bloqueTransferByReference(String reference, String motif) {
        restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept","application/json");
        HttpEntity<?> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(transfer_rest_url+"/UniqueTransfer/bloque/"+reference+"?motif="+motif,HttpMethod.PUT,entity,Object.class,1);
    }

    @Override
    public ResponseEntity<?> unlockTransferByReference(String reference, String motif) {
        restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept","application/json");
        HttpEntity<?> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(transfer_rest_url+"/UniqueTransfer/unlock/"+reference+"?motif="+motif,HttpMethod.PUT,entity,Object.class,1);
    }


    @Override
    public ResponseEntity<?> getTransferByReference(String reference) {
        restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());
        return restTemplate.getForEntity(transfer_rest_url+"UniqueTransfer/"+reference, Object.class,1);
    }


    @Override
    public ResponseEntity<?> getTransfers(Integer page) {
        restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());
        return restTemplate.getForEntity(transfer_rest_url+"/MultiTransfers?page="+page, Object.class,1);
    }

    @Override
    public ResponseEntity<?> countTransferById(Integer id) {
        restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());
        return restTemplate.getForEntity(transfer_rest_url+"/MultiTransfer/count/agent"+"?idAgent="+id, Object.class,1);
    }

    @Override
    public ResponseEntity<?> countTransfers() {
        restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());
        return restTemplate.getForEntity(transfer_rest_url+"/MultiTransfers/count/", Object.class,1);
    }

    @Override
    public ResponseEntity<?> getClients() {
        restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());
        return restTemplate.getForEntity(transfer_rest_url+"/clients", Object.class,1);
    }

    @Override
    public ResponseEntity<?> countClients() {
        restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());
        return restTemplate.getForEntity(transfer_rest_url+"/clients/count", Object.class,1);
    }

    @Override
    public ResponseEntity<?> getTransferByAll() {
        restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());
        return restTemplate.getForEntity(transfer_rest_url+"/MultiTransfers/all", Object.class,1);
    }


    @Override
    public ResponseEntity<?> getClientByCin(String cin) {
        restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());
        return restTemplate.getForEntity(client_rest_url+"/cin/"+cin, Object.class,1);
    }

    @Override
    public ResponseEntity<?> getClientByPhoneNumber(String phoneNumber) {
        restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());
        return restTemplate.getForEntity(client_rest_url+"/phonenumber/"+phoneNumber, Object.class,1);
    }


}
