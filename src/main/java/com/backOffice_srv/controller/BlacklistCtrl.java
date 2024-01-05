package com.backOffice_srv.controller;


import com.backOffice_srv.modele.Blacklist;
import com.backOffice_srv.service.BlacklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api_backoffice")
@CrossOrigin(allowCredentials = "true",  originPatterns = "*")
public class BlacklistCtrl {

    @Autowired
    private BlacklistService blacklistService;

    @PostMapping("/blacklist")
    public ResponseEntity<?> getBlacklist(@RequestBody Blacklist client){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(blacklistService.addToBlacklist(client));
    }

    @GetMapping("/blacklist")
    public ResponseEntity<?> getBlacklist(@RequestHeader("id") Integer id
            ,@RequestParam("page") Integer page){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(blacklistService.getBlacklist(page));
    }

    @PostMapping("/blacklist/client")
    public ResponseEntity<?> checkClientINBlacklist(@RequestBody Blacklist client){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(blacklistService.checkClientINBlacklist(client));
    }

    @PutMapping("/blacklist/client/delete")
    public ResponseEntity<?> deleteFromBlacklist(@RequestParam("id") Integer id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(blacklistService.deleteFromBlacklist(id));
    }
}
