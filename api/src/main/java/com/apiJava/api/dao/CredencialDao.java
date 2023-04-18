package com.apiJava.api.dao;

import com.apiJava.api.models.Usuario;

public interface CredencialDao {

    Usuario userByCredencials(Usuario usuario);
}
