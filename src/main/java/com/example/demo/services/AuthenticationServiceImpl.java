package com.example.demo.services;

import com.example.demo.entities.enums.RoleEnum;
import com.example.demo.repositories.UsersRepository;
import com.example.demo.response.StateResponse;
import com.example.demo.utils.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    //will use to compare the constant(that contains the token) from the header that has that name
    private static final String REQUEST_HEADER_TOKEN = "REQUEST_HEADER_TOKEN";

    //set this boolean to true when you want to put it in production, else let it false
    private static final boolean VALIDATE_ACTIVE = false;

    @Autowired
    UsersRepository usersRepository;

    private Map<String,UserSession> sessionMap = new HashMap<>();

    @Override
    public RoleEnum getUserRole(Long id) {

        return usersRepository.getUserRole(id);
    }

    @Override
    public String registerLogin(Long idUser, String username, RoleEnum roleEnum) {

        String token = UUID.randomUUID().toString().replace("-","");

        UserSession userSession = new UserSession();

        userSession.setToken(token);
        userSession.setUserId(idUser);
        userSession.setUsername(username);
        userSession.setRoleEnum(roleEnum);

        sessionMap.put(token,userSession);

        return token;
    }

    public StateResponse logout(String token) {

        StateResponse stateResponse = new StateResponse();
        if(sessionMap.remove(token) != null) {
            stateResponse.setSuccess(true);
        }
        else stateResponse.setSuccess(false);

        return stateResponse;
    }

    public boolean validateTokenAndRole(HttpServletRequest httpServletRequest,RoleEnum roleRequested) {

        if (!(VALIDATE_ACTIVE)) return true;

        String requestHeaderToken = httpServletRequest.getHeader(REQUEST_HEADER_TOKEN);
        if (requestHeaderToken == null) return false;

        UserSession userSession = sessionMap.get(requestHeaderToken);
        if (userSession == null) return false;

        if (roleRequested == null) return true;

        RoleEnum roleTypeLoggedUser = userSession.getRoleEnum();
        if (roleRequested == RoleEnum.ADMIN) return true;

        if (roleRequested != roleTypeLoggedUser) return false;

        return true;
    }

    public boolean validateTokenAndUser(HttpServletRequest httpServletRequest,Long idUser) {

        if(!VALIDATE_ACTIVE) return true;

        String requestHeaderToken = httpServletRequest.getHeader(REQUEST_HEADER_TOKEN);
        if(requestHeaderToken == null) {
            return false;
        }

        UserSession userSession = sessionMap.get(requestHeaderToken);

        if (userSession == null) {
            return false;
        }

        if(idUser == null) {
            return false;
        }

        Long sessionUserId = userSession.getUserId();

        return sessionUserId.equals(idUser);

    }
    public Long getUserIdByToken(HttpServletRequest httpServletRequest) {

        String requestHeaderToken = httpServletRequest.getHeader(REQUEST_HEADER_TOKEN);
        if(requestHeaderToken == null) {
            return null;
        }

        UserSession userSession = sessionMap.get(requestHeaderToken);

        if (userSession == null) {
            return null;
        }

        return userSession.getUserId();
    }

}
