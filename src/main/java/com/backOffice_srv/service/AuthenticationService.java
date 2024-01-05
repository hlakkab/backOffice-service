package com.backOffice_srv.service;

import com.backOffice_srv.DTO.AuthenticationDTO;
import com.backOffice_srv.DTO.AuthenticationTokenDTO;

public interface AuthenticationService {
    AuthenticationTokenDTO authenticate(AuthenticationDTO authenticationDto);
}
