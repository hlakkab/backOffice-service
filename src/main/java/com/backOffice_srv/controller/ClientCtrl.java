package com.backOffice_srv.controller;


import com.backOffice_srv.VO.Client;
import com.backOffice_srv.service.impl.AgentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api_backoffice")
@CrossOrigin(allowCredentials = "true",  originPatterns = "*")
public class ClientCtrl {

    @Autowired
    private AgentServiceImpl agentService;

    @GetMapping("/clients")
    public ResponseEntity<?> getUsers() {
        return agentService.getClients();
    }

    @GetMapping("/clients/count")
    public ResponseEntity<?> countUsers() {
        return agentService.countClients();
    }

    @GetMapping("/client/cin/{cin}")
    public ResponseEntity<?> getCurrentUserByCin(@PathVariable("cin") String cin) {
        return agentService.getClientByCin(cin);
    }

    @GetMapping("/client/ph/{phoneNumber}")
    public ResponseEntity<?> getClientByPhoneNumber(@PathVariable("phoneNumber") String phoneNumber) {
        return agentService.getClientByPhoneNumber(phoneNumber);
    }

}
