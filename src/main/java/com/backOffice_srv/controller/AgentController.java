package com.backOffice_srv.controller;

import com.backOffice_srv.DTO.AuthenticationDTO;
import com.backOffice_srv.DTO.AuthenticationTokenDTO;
import com.backOffice_srv.controller.converter.AgentConverter;
import com.backOffice_srv.modele.Agent;
import com.backOffice_srv.service.impl.AgentServiceImpl;
import com.backOffice_srv.service.impl.AuthenticationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api_backoffice")
@CrossOrigin(allowCredentials = "true",  originPatterns = "*")
public class AgentController {

    private final AgentConverter agentConverter;

    @Autowired
    private AgentServiceImpl agentService;

    @Autowired
    private AuthenticationServiceImpl authenticationService;

    @Autowired
    public AgentController(AgentConverter agentConverter) {
        this.agentConverter = agentConverter;
    }

    @PostMapping("/createAgent")
    public ResponseEntity<Integer> createAgent(@Valid @RequestBody Agent agent){
        Agent agent_tmp = agentService.createAgent(agent);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(agent_tmp.getId_agent());
    }

    @PostMapping("/login")
    public AuthenticationTokenDTO login(@Valid @RequestBody AuthenticationDTO authenticationDto) {
        return authenticationService.authenticate(authenticationDto);
    }


    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(@RequestHeader("id") Integer id) {
        Agent agent = agentService.getAgentbyId(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(agentConverter.convertToDTO(agent));
    }

}
