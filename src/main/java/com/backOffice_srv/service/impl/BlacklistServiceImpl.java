package com.backOffice_srv.service.impl;

import com.backOffice_srv.exception.AgentException;
import com.backOffice_srv.modele.Blacklist;
import com.backOffice_srv.repository.BlacklistRepository;
import com.backOffice_srv.service.BlacklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlacklistServiceImpl implements BlacklistService {

    @Autowired
    private BlacklistRepository blacklistRepository;

    @Override
    public Blacklist addToBlacklist(Blacklist client) {
        Blacklist blacklist = checkClientINBlacklist(client);
        if(blacklist==null){
            return blacklistRepository.save(client);
        }else{
            blacklist.setArchived(false);
            return blacklistRepository.save(blacklist);
        }
    }

    @Override
    public List<Blacklist> getBlacklist(Integer page) {
        Pageable pageable = PageRequest.of(page-1 , 5);
        return blacklistRepository.getBlacklist(pageable);
    }

    @Override
    public Blacklist checkClientINBlacklist(Blacklist client) {
        Blacklist blacklist = blacklistRepository.checkClientINBlacklist(client.getFirstName(),client.getLastName(),client.getPhoneNumber());
        if(blacklist == null ) throw new AgentException("inexistent client !");
        else{
            return blacklist;
        }
    }

    @Override
    public Integer deleteFromBlacklist(Integer id) {
        Blacklist blacklist = blacklistRepository.getIdclient(id);
        blacklist.setArchived(true);
        return blacklistRepository.save(blacklist).getId();
    }
}
