package com.apiJava.api.dao;

import com.apiJava.api.models.Usuario;
import java.util.List;

public interface UsuarioDao {

    List<Usuario> getUsers();

    void deleteUser(int id);

    void createUser(Usuario usuario);


}
