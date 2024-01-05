package com.backOffice_srv.controller;

import com.backOffice_srv.VO.Multitransfer;
import com.backOffice_srv.VO.Transfer;
import com.backOffice_srv.service.impl.AgentServiceImpl;
import com.backOffice_srv.service.impl.AuthenticationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/api_backoffice")
@CrossOrigin(allowCredentials = "true",  originPatterns = "*")
public class TransferCtrl {

    @Autowired
    private AgentServiceImpl agentService;

    @Autowired
    private AuthenticationServiceImpl authenticationService;

    @GetMapping("/UniqueTransfer/{reference}")
    public ResponseEntity<?> getTransferByReference(@PathVariable("reference") String reference){
        return (agentService.getTransferByReference(reference));
    }


    @GetMapping("/Multitransfers")
    public ResponseEntity<?> getTransfers(@RequestHeader("id") Integer id
                            ,@RequestParam("page") Integer page){
        return (agentService.getTransfers(page));
    }

    @GetMapping("/Multitransfers/count")
    public ResponseEntity<?> countTransfers(@RequestHeader("id") Integer id){
        return (agentService.countTransfers());
    }



    @GetMapping("/Multitransfers/all")
    public ResponseEntity<?> getMultiTransferByidAgentAll(){
        return (agentService.getTransferByAll());
    }


    @PutMapping("/UniqueTransfer/bloque/{reference}")
    public ResponseEntity<?> bloqueTransferByReference(@PathVariable("reference") String reference,
                                                                      @RequestParam("motif") String motif,
                                                                      @RequestHeader("id") Integer id){

        return (agentService.bloqueTransferByReference(reference,motif));

    }

    @PutMapping("/UniqueTransfer/unlock/{reference}")
    public ResponseEntity<?> unlockTransferByReference(@PathVariable("reference") String reference,
                                                       @RequestParam("motif") String motif,
                                                       @RequestHeader("id") Integer id){

        return (agentService.unlockTransferByReference(reference,motif));

    }


}
