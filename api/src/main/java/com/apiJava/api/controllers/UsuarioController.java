package com.apiJava.api.controllers;

import com.apiJava.api.dao.UsuarioDao;
import com.apiJava.api.models.Usuario;
import com.apiJava.api.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class UsuarioController {


    @Autowired
    private UsuarioDao usuarioDao;
    @Autowired
    private  JWTUtil jwtUtil;



    /*@RequestMapping(value = "api/usuarios/{id}", method = RequestMethod.GET)
    public Usuario getUsuario(@PathVariable int id) {
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNombre("maxi");
        usuario.setApellido("calvo");
        usuario.setEmail("maxi@gmail.com");
        usuario.setTelefono("35122");
        usuario.setPassword("1234");
        return usuario;
    }*/

    private boolean validateToken(String token) {
        String id = jwtUtil.getKey(token);
        return id != null;
    }

    @RequestMapping(value = "api/usuarios", method = RequestMethod.GET)
    public List<Usuario> getUsers(@RequestHeader(value = "Authorization") String token) {

        if(!validateToken(token)) {
            return null;
        }
        return usuarioDao.getUsers();
    }

    @RequestMapping(value = "api/usuarios", method = RequestMethod.POST)
    public void createUser(@RequestBody Usuario usuario) {

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1, 1024, 1, usuario.getPassword());
        usuario.setPassword(hash);

        usuarioDao.createUser(usuario);
    }

    @RequestMapping(value = "api/usuarios/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@RequestHeader(value = "Authorization") String token, @PathVariable int id) {
        if (!validateToken(token)) {
            return;
        }
        usuarioDao.deleteUser(id);
    }

}
