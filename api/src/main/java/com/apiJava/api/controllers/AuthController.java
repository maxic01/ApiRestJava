package com.apiJava.api.controllers;

import com.apiJava.api.dao.CredencialDao;
import com.apiJava.api.models.Usuario;
import com.apiJava.api.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private CredencialDao credencialDao;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    public String login(@RequestBody Usuario usuario) {
        Usuario logUser = credencialDao.userByCredencials(usuario);
        if (logUser !=null) {

            String token = jwtUtil.create(String.valueOf(logUser.getId()), logUser.getEmail());
            return token;
        }
        return "FAIL";
    }
}
