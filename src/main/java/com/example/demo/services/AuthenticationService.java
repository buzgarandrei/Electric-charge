package com.example.demo.services;

import com.example.demo.entities.enums.RoleEnum;
import com.example.demo.response.StateResponse;

import javax.servlet.http.HttpServletRequest;

public interface AuthenticationService {

    public RoleEnum getUserRole(Long id);

    public String registerLogin(Long idUser,String username,RoleEnum roleEnum);

    public StateResponse logout(String token);

    public boolean validateTokenAndRole(HttpServletRequest httpServletRequest, RoleEnum roleRequested);

    public boolean validateTokenAndUser(HttpServletRequest httpServletRequest,Long idUser);

    public Long getUserIdByToken(HttpServletRequest httpServletRequest);
}
